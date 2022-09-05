package com.pattanacode.controllers;

import com.pattanacode.error.ManualException;
import com.pattanacode.models.Product;
import com.pattanacode.models.criteria.ProductCriteria;
import com.pattanacode.services.ProductService;
import com.pattanacode.utils.DateTimeBase;
import com.pattanacode.utils.DateTimeUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Product", description = "Operations related to Product Controller ")
@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

	private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	@Autowired
	private ProductService productService;


	//@SuppressWarnings("null")
	//@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping({"/{productId}", "/product", ""})
    public ResponseEntity<Page<Product>> getProduct(
            @PathVariable(required = false) Optional<String> productId,
            @RequestParam(required = false, value = "startDate") Optional<String> start,
            @RequestParam(required = false, value = "endDate") Optional<String> end,
            @RequestParam(required = false, defaultValue = "20", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "0", value = "page") int page
    ) throws Exception {
		 Page<Product> products = null;
		 
		 ProductCriteria productCriteria=new ProductCriteria();
		 
		 productCriteria.setLimit(limit);
		 productCriteria.setPage(page);
		 products = productService.findProducts(productCriteria);
		 
		    if (products.isEmpty()) {
	            logger.info("Product is not found");
	            throw new ManualException(HttpStatus.NOT_FOUND, "Product is not found");
	        }

		 logger.info("Get Promotions successfully");
	        return ResponseEntity.status(HttpStatus.OK).body(products);
	}//
	
	

   // @RequestMapping(method=RequestMethod.POST, value="/create")
    @PostMapping({"", "/"})
    public ResponseEntity<String> saveProduct(Product product, @RequestParam("fileLogo") MultipartFile fileLogo,
    		@RequestParam("fileImage") MultipartFile fileImage,@RequestParam("fileVdo") MultipartFile fileVdo) throws Exception {

    	//logger.info("before getProductStartDate="+product.getProductStartDate());
    	//logger.info("before getProductEndDate="+product.getProductEndDate());
    	
    	 if (product.getProductEndDate().compareTo(product.getProductStartDate()) < 0) {
             logger.info("-startDate- is not before -endDate-");
             throw new ManualException(HttpStatus.BAD_REQUEST, "-Product StartDate- is not before -Product EndDate-");
         }
    	
    	product.setProductStartDate(DateTimeUtils.getUTCTime(product.getProductStartDate()));
    	product.setProductEndDate(DateTimeUtils.getUTCTime(product.getProductEndDate()));
    	
    	//logger.info("after getProductStartDate="+product.getProductStartDate());
    	//logger.info("after getProductEndDate="+product.getProductEndDate());
        
    	  Optional<Product> optional = productService.saveProduct(product, fileLogo,fileImage,fileVdo);
        
        if (optional.isPresent()) {
            logger.info("Success create Product with Id: " + optional.get().getProductId());
            return ResponseEntity.status(HttpStatus.OK).body("Success create promotion with id " + optional.get().getProductId());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Create Product Unknown Error.");
    }


    @RequestMapping(method=RequestMethod.PUT, value="/product/{productId}")
    public ResponseEntity<String> updateProduct( @PathVariable(required = true) String productId,Product product, @RequestParam("file") MultipartFile multipartFile) throws Exception {

    	logger.info("before getProductStartDate="+product.getProductStartDate());
    	logger.info("before getProductEndDate="+product.getProductEndDate());
    	
    	 if (product.getProductEndDate().compareTo(product.getProductStartDate()) < 0) {
             logger.info("-startDate- is not before -endDate-");
             throw new ManualException(HttpStatus.BAD_REQUEST, "-Product StartDate- is not before -Product EndDate-");
         }
    	
    	product.setProductStartDate(DateTimeUtils.getUTCTime(product.getProductStartDate()));
    	product.setProductEndDate(DateTimeUtils.getUTCTime(product.getProductEndDate()));
    	
    	logger.info("after getProductStartDate="+product.getProductStartDate());
    	logger.info("after getProductEndDate="+product.getProductEndDate());
    	
    	if(productId!=null) {
    		product.setProductId(productId);
    	}
    	
    	logger.info("productId="+product.getProductId());
        
    	  Optional<Product> optional = productService.updateProduct(product);
        
        if (optional.isPresent()) {
            logger.info("Success create Product with Id: " + optional.get().getProductId());
            return ResponseEntity.status(HttpStatus.OK).body("Success create promotion with id " + optional.get().getProductId());
        }

        logger.info("Success update promotion");
        return ResponseEntity.status(HttpStatus.OK).body("Success update Product");
    }
    
    @RequestMapping(method=RequestMethod.PUT, value="/product/status/{productId}")
    public ResponseEntity<String> updateProductStatus( @PathVariable(required = true) String productId,Product product) throws Exception {

    	logger.info("before getProductStartDate="+product.getProductStartDate());
    	logger.info("before getProductEndDate="+product.getProductEndDate());
    	
    	 if (product.getProductEndDate().compareTo(product.getProductStartDate()) < 0) {
             logger.info("-startDate- is not before -endDate-");
             throw new ManualException(HttpStatus.BAD_REQUEST, "-Product StartDate- is not before -Product EndDate-");
         }
    	
    	product.setProductStartDate(DateTimeUtils.getUTCTime(product.getProductStartDate()));
    	product.setProductEndDate(DateTimeUtils.getUTCTime(product.getProductEndDate()));
    	
    	logger.info("after getProductStartDate="+product.getProductStartDate());
    	logger.info("after getProductEndDate="+product.getProductEndDate());
    	
    	if(productId!=null) {
    		product.setProductId(productId);
    	}
    	
    	logger.info("productId="+product.getProductId());
        
    	  Optional<Product> optional = productService.updateProduct(product);
        
        if (optional.isPresent()) {
            logger.info("Success create Product with Id: " + optional.get().getProductId());
            return ResponseEntity.status(HttpStatus.OK).body("Success create promotion with id " + optional.get().getProductId());
        }

        logger.info("Success update promotion");
        return ResponseEntity.status(HttpStatus.OK).body("Success update Product");
    }


}
