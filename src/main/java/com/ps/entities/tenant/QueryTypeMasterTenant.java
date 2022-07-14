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
@Table(name = "QueryTypeMaster")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QueryTypeMasterTenant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int queryTypeMasterId;
	private int applicationModuleId;
	private String queryTypeCode;
	private String queryTypeDescription;
	private boolean subQuery;
	private boolean priorityRequired;
	private String resolutionTimeForNopriority;
	private String autoCloseTimeforNopriority;	
	private int replyWorkflowId;
	private int forwardWorkflowId;
	private String Remark;
	private String createdBy;
	private String lastModifiedBy;
	private boolean isActive;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date createdDateTime;	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date lastModifiedDateTime;
	
	public QueryTypeMasterTenant() {
		super();
		// TODO Auto-generated constructor stub
	}
}
