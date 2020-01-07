package com.esrx.person.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.esrx.person.model.Student;
import com.esrx.person.repository.PersonRepository;

@Service
public abstract class PersonServiceImpl implements PersonService{
	
	private PersonRepository repository = new PersonRepository();
	private static final AtomicLong counter = new AtomicLong();
	
	public Student findById(long id) {
		for (Student student : repository.returnStudents()) {
			if (student.getId()==id) {
				return student;
			}
		}
		return null;
	}
	
	public Student findByName(String name) {
		for (Student student: repository.returnStudents()) {
			if (student.getFirstName().equalsIgnoreCase(name)) {
				return student;
			}
		}
		return null;
	}
	
	public Student addStudent (Student student) {
		student.setId(counter.incrementAndGet());
		repository.addStudent(student);
		return student;
	}
	
	public boolean isStudentExist (Student student) {
		return findByName(student.getFirstName()) !=null;
	}
}
