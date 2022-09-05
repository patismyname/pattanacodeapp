package com.pattanacode.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private String catetoryId;
	private String categoryName;
	private String fileLogoId;
	private String fileImageId;
	private String fileVdoId;
    private  String statusFlag;//A,I
    private  String createdBy;
    private  Date createdDate;
    private String updatedBy;
    private  Date updatedDate;

	public Category() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @return the catetoryId
	 */
	public String getCatetoryId() {
		return catetoryId;
	}

	/**
	 * @param catetoryId the catetoryId to set
	 */
	public void setCatetoryId(String catetoryId) {
		this.catetoryId = catetoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	 * @return the fileLogoId
	 */
	public String getFileLogoId() {
		return fileLogoId;
	}

	/**
	 * @param fileLogoId the fileLogoId to set
	 */
	public void setFileLogoId(String fileLogoId) {
		this.fileLogoId = fileLogoId;
	}

	/**
	 * @return the fileImageId
	 */
	public String getFileImageId() {
		return fileImageId;
	}

	/**
	 * @param fileImageId the fileImageId to set
	 */
	public void setFileImageId(String fileImageId) {
		this.fileImageId = fileImageId;
	}

	/**
	 * @return the fileVdoId
	 */
	public String getFileVdoId() {
		return fileVdoId;
	}

	/**
	 * @param fileVdoId the fileVdoId to set
	 */
	public void setFileVdoId(String fileVdoId) {
		this.fileVdoId = fileVdoId;
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
