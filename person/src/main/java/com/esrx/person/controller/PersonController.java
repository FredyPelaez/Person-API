package com.esrx.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esrx.person.model.Student;
import com.esrx.person.model.Teacher;
import com.esrx.person.service.PersonServiceImpl;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class PersonController {
	
	final static Logger logger = LogManager.getLogger(PersonController.class);
	
	@Autowired
	private PersonServiceImpl personService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> response = null;
		
		try {
			logger.info("--- Calling Person Service ---");
			response = personService.findAllStudents();
		}catch(Exception e){
			logger.error(e);
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<List<Student>> (response, HttpStatus.OK);
	}
	
	@GetMapping("/teachers")
	public ResponseEntity<List<Teacher>> getPersons() {
		List<Teacher> response = null;
		
		try {
			logger.info("--- Calling Person Service ---");
			response = personService.findAllTeachers();
		}catch(Exception e) {
			logger.error(e);
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<List<Teacher>> (response, HttpStatus.OK);
	}
	
	@GetMapping("/person")
	public ResponseEntity<Object> getStudent(@Valid @RequestParam( required = true, value = "type") String type, @Valid @RequestParam( required = false, value = "firstName") String firstName, @Valid @RequestParam( required = false, value = "lastName") String lastName, @Valid @RequestParam( required = false, value = "id" ) Long id) {
		Object response = null;
		logger.info("Searching person");
		
		try {
			logger.debug("validating request");
			if(StringUtils.isNotBlank(type)) {
				if(StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
					logger.info("--- Calling Person Service ---");
					response = personService.findByName(type, firstName, lastName);
				}
				if(id != null && response == null) {
					logger.info("--- Calling Person Service ---");
					response = personService.findById(type, id);
				}
			}
		}catch(Exception e) {
			logger.error(e);
			ResponseEntity.badRequest().build();
		}
		if (response == null){
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("/person/add")
	public ResponseEntity <Object> addPerson(@Valid @RequestBody Object obj, @Valid @RequestParam( required = true, value = "type") String type) {
		Object response = new Object();
		logger.info("Adding Person");
		
		try {
			logger.info("--- Calling Person Service ---");
			response = personService.addPerson(obj, type);
		}catch (Exception e) {
			logger.error("An exception has ocurred\n"+obj+"\n"+type);
			ResponseEntity.badRequest().build();	
		}
		if (response == null)
			ResponseEntity.badRequest().build();
		return new ResponseEntity<Object> (response, HttpStatus.CREATED);		
	}
	
	@PutMapping("person/modify")
	public ResponseEntity <Object> changePerson(@Valid @RequestBody Object obj, @Valid @RequestParam( required = true, value = "type") String type){
		Object response = new Object();
		logger.info("Modifying Person");
		
		try {
			logger.info("--- Calling Person Service ---");
			response = personService.updatePerson(obj, type);
		}catch(Exception e) {
			logger.error(e);
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Object> (response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("person/delete")
	public ResponseEntity <Object> deletePerson(@Valid @RequestBody Object obj, @Valid @RequestParam( required = true, value = "type") String type){
		logger.info("Deleting Person");
		
		try {
			logger.info("--- Calling Person Service ---");
			personService.deletePerson(obj, type);
		}catch(Exception e){
			logger.error(e);
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Object> (HttpStatus.ACCEPTED);
	}
}
