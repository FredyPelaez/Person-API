package com.esrx.person.service;

import com.esrx.person.model.Student;

public interface PersonService{
	
	//List<Student> findAllStudents();
	
	Student findById(long id);
	
	Student findByName(String name);
	
	Student addStudent(Student student);
	
	public boolean isStudentExist(Student student);
}
