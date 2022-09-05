package com.pattanacode.controllers;

import com.pattanacode.error.ManualException;
import com.pattanacode.models.Customer;
import com.pattanacode.models.TrackingPrice;
import com.pattanacode.models.criteria.CustomerCriteria;
import com.pattanacode.repository.CustomerRepository;
import com.pattanacode.utils.DateTimeUtils;
import com.pattanacode.utils.Utility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Customer", description = "Operations related to Customer Controller")
@RestController
@RequestMapping("/api/customer/v1")
public class CustomerController {
    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerRepository customerRepository;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(method= RequestMethod.GET, value="/customers")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

   // @Secured({"ROLE_ADMIN", "ROLE_MOD", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping({"/{customerId}", "/", ""})
    public ResponseEntity<Page<Customer>> getCustomer(
    		  @PathVariable(required = false) Optional<String> customerId,
    		  @RequestParam(required = false, value = "start") Optional<String> start,
              @RequestParam(required = false, value = "end") Optional<String> end,
              @RequestParam(required = false, defaultValue = "20", value = "limit") int limit,
              @RequestParam(required = false, defaultValue = "0", value = "page") int page
      ) throws Exception {
    	 Page<Customer> customerPage = null;
    	 CustomerCriteria customerCriteria =new CustomerCriteria();
    	  if (start.isPresent() && end.isPresent()) {
              if (Utility.validateJavaDate(start.get()) && Utility.validateJavaDate(end.get())) {

                  Date startDate = Utility.parseStringToDateTime(start.get(), "000000");
                  Date endDate = Utility.parseStringToDateTime(end.get(), "235959");

                  if (endDate.compareTo(startDate) > -1) {
                	  customerCriteria.setStartDate(startDate);
                	  customerCriteria.setEndDate(endDate);
                  } else {
                      logger.info("-endDate- is less than -startDate-");
                      throw new ManualException(HttpStatus.BAD_REQUEST, "-endDate- is less than -startDate-");
                  }

              } else {
            	  
                  logger.info("-startDate- or -endDate- is in the wrong format/type");
                  throw new ManualException(HttpStatus.BAD_REQUEST, "-startDate- or -endDate- is in the wrong format/type");
              }
          } else if (!start.isPresent() && !end.isPresent()) {
              logger.info("find all Customer");
          } else {
              logger.info("-startDate- or -endDate- is missing");
              throw new ManualException(HttpStatus.BAD_REQUEST, "-startDate- or -endDate- is missing");
          }
    	  
    	  
    	  customerCriteria.setLimit(limit);
    	  customerCriteria.setPage(page);
    	  Pageable paging = PageRequest.of(page, limit);
    	  customerPage=customerRepository.findAll(paging);
    	  
    	 
    	   if (customerPage.isEmpty()) {
               logger.info("Customer is not found");
               throw new ManualException(HttpStatus.NOT_FOUND, "Customer is not found");
           }//if
    	  
    	 return ResponseEntity.status(HttpStatus.OK).body(customerPage);
    }//end method
    
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method= RequestMethod.GET, value="/findCustomersByLineID")
    public List<Customer> findCustomersBy(String lineID) {
        return customerRepository.findCustomersBy(lineID);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method= RequestMethod.POST, value="/findByNickNameLike")
    public List<Customer> findByNickNameLike(@RequestBody Customer customer) {
       // System.out.println("findByNickNameLike lineID="+customer.getNickName());
        return customerRepository.findByNickNameLike(customer.getNickName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method=RequestMethod.POST, value="/customer")
    public String saveCustomer(@RequestBody Customer customer) {

        customer.setCreatedDate(DateTimeUtils.getSystemDate());
        customer.setUpdatedDate(DateTimeUtils.getSystemDate());
        customerRepository.save(customer);
        return "Customer saved id="+customer.getNickName()+" is Successes.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(method=RequestMethod.GET, value="/customer/{id}")
    public Optional<Customer> show(@PathVariable String id) {
        return customerRepository.findById(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        logger.error("Internal server error.", e);
        return e;
    }


}
