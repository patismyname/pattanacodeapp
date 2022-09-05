/**
 * 
 */
package com.pattanacode.controllers;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pattanacode.error.ManualException;
import com.pattanacode.models.Category;
import com.pattanacode.models.Product;
import com.pattanacode.models.criteria.BaseCriteria;
import com.pattanacode.repository.CategoryRepository;
import com.pattanacode.services.CategoryService;
import com.pattanacode.utils.DateTimeUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author patismyname
 *
 */
@Tag(name = "Cagetory", description = "Operations related to Cagetory Controller")
@RestController
@RequestMapping("/api/master/category/v1")
public class CategoryController {
	
	private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	 
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService;
	
	//@SuppressWarnings("null")
	//	@PreAuthorize("hasRole('ADMIN')")
	    @Operation(summary = "Create a new Category",
	            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
	            security = @SecurityRequirement(name = "bearerAuth"))
		@GetMapping({"/{categoryId}", "/category", ""})
	    public ResponseEntity<Page<Category>> getCategory(
	            @PathVariable(required = false) Optional<String> categoryId,
	            @RequestParam(required = false, defaultValue = "20", value = "limit") int limit,
	            @RequestParam(required = false, defaultValue = "0", value = "page") int page
	    ) throws Exception {
			 Page<Category> cPage = null;
			 
			 BaseCriteria baseCriteria=new BaseCriteria();
			 
			 baseCriteria.setLimit(limit);
			 baseCriteria.setPage(page);
			 cPage = categoryService.findCategory(baseCriteria);
			 
			    if (cPage.isEmpty()) {
		            logger.info("Category is not found");
		            throw new ManualException(HttpStatus.NOT_FOUND, "Category is not found");
		        }

			 logger.info("Get Category successfully");
		        return ResponseEntity.status(HttpStatus.OK).body(cPage);
		}//

	    @Operation(summary = "Create a new Category",
	            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
	            security = @SecurityRequirement(name = "bearerAuth"))
		//@GetMapping({"/{categoryId}", "/listcategory", ""})
	    @RequestMapping(method=RequestMethod.GET, value="/listcategory")
	    public ResponseEntity<List<Category>> readCategory(
	            @PathVariable(required = false) Optional<String> categoryId
	    ) throws Exception {
			
			 List<Category> lCategories = categoryService.readCategory();
			 
			    if (lCategories.size()==0) {
		            logger.info("Category is not found");
		            throw new ManualException(HttpStatus.NOT_FOUND, "Category is not found");
		        }//if

			 logger.info("Get Category successfully");
		        return ResponseEntity.status(HttpStatus.OK).body(lCategories);
		}//

	 
	    
	//@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new Category",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    //@RequestMapping(method=RequestMethod.POST, value="")
	/*
	 * @PostMapping({"", "/"}) public ResponseEntity<String>
	 * createCategory(@RequestBody(required = true) Category category) throws
	 * Exception{
	 * 
	 * category.setCreatedDate(DateTimeUtils.getSystemDate());
	 * category.setUpdatedDate(DateTimeUtils.getSystemDate());
	 * category.setCreatedBy("System"); Optional<Category> optional
	 * =Optional.of(categoryRepository.save(category)); if (optional.isPresent()) {
	 * logger.info("Success create Category with Id: " +
	 * optional.get().getCatetoryId()); return
	 * ResponseEntity.status(HttpStatus.OK).body("Success create Category with Id: "
	 * + optional.get().getCatetoryId()); }
	 * 
	 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	 * body("Create Category Unknown Error."); // return
	 * "Category saved id="+category.getCategoryName()+" is Success."; }
	 */
    @PostMapping({"", "/"})
    public ResponseEntity<String> uploadCategory(Category category,@RequestParam("fileLogo") MultipartFile fileLogo,@RequestParam("fileImage") MultipartFile fileImage,@RequestParam("fileVdo") MultipartFile fileVdo) throws Exception{

    	category.setStatusFlag("A");// A is Active
    	category.setCreatedDate(DateTimeUtils.getSystemDate());
    	category.setUpdatedDate(DateTimeUtils.getSystemDate());
    	category.setCreatedBy("System");
    	//Optional<Category> optional =Optional.of(categoryRepository.save(category));
    	 
    	Optional<Category> optional = categoryService.saveCategory(category, fileLogo, fileImage, fileVdo);
    	 
    	 if (optional.isPresent()) {
             logger.info("Success create Category with Id: " + optional.get().getCatetoryId());
             return ResponseEntity.status(HttpStatus.OK).body("Success create Category Name : " + optional.get().getCategoryName());
         }

         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Create Category Unknown Error.");
       // return "Category saved id="+category.getCategoryName()+" is Success.";
    }


}
