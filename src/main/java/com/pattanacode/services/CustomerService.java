/**
 * 
 */
package com.pattanacode.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pattanacode.models.Customer;
import com.pattanacode.models.criteria.CustomerCriteria;
import com.pattanacode.repository.CustomerRepository;
import javax.persistence.criteria.Predicate;
/**
 * @author patismyname
 *
 */
@Service
@Transactional
public class CustomerService {
	
	 private final static Logger logger = LoggerFactory.getLogger(CustomerService.class);
	 
	 @Autowired
	 private CustomerRepository customerRepository;

	 private PageRequest genPageRequest(int page, int limit) {
			return PageRequest.of(page,limit);	        
		}
		
		  private Specification<Customer> getCustomerSpecification(String
		  customerId,String email, Date startDate, Date endDate){ return (root, query,
		  cb) -> { List<Predicate> predicates = new ArrayList<>(); List<Predicate>
		  between = new ArrayList<>(); List<Predicate> periods = new ArrayList<>();
		  
		  if (customerId != null) {
		  predicates.add(cb.equal(root.get("id"),customerId)); } if (email != null) {
		  predicates.add(cb.equal(root.get("email"),email)); }
		  
		  if (startDate != null && endDate != null) {
		  between.add(cb.between(root.get("startDate"), startDate, endDate));
		  between.add(cb.between(root.get("endDate"), startDate, endDate));
		  
		  periods.add(cb.lessThanOrEqualTo(root.get("startDate"), startDate));
		  periods.add(cb.greaterThanOrEqualTo(root.get("endDate"), endDate));
		  
		  between.add(cb.and(periods.toArray(new Predicate[periods.size()])));
		  predicates.add(cb.or(between.toArray(new Predicate[between.size()]))); }
		  
		  
		  return cb.and(predicates.toArray(new Predicate[predicates.size()])); }; }
		 
	 
	 
	 
	 
		/*
		 * public Page<Customer> findPromotions(CustomerCriteria customerCriteria) {
		 * PageRequest pageRequest = genPageRequest(customerCriteria.getPage(),
		 * customerCriteria.getLimit()); Page<Customer>
		 * customerPage=customerRepository.findAll(getCustomerSpecification(
		 * customerCriteria.getId(), customerCriteria.getFirstName(),
		 * customerCriteria.getStartDate(), customerCriteria.getEndDate()),
		 * pageRequest);
		 * 
		 * return customerPage; }
		 */

}
