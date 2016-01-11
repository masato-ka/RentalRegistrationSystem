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

import com.example.domain.Equipment;
import com.example.domain.Schedule;
import com.example.service.EquipmentService;
import com.example.service.ScheduleService;

@RestController
@RequestMapping("api/equipment")
public class EquipmetnRestController {

	@Autowired
	EquipmentService equipmentService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	Equipment getEquipment(@PathVariable Integer id){
		Equipment equipment = equipmentService.findOne(id);
		return equipment;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	Page<Equipment> getEquipment(@PageableDefault Pageable pageable, @RequestParam String typeName){
		Page<Equipment> equipment = equipmentService.findAllByEquipmentType(pageable, typeName);
		return equipment;
	}
	
	@RequestMapping(value="name/{equipmentName}", method=RequestMethod.GET)
	Equipment getEquipment(@PathVariable String equipmentName){
		Equipment equipment = equipmentService.findOnebyName(equipmentName);
		return equipment;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<Equipment> postEquipment(@RequestBody Equipment equipment, UriComponentsBuilder uriBuilder){
		
		Equipment created = equipmentService.createEquipment(equipment);
		URI location = uriBuilder.path("api/schedule/{id}")
				.buildAndExpand(created.getEquipmentId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	Equipment editEquipment(@PathVariable Integer id, @RequestBody Equipment equipment){
		equipment.setEquipmentId(id);
		Equipment updated = equipmentService.createEquipment(equipment);
		return updated;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteEquipment(@PathVariable Integer id){
		equipmentService.deleteEquipment(id);
	}
	
	
}
