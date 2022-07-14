package com.ps.entities.master;

import java.util.Date;

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
@Table(name = "QueryTypeQueAnsMappingMaster")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QueryTypeQueAnsMappingMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int queryTypeQueAnsMappingId;
	//private int queryTypeMasterId;
	@ManyToOne
	@JoinColumn(name="queryTypeMasterId",referencedColumnName = "queryTypeMasterId")
	private QueryTypeMaster queryTypeMaster;
	
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
	
	public QueryTypeQueAnsMappingMaster() {
		super();
		
	}
	
}
