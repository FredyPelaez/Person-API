package com.esrx.person.service;

import java.util.List;

import com.esrx.person.model.Student;
import com.esrx.person.model.Teacher;

public interface PersonService{
	
	public List<Student> findAllStudents();

	public List<Teacher> findAllTeachers();

	public Object addPerson(Object obj, String type);

	public Object findById(String type, long id);
	
	public Object findByName(String type,String firstName, String lastName);
	
	
	public Object updatePerson(Object obj, String type);
	
	public void deletePerson(Object obj, String type);
	
	public Object personExist(Object obj, String type);
}
