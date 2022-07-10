package com.pattanacode.services;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

import com.pattanacode.models.LoadFile;
import com.pattanacode.payload.response.UploadFileResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
public class FileService {

	private final static Logger logger = LoggerFactory.getLogger(FileService.class);
	
    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    
    public UploadFileResponse uploadFile(MultipartFile multipartFile) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", multipartFile.getSize());
        
        UploadFileResponse uploadFileResponse =null;

        Object fileId = template.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), metadata);
       
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/download/")
                .path(fileId.toString())
                .toUriString();
        uploadFileResponse=new UploadFileResponse(multipartFile.getOriginalFilename(), fileDownloadUri, multipartFile.getContentType(), multipartFile.getSize());
        
        return uploadFileResponse;
    }
    
    public String uploadMultipartFile(MultipartFile multipartFile) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", multipartFile.getSize());

        Object fileID = template.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), metadata);

        logger.info("FileService#uploadMultipartFile#fileID="+fileID);
        return fileID.toString();
    }
    

    public LoadFile downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );
       

        LoadFile loadFile = new LoadFile();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }

        return loadFile;
    }
    
    public void deleteFile(String id) throws IOException {

        
        logger.info("Delete file id:"+id);
        template.delete(new Query(Criteria.where("_id").is(id)));

       
    }

}