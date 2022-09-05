/**
 * 
 */
package com.pattanacode.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pattanacode.models.FileUploadTransaction;
import com.pattanacode.repository.FileUploadTransactionRepository;
import com.pattanacode.utils.DateTimeUtils;

/**
 * @author patismyname
 *
 */
@Service
public class FileUploadTransactionService {
	
	private final static Logger logger = LoggerFactory.getLogger(FileUploadTransactionService.class);
	
	@Autowired
    FileService fileService;
	
	@Autowired
	FileUploadTransactionRepository fileUploadTransactionRepository;

	/**
	 * 
	 */
	public FileUploadTransactionService() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param mappingId
	 * @param files
	 * @throws Exception
	 */
	public void createFileUploadTransaction(String mappingId,ArrayList<String> fileIds) throws Exception{
		FileUploadTransaction fileUploadTransaction=null;
		if(mappingId!=null && fileIds.size()>0) {
		    
			logger.info("fileIds.size()=>"+fileIds.size());
			for(int i=0;i<fileIds.size();i++) {
			fileUploadTransaction=new FileUploadTransaction();
			String strFileId=	(String)fileIds.get(i);
			logger.info("createFileUploadTransaction strFileId="+strFileId);
			fileUploadTransaction.setFilesId(strFileId);
			fileUploadTransaction.setMappingId(mappingId);
			fileUploadTransaction.setCreatedBy("System");
			fileUploadTransaction.setCreatedDate(DateTimeUtils.getSystemDate());
			fileUploadTransaction.setUpdatedBy("System");
			fileUploadTransaction.setUpdatedDate(DateTimeUtils.getSystemDate());
			fileUploadTransactionRepository.save(fileUploadTransaction);
			
			}//for
			
		}//if
		
	}

}
