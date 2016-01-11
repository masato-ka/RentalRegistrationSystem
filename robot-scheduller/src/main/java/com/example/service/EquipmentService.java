package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.domain.Equipment;
import com.example.repository.EquipmentRepository;

@Service
public class EquipmentService {

	@Autowired
	EquipmentRepository equipmentRepository;
	
	public Page<Equipment> findAllByEquipmentType(Pageable pageable, String equipmentType){
		return equipmentRepository.findAllByEquipmentType(pageable, equipmentType);
	}
	
	public Equipment findOnebyName(String equipmentName){
		return equipmentRepository.findOneByEquipmentName(equipmentName);
	}
	
	public Equipment findOne(Integer id){
		return equipmentRepository.findOne(id);
	}
	
	public Equipment createEquipment(Equipment equipment){
		return equipmentRepository.save(equipment);
	}
	
	public Equipment updateEquipment(Equipment equipment){
		return equipmentRepository.save(equipment);
	}
	
	public void deleteEquipment(Integer id){
		equipmentRepository.delete(id);
	}
	
	
}
