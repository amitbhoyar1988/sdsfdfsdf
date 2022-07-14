package com.ps.entities.tenant;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EmployeeMaster")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EmployeeMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int employeeMasterId;	
	
	@ManyToOne
	@JoinColumn(name="groupCompanyId",referencedColumnName = "groupCompanyId")
	private GroupCompany groupCompany;
	
//	private int groupCompanyId;	
	private String employeeCode;	
	private String firstName;	
	private String lastName;
	private String fullName;
	private boolean isActive;
	
	public EmployeeMaster() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
