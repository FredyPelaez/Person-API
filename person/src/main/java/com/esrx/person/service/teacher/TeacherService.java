package com.esrx.person.service.teacher;

import java.util.List;

import com.esrx.person.model.Teacher;

public interface TeacherService {

public List<Teacher> findAllTeacher();
	
	public Teacher findById(long id);
	
	public Teacher findByName(String firstName, String lastName);
	
	public Teacher addTeacher(Teacher teacher);
	
	public Teacher updateTeacher(Teacher teacher);
	
	public void deleteTeacher(Teacher teacher);
	
	public Teacher teacherExist(Teacher teacher);
}
