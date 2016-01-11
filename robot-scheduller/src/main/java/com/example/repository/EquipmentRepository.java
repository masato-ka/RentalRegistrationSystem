package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
	
	@Query("SELECT x FROM Equipment x WHERE x.EquipmentType = :typeName")
	Page<Equipment> findAllByEquipmentType(Pageable pageable, @Param("typeName") String equipmentType);

	@Query("SELECT x FROM Equipment x WHERE x.EquipmentName = :equipmentName")
	Equipment findOneByEquipmentName(@Param("equipmentName") String equipmentName);
}
