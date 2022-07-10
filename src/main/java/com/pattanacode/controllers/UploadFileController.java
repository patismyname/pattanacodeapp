/**
 * 
 */
package com.pattanacode.controllers;

import java.io.IOException;
import java.util.Date;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
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
import com.pattanacode.models.File;

/**
 * @author patismyname
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/file")
public class UploadFileController {

	/**
	 * 
	 */
	public UploadFileController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private MongoTemplate mongoTemplate;

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
	                .path("/api/v1/file/image/")
	                .path(savedFile.getId())
	                 .toUriString();

	        jsonResult =   new JSONResult (200, "image uploaded successfully", fileDownloadUri);
	    } catch (IOException e) {
	        e.printStackTrace();
	        jsonResult =   new JSONResult (500, "image upload failed", null);
	    }
	    return jsonResult;

	}
	
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

