/**
 * 
 */
package com.pattanacode.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pattanacode.models.Product;
import com.pattanacode.models.criteria.ProductCriteria;
import com.pattanacode.repository.ProductRepository;
import com.pattanacode.utils.DateTimeUtils;

/**
 * @author patismyname
 *
 */
@Service
@Transactional
public class ProductService {

 private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
	 
	 @Autowired
	 private ProductRepository productRepository;
	 
	 @Autowired
	 private FileService fileService;
	    
	 
	 public Page<Product> findProducts(ProductCriteria cri) {
			PageRequest pageRequest = genPageRequest(cri.getPage(), cri.getLimit());
			Page<Product> promotionPage=productRepository.findAll(
					pageRequest);
			return  promotionPage;
		}
		private PageRequest genPageRequest(int page, int limit) {
			return PageRequest.of(page,limit);	        
		}
		
		
		  @SuppressWarnings("unused") private Specification<Product>
		  getProductSpecification(Long productId, Date startDate, Date endDate){ return
		  (root, query, cb) -> { List<Predicate> predicates = new ArrayList<>();
		  List<Predicate> between = new ArrayList<>(); List<Predicate> periods = new
		  ArrayList<>();
		  
		  if (productId != null) {
		  predicates.add(cb.equal(root.get("productId"),productId)); }
		  
		  if (startDate != null && endDate != null) {
		  between.add(cb.between(root.get("startDate"), startDate, endDate));
		  between.add(cb.between(root.get("endDate"), startDate, endDate));
		  
		  periods.add(cb.lessThanOrEqualTo(root.get("startDate"), startDate));
		  periods.add(cb.greaterThanOrEqualTo(root.get("endDate"), endDate));
		  
		  between.add(cb.and(periods.toArray(new Predicate[periods.size()])));
		  predicates.add(cb.or(between.toArray(new Predicate[between.size()]))); }
		  
		  
		  return cb.and(predicates.toArray(new Predicate[predicates.size()])); }; }
		 
	 
	 public Optional<Product> saveProduct(Product product,MultipartFile file) throws Exception{
		 String fileId ="";
		
		 // Upload file
			 if(file!=null ) {
			    fileId = fileService.uploadMultipartFile(file);
			 }//if
		
		 // create new one
		 if(product.getProductId()==null) {
			 
			 logger.info("ProductService#saveProduct#fileId="+fileId);
			 product.setFileId(fileId);
		     product.setCreatedDate(DateTimeUtils.getSystemDate());
		     product.setUpdatedDate(DateTimeUtils.getSystemDate());
		     
			
				return Optional.of(productRepository.save(product));
			}
			else {
				
				//update by ProductId
				product.setFileId(fileId);
				logger.info("product.getProductId()="+product.getProductId());
				logger.info("product.getFileId()="+product.getFileId());
				//if(!productRepository.findById(product.getProductId()).isPresent()) {
					Optional<Product> prOptional=productRepository.findById(product.getProductId());
					if(prOptional!=null ) {
					
				
						//Delete file uploaded
					fileService.deleteFile(prOptional.get().getFileId());	
						//Update product
					return Optional.of(productRepository.save(product));
				}
			}
			return Optional.empty();
			
			
		
	 }//end method saveProduct
	
	 public Optional<Product> updateProduct(Product product) throws Exception{
		 Optional<Product> prOptional=null;
		 if(product.getProductId()!=null) {
			 
			 Product newProduct=new Product();
			 
			 prOptional=productRepository.findById(product.getProductId());
			 
			 newProduct=prOptional.get();
			 newProduct.setProductType(product.getStatusType());
			 newProduct.setUpdatedBy("System");
			 newProduct.setUpdatedDate(DateTimeUtils.getSystemDate());
			 
			 productRepository.save(newProduct);
			 
		 }
		 return prOptional;
	 }
	 
	 
}//end class
