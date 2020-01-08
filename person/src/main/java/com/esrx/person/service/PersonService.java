package com.esrx.person.service;

import com.esrx.person.model.Student;

public interface PersonService{
	
	//List<Student> findAllStudents();
	
	public Student findById(long id);
	
	public Student findByName(String name);
	
	public Student addStudent(Student student);
	
	public boolean isStudentExist(Student student);
}
