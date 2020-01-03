package com.esrx.person.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.esrx.person.model.Student;

//@RestController
@Controller
@RequestMapping(value = "/", produces = "application/json")
public class PersonController {

	@GetMapping("/students")
	public List<Student> retrieveStudent(@RequestParam(name = "nombre") String nombre) {
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("iou", "iu"));
		return studentList;
	}
	
	//@GetMapping("/students")
	@RequestMapping(method = RequestMethod.POST)
	public List<Student> createStudent() {
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("iou", "iu"));
		return studentList;
	}
	
}
