package com.esrx.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.esrx.person.service.PersonServiceImpl;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class PersonController {
	
	@Autowired
	private PersonServiceImpl personService;
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		return personService.findAllStudents();
	}
	
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent(@Valid @RequestParam( required = false, value = "id" ) Long id, @Valid @RequestParam( required =  false, value = "firstName") String firstName) {
		Student student = null;
		if(id >= 0 && id != null) {
			student = personService.findById(id);
		}
		if(StringUtils.isNotBlank(firstName) && student == null) {
			student = personService.findByName(firstName);
		}
		else {
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@GetMapping("/student/name")
	public ResponseEntity<Student> getStudent(@Valid @RequestParam( required =  false, value = "firstName") String firstName) {
		Student student = null;
		if(StringUtils.isNotBlank(firstName)) {
			student = personService.findByName(firstName);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@PostMapping("/student/add")
	public ResponseEntity <Student> addStudent(@Valid @RequestBody Student student) {
		if(StringUtils.isBlank(student.getFirstName()) || StringUtils.isBlank(student.getLastName())) {
			ResponseEntity.badRequest().build();	
		}
		Student studentAdded = personService.addStudent(student);
		return new ResponseEntity<Student> (studentAdded, HttpStatus.CREATED);
	}
	
	@PutMapping("student/modify")
	public ResponseEntity <Student> changeStudent(@Valid @RequestBody Student student){
		if(StringUtils.isBlank(student.getFirstName()) || StringUtils.isBlank(student.getFirstName())) {
			ResponseEntity.badRequest().build();
		}
		Student studentChanged = personService.updateStudent(student);
		return new ResponseEntity<Student> (studentChanged, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("student/delete")
	public ResponseEntity <Student> deleteStudent(@Valid @RequestBody Student student){
		personService.deleteStudent(student);
		return new ResponseEntity<Student> (HttpStatus.ACCEPTED);
	}
}
