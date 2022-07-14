package com.ps.entities.tenant;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "GroupCompany")
@Setter
@Getter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GroupCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupCompanyId;
	private String companyCode;
	private String companyName;
	private String shortName;	
	private String companyGroupCode;	
	private boolean isCompanyActive;
	private boolean isActive;
	
	public GroupCompany() {
		super();
		// TODO Auto-generated constructor stub
	}
}
