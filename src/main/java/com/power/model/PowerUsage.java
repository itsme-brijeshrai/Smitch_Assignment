package com.power.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerUsage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime fromTime;
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime toTime;
	

	
	double unitConsumed;

	LocalTime duration;
	
	String applianceType;
	

}
