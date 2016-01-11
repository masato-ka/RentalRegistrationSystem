package com.example.domain;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
	
	@Id
	@GeneratedValue
	private Integer EquipmentId;
	@Column(nullable=false, unique=true)
	private String EquipmentName;
	@Column(nullable=false)
	private String EquipmentType;

}
