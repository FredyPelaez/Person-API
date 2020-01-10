package com.esrx.person.service;

import java.util.List;

import com.esrx.person.model.Student;

public interface PersonService{
	
	public List<Student> findAllStudents();
	
	public Student findById(long id);
	
	public Student findByName(String name);
	
	public Student addStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(Student student);
	
	public boolean studentExist(Student student);
}
