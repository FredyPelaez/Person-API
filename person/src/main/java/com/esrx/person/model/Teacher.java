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
	private long id;
	@JsonProperty
	@NotNull
	private String type;
	@JsonProperty
	@NotNull
	private String firstName;
	@JsonProperty
	@NotNull
	private String lastName;
	
}