package com.esrx.person.service.student;

import java.util.List;

import com.esrx.person.model.Student;

public interface StudentService {

public List<Student> findAllStudents();
	
	public Student findById(long id);
	
	public Student findByName(String firstName, String lastName);
	
	public Student addStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public void deleteStudent(Student student);
	
	public Student studentExist(Student student);
}
