package com.esrx.person.transformer;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

import com.esrx.person.model.Student;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class StudentMapper {
	public Student map(Object obj) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(LinkedHashMap.class, Student.class)
		.field("nest:{get('nest')|type=Map}.id:{get('id')|type=java.lang.String}", "id")
		.field("nest:{get('nest')|type=Map}.type:{get('type')|type=java.lang.String}", "type")
		.field("nest:{get('nest')|type=Map}.firstName:{get('firstName')|type=java.lang.String}", "firstName")
		.field("nest:{get('nest')|type=Map}.lastName:{get('lastName')|type=java.lang.String}", "lastName")
		.toClassMap();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		Student student = mapper.map(obj, Student.class);
		return student;
	}
}
