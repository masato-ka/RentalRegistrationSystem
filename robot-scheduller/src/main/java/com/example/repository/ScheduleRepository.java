package com.example.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	@Query("SELECT x FROM Schedule x ORDER BY x.startDate")
	Page<Schedule> findAllOrderByName(Pageable pageable);
	
	@Query("SELECT x FROM Schedule x WHERE x.startDate <= :end AND x.endDate >= :start AND x.equipmentId = :robot")
	List<Schedule> findByDate(@Param("robot") Integer robotId, @Param("start") Timestamp start, @Param("end") Timestamp end); 
	
	@Query("SELECT x FROM Schedule x WHERE x.equipmentId = :equipmentId")
	Page<Schedule> findAllByEquipmentId(Pageable pageable, @Param("equipmentId") Integer equipmentId);
	
}
