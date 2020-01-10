package com.esrx.person.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrx.person.model.Student;
import com.esrx.person.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository repository;
	
	private static final AtomicLong counter = new AtomicLong();
	
	public List<Student> findAllStudents(){
		return repository.getListStudent();
	}
	
	public Student findById(long id) {
		for (int i = 0; i < repository.getListStudent().size(); i++) {
			if (repository.getListStudent().get(i).getId() == id) {
				return repository.getListStudent().get(i);
			}
		}
		return null;
	}
	
	public Student findByName(String firstName) {
		for (int i = 0; i < repository.getListStudent().size(); i++) {
			if (repository.getListStudent().get(i).getFirstName().equals(firstName)) {
				return repository.getListStudent().get(i);
			}
		}
		return null;
	}
	
	public Student addStudent(Student student) {
		student.setId(counter.incrementAndGet());
		repository.getListStudent().add(student);
		return student;
	}
	
	public Student updateStudent(Student student) {
		if(StringUtils.isNotBlank(student.getFirstName()) && student.getFirstName() != repository.getListStudent().get((int)(student.getId())-1).getFirstName()) {
			repository.getListStudent().get((int) (student.getId()-1)).setFirstName(student.getFirstName());
		}
		if(StringUtils.isNotBlank(student.getLastName()) && student.getLastName() != repository.getListStudent().get((int)(student.getId())-1).getLastName()) {
			repository.getListStudent().get((int) (student.getId()-1)).setLastName(student.getLastName());
		}
		return student;
	}
	
	public void deleteStudent(Student student) {
		repository.getListStudent().remove((int)(student.getId())-1);
	}
	
	public boolean studentExist (Student student) {
		return findByName(student.getFirstName()) !=null;
	}
}
