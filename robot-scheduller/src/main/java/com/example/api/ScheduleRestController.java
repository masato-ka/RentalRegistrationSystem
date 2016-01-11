package com.example.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Schedule;
import com.example.service.ScheduleService;

@RestController
@RequestMapping("api/schedule")
public class ScheduleRestController {

	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping(method=RequestMethod.GET)
	Page<Schedule> getSchedule(@PageableDefault Pageable pageable){
		Page<Schedule> schedules = scheduleService.findAll(pageable);
		return schedules;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	Schedule getSchedule(@PathVariable Integer id){
		Schedule schedule = scheduleService.findOne(id);
		return schedule;
	}
	
	@RequestMapping(value="equipment",method=RequestMethod.GET)
	Page<Schedule> getScheduleByEquipmentName(@PageableDefault Pageable pageable, @RequestParam String name){
		Page<Schedule> schedules = scheduleService.findAllByEquipmentName(pageable, name);
		return schedules;
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<Schedule> postSchedule(@RequestBody Schedule schedule, UriComponentsBuilder uriBuilder){
		Schedule created = scheduleService.createSchedule(schedule);
		if(created == null){
			return new ResponseEntity<>(null,null,HttpStatus.CONFLICT);
		}
		URI location = uriBuilder.path("api/schedule/{id}")
				.buildAndExpand(created.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	Schedule editSchedule(@PathVariable Integer id, @RequestBody Schedule schedule){
		schedule.setId(id);
		return scheduleService.updateSchedule(schedule);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteSchedule(@PathVariable Integer id){
		scheduleService.deleteSchedule(id);
	}
}
