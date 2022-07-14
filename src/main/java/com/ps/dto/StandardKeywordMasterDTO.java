package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class StandardKeywordMasterDTO extends AbstractDTO {
	private int stndKeywordMasterId;	
	private int keywordTableMasterId;	
	private String dbFieldName;	
	private String displayName;
	private int dateFormatMasterId;	
	private boolean isActive;
	
	public StandardKeywordMasterDTO() {
		super();
	}
}
