package com.ps.entities.master;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;
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
public class QueryTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int queryTypeMasterId;
	
	private int applicationModuleId;
	private String queryTypeCode;
	
	private String queryTypedescription;
	//private int queAnsMasterId;
	private boolean subQuery;
	private boolean priorityRequired;
	
	private String autoCloseTimeforNopriority;
	private String resolutionTimeforNopriority;
	private int replyWorkflowId;
	private int forwardWorkFlowId;
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

	public QueryTypeMaster() {
		super();
		
	}
	

}
