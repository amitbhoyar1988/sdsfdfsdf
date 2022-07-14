package com.ps.entities.tenant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "QueryGenerationEmployee")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QueryGenerationEmployee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int queryGenerationEmpId;	
	private int queryNumber;
	private Date submissionDate;
	
	@ManyToOne
	@JoinColumn(name="employeeMasterId",referencedColumnName = "employeeMasterId")
	private EmployeeMaster employeeMaster;
	private boolean onBehalfOfEmployee;
	@ManyToOne
	@JoinColumn(name="applicationModuleId",referencedColumnName = "applicationModuleId")
	private ApplicationModuleDetails applicationModuleDetails;	
	
	@ManyToOne
	@JoinColumn(name="queryTypeMasterId",referencedColumnName = "queryTypeMasterId")
	private QueryTypeMasterTenant queryTypeMasterTenant;	
	
	private int subQueTypeMasterId;	
	private String priority;		
	private Date escalationDate;
	private String subject;
	private String queryRootCause;	
	private String status;	
	private boolean isActive;
	private String createdBy;
	private String lastModifiedBy;	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date lastModifiedDateTime;
	
	public QueryGenerationEmployee() {
		super();
	}
}
