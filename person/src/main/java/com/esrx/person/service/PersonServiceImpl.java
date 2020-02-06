package com.esrx.person.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrx.person.model.Student;
import com.esrx.person.model.Teacher;
import com.esrx.person.service.student.StudentService;
import com.esrx.person.service.teacher.TeacherService;
import com.esrx.person.transformer.StudentMapper;
import com.esrx.person.transformer.TeacherMapper;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TeacherMapper teacherMapper;
		
	final static Logger logger = Logger.getLogger(PersonServiceImpl.class);
	
	
	/*Flags to define the type of the object*/
	private boolean studentFlag = false;
	private boolean teacherFlag = false;
	
	/*Validator if a request need to be Student*/
	private boolean isStudent(String type) {
		studentFlag = false;
		teacherFlag = false;
		if(type.equalsIgnoreCase("Student"))
			studentFlag = true;
		return studentFlag;
	}

	/*Validator if a request need to be Teacher*/
	private boolean isTeacher(String type) {
		teacherFlag = false;
		studentFlag = false;
		if(type.equalsIgnoreCase("Teacher"))
			teacherFlag = true;
		return teacherFlag;
	}
	
	public List<Student> findAllStudents(){
		logger.info("--- Calling Student Service ---");
		return studentService.findAllStudents();
	}
	
	public List<Teacher> findAllTeachers(){
		logger.info("--- Calling Teacher Service ---");
		return teacherService.findAllTeacher();
	}
	
	public Object addPerson(Object obj, String type) {
		
		try {
			if(isStudent(type)) {			
				logger.debug("Trying to map Student object");
				return studentService.addStudent(studentMapper.map(obj));
			}
			else if (isTeacher(type)) {
				logger.debug("Trying to map Teacher object");
				return teacherService.addTeacher(teacherMapper.map(obj));
			}
		}catch(Exception e) {
			logger.error("Invalid object, can't add person " + e);
		}
		return null;
	}
	
	public Object findById(String type, long id) {
		
		try {
			if(isStudent(type)) {			
				logger.info("--- Calling Student Service ---");
				studentService.findById(id);
			}
			else if(isTeacher(type)) {
				logger.info("--- Calling Teacher Service ---");
				teacherService.findById(id);
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public Object findByName(String type, String firstName, String lastName) {
		
		try {
			if(isStudent(type)) {
				logger.info("--- Calling Student Service ---");
				return studentService.findByName(firstName, lastName);
			}
			else if(isTeacher(type)) {
				logger.info("--- Calling Teacher Service ---");
				return teacherService.findByName(firstName, lastName);
			} 
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	} 
	
	public Object updatePerson(Object obj, String type) {
		
		try {
			if(isStudent(type)) {
				logger.debug("Trying to map Student object");
				return studentService.updateStudent(studentMapper.map(obj));
			}
			else if(isTeacher(type)) {
				logger.debug("Trying to map Teacher object");
				return teacherService.updateTeacher(teacherMapper.map(obj));
			}
		}catch(Exception e) {
			logger.error("Can't update Person " + e);
		}
		return null;
	}
	
	public void deletePerson(Object obj, String type) {
		
		try {
			if(isStudent(type)) {
				logger.debug("Trying to map Student object");
				studentService.deleteStudent(studentMapper.map(obj));
			}
			else if(isTeacher(type)) {
				logger.debug("Trying to map Teacher object");
				teacherService.deleteTeacher(teacherMapper.map(obj));
			}
		}catch(Exception e) {
			logger.error("Can't delete Person " + e);
		}
	}
	
	public Object personExist(Object obj, String type) {
		
		try {
			if(isStudent(type)) {
				logger.debug("Trying to map Student object");
				return studentService.studentExist(studentMapper.map(obj));
			}
			else if(isTeacher(type)) {
				logger.debug("Trying to map Teacher object");
				return teacherService.teacherExist(teacherMapper.map(obj));
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
}
