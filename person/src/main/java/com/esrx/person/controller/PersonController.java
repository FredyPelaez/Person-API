package com.esrx.person.controller;

import java.util.List;

import javax.validation.Valid;

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
	public ResponseEntity<Student> getStudent(@Valid @RequestParam(value = "id") Long id) {
		Student student = personService.findById(id);
		if(student == null) {
			ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@PostMapping("/student/add")
	public ResponseEntity <Student> addStudent(@Valid @RequestBody Student student) {
		if(student.getFirstName() == null || student.getLastName() == null ) {
			ResponseEntity.badRequest().build();	
		}
		Student studentAdded = personService.addStudent(student);
		return new ResponseEntity<Student> (studentAdded, HttpStatus.CREATED);
	}
	
	@PutMapping("student/modify")
	public ResponseEntity <Student> changeStudent(@Valid @RequestBody Student student){
		Student studentChanged = personService.updateStudent(student);
		return new ResponseEntity<Student> (studentChanged, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("student/delete")
	public ResponseEntity <Student> deleteStudent(@Valid @RequestBody Student student){
		personService.deleteStudent(student);
		return new ResponseEntity<Student> (student, HttpStatus.ACCEPTED);
	}
}
