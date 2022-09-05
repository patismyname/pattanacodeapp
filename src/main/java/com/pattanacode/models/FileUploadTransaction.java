/**
 * 
 */
package com.pattanacode.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author patismyname
 *
 */
@Document(collection = "fileuploadtransaction")
public class FileUploadTransaction {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private String fileuploadtransactionId;
	
	private String filesId;
	private String mappingId;
    private  String statusFlag;//A,I
    private  String createdBy;
    private  Date createdDate;
    private String updatedBy;
    private  Date updatedDate;
    
	/**
	 * 
	 */
	public FileUploadTransaction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fileuploadtransactionId
	 */
	public String getFileuploadtransactionId() {
		return fileuploadtransactionId;
	}

	/**
	 * @param fileuploadtransactionId the fileuploadtransactionId to set
	 */
	public void setFileuploadtransactionId(String fileuploadtransactionId) {
		this.fileuploadtransactionId = fileuploadtransactionId;
	}

	/**
	 * @return the filesId
	 */
	public String getFilesId() {
		return filesId;
	}

	/**
	 * @param filesId the filesId to set
	 */
	public void setFilesId(String filesId) {
		this.filesId = filesId;
	}

	/**
	 * @return the mappingId
	 */
	public String getMappingId() {
		return mappingId;
	}

	/**
	 * @param mappingId the mappingId to set
	 */
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}

	/**
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}
