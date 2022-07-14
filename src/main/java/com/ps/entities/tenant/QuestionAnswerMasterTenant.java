package com.ps.entities.tenant;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "QuestionAnswerMaster")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QuestionAnswerMasterTenant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
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
	private String createdBy;
	private String lastModifiedBy;	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date createdDateTime;	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date lastModifiedDateTime;
	
	public QuestionAnswerMasterTenant() {
		super();
		// TODO Auto-generated constructor stub
	}
}
