package com.esrx.person.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

//import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
	
	//UUID id;
	@JsonProperty
	@NotNull
	private String firstName;
	@JsonProperty
	@NotNull
	private String lastName;
	
}