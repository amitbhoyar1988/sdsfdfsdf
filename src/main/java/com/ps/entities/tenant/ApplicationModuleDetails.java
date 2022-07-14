package com.ps.entities.tenant;

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
@Table(name = "ApplicationModuleDetails")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ApplicationModuleDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int applicationModuleId;	
	private String applicationModuleName;	
	private boolean isActive;
	
	public ApplicationModuleDetails() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
