package com.ps.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QuestionAnswerMasterDTO extends AbstractDTO {
	private int queAnsMasterId;	
	private int code;	
	private String description;	
	private int moduleId;	
	private String questionSubject;
	private String questionDescription;
	private String answerSubject;
	private String answerDescription;
	private String remark;
	private boolean isActive;
	
	public QuestionAnswerMasterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
