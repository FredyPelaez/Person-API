package com.esrx.person.service.student;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrx.person.model.Student;
import com.esrx.person.repository.PersonRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private PersonRepository repository;
	
	private static final AtomicLong counter = new AtomicLong();
	final static Logger logger = LogManager.getLogger(StudentServiceImpl.class);
	
	public List<Student> findAllStudents(){
		logger.info("Retrieving information from Repository");
		return repository.getListStudent();
	}
	
	public Student addStudent(Student student) {
		
		try {
			student.setId(counter.incrementAndGet());
			repository.getListStudent().add(student);
			logger.info("Student Added");
		}catch(Exception e) {
			logger.error("Can't add Student" + e);
		}
		return student;
	}
	
	public Student findById(long id) {
	
		try {
			for (int i = 0; i < repository.getListStudent().size(); i++) {
				if (repository.getListStudent().get(i).getId() == id) {
					logger.info("Retrieving information from Repository");
					return repository.getListStudent().get(i);
				}
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public Student findByName(String firstName, String lastName) {
		
		try {
			for (int i = 0; i < repository.getListStudent().size(); i++) {
				if (repository.getListStudent().get(i).getFirstName().equals(firstName) && repository.getListStudent().get(i).getLastName().equals(lastName)) {
					logger.info("Retrieving information from Repository");
					return repository.getListStudent().get(i);
				}
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	} 
	
	public Student updateStudent(Student student) {
		
		try {
			if(StringUtils.isNotBlank(student.getFirstName()) && student.getFirstName() != repository.getListStudent().get((int)(student.getId())-1).getFirstName()) {
				repository.getListStudent().get((int) (student.getId()-1)).setFirstName(student.getFirstName());
			}
			if(StringUtils.isNotBlank(student.getLastName()) && student.getLastName() != repository.getListStudent().get((int)(student.getId())-1).getLastName()) {
				repository.getListStudent().get((int) (student.getId()-1)).setLastName(student.getLastName());
			}
		}catch(Exception e) {
			logger.error(e);
		}
		logger.info("Student Modified");
		return student;
	}
	
	public void deleteStudent(Student student) {
		
		try {
			repository.getListStudent().remove(studentExist(student));
		}catch(Exception e) {
			logger.error(e);
		}
		logger.info("Student Deleted");
	}
	
	public Student studentExist (Student student) {
		
		try {
			return findByName(student.getFirstName(), student.getLastName());
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
}
