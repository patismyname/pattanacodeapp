/**
 * 
 */
package com.pattanacode.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pattanacode.models.Category;
import com.pattanacode.models.Product;
import com.pattanacode.models.criteria.BaseCriteria;
import com.pattanacode.repository.CategoryRepository;
import com.pattanacode.utils.DateTimeUtils;

/**
 * @author patismyname
 *
 */
@Service
@Transactional
public class CategoryService {

	private final static Logger logger = LoggerFactory.getLogger(CategoryService.class);
	 
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	FileUploadTransactionService fileUploadTransactionService;

	 public Page<Category> findCategory(BaseCriteria baseCriteria) {
			PageRequest pageRequest = genPageRequest(baseCriteria.getPage(), baseCriteria.getLimit());
			Page<Category> pageCategory=categoryRepository.findAll(
					pageRequest);
			return  pageCategory;
		}
		private PageRequest genPageRequest(int page, int limit) {
			return PageRequest.of(page,limit);	        
		}
		 public List<Category> readCategory() {
			 
			 List<Category> lCategories=categoryRepository.findAll();
			 return lCategories;
		
			}

	
	public Optional<Category> createCategory(Category category)throws Exception{
		
		if(category!=null) {
			return Optional.of(categoryRepository.save(category));
		}else {
			return Optional.empty();
			
		}//if
		
	}// createCategory
	
 public Optional<Category> saveCategory(Category category,MultipartFile fileLogo, MultipartFile fileImage,
			MultipartFile fileVdo)throws Exception{
	    String fileLogoId = "";
		String fileImageId = "";
		String fileVdoId = "";

		// Upload file Logo
		if (fileLogo != null) {
			fileLogoId = fileService.uploadMultipartFile(fileLogo);
		} // if

		// Upload file Image
		if (fileImage != null) {
			fileImageId = fileService.uploadMultipartFile(fileImage);
		} // if

		// Upload file Video
		if (fileVdo != null) {
			fileVdoId = fileService.uploadMultipartFile(fileVdo);
		} // if

		//MultipartFile multipartFile []=null;
		//ArrayList<MultipartFile> arrayList = new ArrayList<MultipartFile>(Arrays.asList(multipartFile));  
		ArrayList<String> fileIds = new ArrayList<String>();
		fileIds.add(fileLogoId);  
		fileIds.add(fileImageId);  
		fileIds.add(fileVdoId);  
		logger.info("fileIds.size()=>"+fileIds.size());
		
		
		
		// create new one
		if (category.getCatetoryId() == null) {

			// logger.info("ProductService#saveProduct#fileLogoId="+fileLogoId);
			category.setFileLogoId(fileLogoId);
			// logger.info("ProductService#saveProduct#fileImageId="+fileImageId);
			category.setFileImageId(fileImageId);
			// logger.info("ProductService#saveProduct#fileVdoId="+fileVdoId);
			category.setFileVdoId(fileVdoId);

			category.setCreatedDate(DateTimeUtils.getSystemDate());
			category.setUpdatedDate(DateTimeUtils.getSystemDate());

			category.setCreatedBy("System");
			category.setUpdatedBy("System");

			//return Optional.of(categoryRepository.save(category));
			
			Optional<Category> cOptional=Optional.of(categoryRepository.save(category));
			
			if (cOptional != null) {
				
				fileUploadTransactionService.createFileUploadTransaction(cOptional.get().getCatetoryId(), fileIds);
			}//if
			return cOptional;
			
		} else {

			// update by ProductId
			category.setFileLogoId(fileLogoId);
			category.setFileImageId(fileImageId);
			category.setFileVdoId(fileVdoId);
			// logger.info("product.getProductId()="+product.getProductId());
			// logger.info("product.getFileId()="+product.getFileId());
			// if(!productRepository.findById(product.getProductId()).isPresent()) {
			Optional<Category> cOptional = categoryRepository.findById(category.getCatetoryId());
			if (cOptional != null) {
				// Delete file uploaded
				fileService.deleteFile(cOptional.get().getFileLogoId());
				fileService.deleteFile(cOptional.get().getFileImageId());
				fileService.deleteFile(cOptional.get().getFileVdoId());
				// Update product
				return Optional.of(categoryRepository.save(category));
				
				
				//Optional<Category> cOptional=Optional.of(categoryRepository.save(category));
				
				//if (cOptional != null) {
					
					
				//}//if
				//return cOptional;
				
			}//if
			
			
			
		}//if
		
		return Optional.empty();
		
	}// createCategory


}//end class
