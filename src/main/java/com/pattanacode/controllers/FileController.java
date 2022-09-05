/**
 * 
 */
package com.pattanacode.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author patismyname
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pattanacode.models.LoadFile;
import com.pattanacode.payload.response.UploadFileResponse;
import com.pattanacode.services.FileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

import java.io.IOException;

@Tag(name = "File", description = "Operations related File Controller")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/file/v1")
public class FileController {

	private final static Logger logger = LoggerFactory.getLogger(FileController.class);
	 
    @Autowired
    private FileService fileService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/uploadfile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile multipartFile) {
    	UploadFileResponse uploadFileResponse  = null;
    	logger.info("Start uploadFile File Size:"+multipartFile.getSize());
	    	if(multipartFile!=null ) {
	    		logger.info("uploadFile File name:"+multipartFile.getName());
	    	
	    	try {
	    		
	    	   uploadFileResponse  = fileService.uploadFile(multipartFile);
	    	   
	    	}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	}//if
    
        return uploadFileResponse;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/upload")
    public ResponseEntity<?> uploadMultipartFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        return new ResponseEntity<>(fileService.uploadMultipartFile(multipartFile), HttpStatus.OK);
    }
    

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        LoadFile loadFile = fileService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

}
