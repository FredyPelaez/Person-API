package com.esrx.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor

@Accessors(fluent = true) @Getter 
public class Student {
	
	//UUID id;
	@JsonProperty
	public String firstName;
	@JsonProperty
	public String lastName;
	
}