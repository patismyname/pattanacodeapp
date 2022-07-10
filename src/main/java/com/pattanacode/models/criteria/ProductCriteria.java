/**
 * 
 */
package com.pattanacode.models.criteria;

import java.util.Date;

/**
 * @author patismyname
 *
 */
public class ProductCriteria {

	 private Long productId;
	 private String productCode;
	 private String productName;
	
	 private Date productStartDate;
	 private Date productEndDate;
	 
	 private  int status;
	 private int page;
	 private int limit;

	 
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	 
	 
    
}
