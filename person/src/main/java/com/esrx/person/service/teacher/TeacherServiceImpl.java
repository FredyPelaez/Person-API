package com.esrx.person.service.teacher;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrx.person.model.Teacher;
import com.esrx.person.repository.PersonRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private PersonRepository repository;
	
	private static final AtomicLong counter = new AtomicLong();
	final static Logger logger = Logger.getLogger(TeacherServiceImpl.class);
	
	public List<Teacher> findAllTeacher(){
		logger.info("Retrieving information from Repository");
		return repository.getListTeacher();
	}
	
	public Teacher addTeacher(Teacher teacher) {
		
		try {
			teacher.setId(counter.incrementAndGet());
			repository.getListTeacher().add(teacher);
			logger.info("Teacher Added");
		}catch(Exception e) {
			logger.error(e);
		}
		return teacher;
	}
	
	public Teacher findById(long id) {
		
		try {
			for (int i = 0; i < repository.getListTeacher().size(); i++) {
				if (repository.getListTeacher().get(i).getId() == id) {
					return repository.getListTeacher().get(i);
				}
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public Teacher findByName(String firstName, String lastName) {
		
		try {
			for (int i = 0; i < repository.getListTeacher().size(); i++) {
				if (repository.getListTeacher().get(i).getFirstName().equals(firstName) && repository.getListTeacher().get(i).getLastName().equals(lastName)) {
					return repository.getListTeacher().get(i);
				}
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	} 
	
	public Teacher updateTeacher(Teacher teacher) {
		
		try {
			if(StringUtils.isNotBlank(teacher.getFirstName()) && teacher.getFirstName() != repository.getListTeacher().get((int)(teacher.getId())-1).getFirstName()) {
				repository.getListTeacher().get((int) (teacher.getId()-1)).setFirstName(teacher.getFirstName());
			}
			if(StringUtils.isNotBlank(teacher.getLastName()) && teacher.getLastName() != repository.getListTeacher().get((int)(teacher.getId())-1).getLastName()) {
				repository.getListTeacher().get((int) (teacher.getId()-1)).setLastName(teacher.getLastName());
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return teacher;
	}
	
	public void deleteTeacher(Teacher teacher) {
		
		try {
			repository.getListTeacher().remove(teacherExist(teacher));
		}catch(Exception e) {
			logger.error(e);
		}
	}
	
	public Teacher teacherExist (Teacher teacher) {
		
		try {
			return findByName(teacher.getFirstName(), teacher.getLastName());			
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
}
