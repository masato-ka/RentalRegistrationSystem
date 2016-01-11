package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.domain.Equipment;
import com.example.domain.Schedule;
import com.example.repository.EquipmentRepository;
import com.example.repository.ScheduleRepository;

@Service
public class ScheduleService {
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	EquipmentRepository equipmentRepository;
	
	public Page<Schedule> findAll(Pageable pageable){
		return scheduleRepository.findAllOrderByName(pageable);
	}
	
	public Schedule findOne(Integer id){
		return scheduleRepository.findOne(id);
	}
	
	public Schedule createSchedule(Schedule schedule){
		Equipment equipment = equipmentRepository.findOne(schedule.getEquipmentId()); 
		
		if(equipment == null){
			return null;
		}
		
		List<Schedule> check = scheduleRepository.findByDate(schedule.getEquipmentId(),
				schedule.getStartDate(),
				schedule.getEndDate());
		
		if(check.size() > 0){
			return null;
		}
		
		return scheduleRepository.save(schedule);
	}
	
	public Schedule updateSchedule(Schedule schedule){
		return scheduleRepository.save(schedule);
	}
	
	public void deleteSchedule(Integer id){
		scheduleRepository.delete(id);
	}

	public Page<Schedule> findAllByEquipmentName(Pageable pageable, String name) {
		Equipment equipment = equipmentRepository.findOneByEquipmentName(name);
		if(equipment==null){
			return null;
		}
		Page<Schedule> schedule = scheduleRepository.findAllByEquipmentId(pageable, equipment.getEquipmentId());
		return schedule;
	}
	
}
