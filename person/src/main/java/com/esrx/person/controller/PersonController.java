package com.esrx.person.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esrx.person.model.Student;


@RestController
@RequestMapping(value = "/", produces = "application/json")
public class PersonController{
	
	//@GetMapping("/students")
	//public List<Student> getStudent(){
	//	List<Student> studentList = new ArrayList<Student>();
	//	studentList.add(new Student("",""));
	//	return studentList;
	//}
}
