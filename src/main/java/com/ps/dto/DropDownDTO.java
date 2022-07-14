package com.ps.dto;
/**
 * @author MayurG: This Class will refers to the DropDown DTO.
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class DropDownDTO {

	private int positionDetailDDId;

	private String fieldName;
	private String value;
	private String category;
	private String description;
	private String createdBy;

	private Date createDateTime;
	private int isActive;

	public DropDownDTO() {
		super();
	}

	public int getPositionDetailDDId() {
		return positionDetailDDId;
	}

	public void setPositionDetailDDId(int positionDetailDDId) {
		this.positionDetailDDId = positionDetailDDId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
