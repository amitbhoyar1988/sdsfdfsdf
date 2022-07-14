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
import javax.persistence.OneToMany;
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
@Table(name = "QueryGenerationEmpIterations")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QueryGenerationEmpIterations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int queryIterationId;
		
	@ManyToOne
	@JoinColumn(name="queryGenerationEmpId",referencedColumnName = "queryGenerationEmpId")
	private QueryGenerationEmployee queryGenerationEmployee;
	
	private int refNumber;	
	private String status;	
	
//	@OneToMany
//	@JoinColumn(name="queAnsMasterId",referencedColumnName = "queAnsMasterId")
//	private QuestionAnswerMasterTenant questionAnswerMaster;
	
	private int queAnsMasterId;
	private String queryDescription;
	private boolean isActive;
	private String createdBy;
	private String lastModifiedBy;	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "createDateTime")
	private Date createdDateTime;	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date lastModifiedDateTime;
	
	public QueryGenerationEmpIterations() {
		super();
		// TODO Auto-generated constructor stub
	}
}
