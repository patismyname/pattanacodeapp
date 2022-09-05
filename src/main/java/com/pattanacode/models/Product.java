package com.pattanacode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

@Document(collection = "products")
public class Product {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private String productId;
	private String productCode;
	private String productName;
	private String productTopic;
	private String productDesc;
	private Double productPrice;
	private Date productStartDate;
	private Date productEndDate;
	private String fileLogoId;
	private String fileImageId;
	private String fileVdoId;
	@DBRef
	private Category category;
	
	private String categoryName;
	
	
	private String statusType;
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	//private MultipartFile multipartFile;
	
	public Product() {
		this.productCode="";
		this.productName="";
		this.productDesc="";
		this.productPrice=0.00;
		this.fileLogoId="";
		this.fileImageId="";
		this.fileVdoId="";
		this.statusType="";
		this.categoryName="";
		//this.createdDate=new Date();
		//this.updatedDate=new Date();
	}
	

	
	

	/**
	 * @return the productTopic
	 */
	public String getProductTopic() {
		return productTopic;
	}





	/**
	 * @param productTopic the productTopic to set
	 */
	public void setProductTopic(String productTopic) {
		this.productTopic = productTopic;
	}





	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}





	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}





	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}





	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}




	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the productPrice
	 */
	public Double getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the productStartDate
	 */
	public Date getProductStartDate() {
		return productStartDate;
	}

	/**
	 * @param productStartDate the productStartDate to set
	 */
	public void setProductStartDate(Date productStartDate) {
		this.productStartDate = productStartDate;
	}

	/**
	 * @return the productEndDate
	 */
	public Date getProductEndDate() {
		return productEndDate;
	}

	/**
	 * @param productEndDate the productEndDate to set
	 */
	public void setProductEndDate(Date productEndDate) {
		this.productEndDate = productEndDate;
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
	 * @return the statusType
	 */
	public String getStatusType() {
		return statusType;
	}

	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(String statusType) {
		this.statusType = statusType;
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
	

	
}
