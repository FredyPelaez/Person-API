package com.esrx.person.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
	@JsonProperty
	@NotNull
	Long id;
	@JsonProperty
	@NotNull
	String firstName;
	@JsonProperty
	@NotNull
	String lastName;
	
}