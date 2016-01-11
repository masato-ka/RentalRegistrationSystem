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
@Table(name= "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private Integer equipmentId;
	@Column(nullable = true)
	private Timestamp startDate;
	@Column(nullable = true)
	private Timestamp endDate;
	@Column(nullable = false)
	private String demoName;
	@Column(nullable = false)
	private String demoOwner;
	@Column(nullable = true)
	private String demoDescript;
}
