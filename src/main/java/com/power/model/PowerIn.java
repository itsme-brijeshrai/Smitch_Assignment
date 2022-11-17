package com.power.model;

import java.time.LocalDateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerIn {
	@NotNull(message = "From Time Empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime fromTime;
	
	@NotNull(message = "To Time Empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime toTime;
	
	@NotNull(message = "Appliance feld type Empty")
	String applianceType;
	
	
}
