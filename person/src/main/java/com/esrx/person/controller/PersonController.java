package com.esrx.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esrx.person.model.Student;
import com.esrx.person.repository.*;
import com.esrx.person.service.PersonServiceImpl;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class PersonController {
	
	@Autowired
	private PersonServiceImpl personService;
	
	private PersonRepository repository = new PersonRepository();
	
	PersonController(PersonRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return repository.returnStudents();
	}
	
	@GetMapping("/student/{id}")
	public Student getStudent(@RequestParam(value = "id") Long id) {
		return personService.findById(id);
	}
	
	@PostMapping("/students/add")
	public ResponseEntity <Student> addStudent(@Valid @RequestBody Student student) {
		
		Student studentAdded = personService.addStudent(student);
		//repository.addStudent(student);
		return new ResponseEntity<Student> (studentAdded, HttpStatus.CREATED);
	}
	
	//@GetMapping("/students/add")
	//public Student addStudent() {
		//long id = repository.returnStudents().size() + 1;
		//Student student = new Student(id, "iou", "iu");
		//repository.addStudent(student);
		//return student;
	//}
}
