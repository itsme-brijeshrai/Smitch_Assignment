package com.power.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@NotNull(message = "Display Name is mandatory")
	private String display_name;
	
	
	@NotNull(message = "User Name is mandatory")
	@Column(unique = true)
	private String username ;
	
	@Email(message="Enter your Email ID properly")
	@Column(unique = true)
	@NotNull(message = "Email ID is mandatory")
	private String emailId;
	
	@Size(max = 10,min = 10)
	@Column(unique = true)
	@NotNull(message = "Mobile Number is mandatory")
	@Pattern(regexp = "^[6-9][0-9]{9}$",message="Mobile No is Invalid!")
	private String mobileNumber;
	
	@NotNull(message = "Password is mandatory")
	private String password;

	
	
}
