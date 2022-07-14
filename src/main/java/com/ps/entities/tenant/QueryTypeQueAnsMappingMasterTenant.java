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
@Table(name = "QueryTypeQueAnsMappingMaster")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QueryTypeQueAnsMappingMasterTenant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int queryTypeQueAnsMappingId;
	private int queryTypeMasterId;
	private int queAnsMasterId;
	private boolean isActive;
	private String createdBy;
	private String lastModifiedBy;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date createdDateTime;	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)	
	private Date lastModifiedDateTime;
	
	public QueryTypeQueAnsMappingMasterTenant() {
		super();
		// TODO Auto-generated constructor stub
	}
}
