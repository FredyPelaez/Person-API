package com.esrx.person.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.esrx.person.model.Student;

import lombok.Data;

@Data
@Component
public class PersonRepository {
	
	List <Student> listStudent = new ArrayList<Student>();
	
	public List <Student> returnStudents(){
		return listStudent;
	}
}