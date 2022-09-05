/**
 * 
 */
package com.pattanacode.models.criteria;

import java.util.Date;

/**
 * @author patismyname
 *
 */
public class BaseCriteria {

	 private Long longId;
	 private String name;
	
	 private Date startDate;
	 private Date endDate;
	 
	 private  String status;
	 private int page;
	 private int limit;

	/**
	 * 
	 */
	public BaseCriteria() {
		// TODO Auto-generated constructor stub
		longId=1L;
		name="";
		startDate=null;
		endDate=null;
		status="";
		page=0;
		limit=0;
		
		
	}

	/**
	 * @return the longId
	 */
	public Long getLongId() {
		return longId;
	}

	/**
	 * @param longId the longId to set
	 */
	public void setLongId(Long longId) {
		this.longId = longId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
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
