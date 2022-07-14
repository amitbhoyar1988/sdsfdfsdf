package com.ps.dto;

import java.util.Date;

/*
* @author MayurG This class refers to the Fields which are commonly uses for Masters DTO.
* 
*/

public class MastersCommonPropertiesDTO {

	private String createdBy;
	private Date createDateTime;

	private String lastModifiedBy;
	private Date lastModifiedDateTime;
	private int isActive;

	public MastersCommonPropertiesDTO() {
		super();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public MastersCommonPropertiesDTO(String createdBy, Date createDateTime, String lastModifiedBy,
			Date lastModifiedDateTime, int isActive) {
		super();
		this.createdBy = createdBy;
		this.createDateTime = createDateTime;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDateTime = lastModifiedDateTime;
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "MastersCommonPropertiesDTO [createdBy=" + createdBy + ", createDateTime=" + createDateTime
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDateTime=" + lastModifiedDateTime
				+ ", isActive=" + isActive + "]";
	}

}// MastersCommonPropertiesDTO
