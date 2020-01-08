package com.esrx.person.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrx.person.model.Student;
import com.esrx.person.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository repository;
	
	private static final AtomicLong counter = new AtomicLong();
	
	public Student findById(long id) {
	
		for (int i = 0; i < repository.getListStudent().size(); i++) {
			if (repository.getListStudent().get(i).getId() == id) {
				return repository.getListStudent().get(i);
			}
		}
		return null;
	}
	
	public Student findByName(String firstName) {
		for (Student student: repository.returnStudents()) {
			if (student.getFirstName().equalsIgnoreCase(firstName)) {
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
