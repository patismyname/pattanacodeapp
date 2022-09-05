/**
 * 
 */
package com.pattanacode.controllers;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

import java.io.IOException;
import java.util.Date;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pattanacode.models.JSONResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.pattanacode.models.File;

/**
 * @author patismyname
 *
 */
@Tag(name = "UploadFileController", description = "Operations related to Upload File Controller")
@RestController
//@CrossOrigin("*")
@RequestMapping("/api/file/v1")
public class UploadFileController {

	/**
	 * 
	 */
	public UploadFileController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/uploadimage")
	@ResponseBody
	public JSONResult uploadImage(@RequestParam(value = "image") MultipartFile file){
	    if(file.isEmpty())
	            return  new JSONResult (200, "please select a picture", "");

	    //This class can encapsulate itself
	    JSONResult jsonResult = null;
	    String fileName = file.getOriginalFilename();
	    try {
	        File uploadFile = new File();
	        uploadFile.setFilename(fileName);
	        uploadFile.setCreatedTime(new Date());
	        uploadFile.setContent(new Binary(file.getBytes()));
	        uploadFile.setContentType(file.getContentType());
	        uploadFile.setSize(file.getSize());

	        File savedFile = mongoTemplate.save(uploadFile);
	       // String url = "http://localhost:8080/file/image/"+ savedFile.getId();

	         String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/api/file/v1/image/")
	                .path(savedFile.getId())
	                 .toUriString();

	        jsonResult =   new JSONResult (200, "image uploaded successfully", fileDownloadUri);
	    } catch (IOException e) {
	        e.printStackTrace();
	        jsonResult =   new JSONResult (500, "image upload failed", null);
	    }
	    return jsonResult;

	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new dentist",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "JWT token required", required = true),
            security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@ResponseBody
	public byte[] image(@PathVariable String id){
	    byte[] data = null;
	    File file = mongoTemplate.findById(id, File.class);
	    if(file != null){
	        data = file.getContent().getData();
	    }
	    return data;
	}

}

