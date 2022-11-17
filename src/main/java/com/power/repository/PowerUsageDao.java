package com.power.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.power.model.PowerUsage;

public interface PowerUsageDao extends JpaRepository<PowerUsage, Integer>{
	
	//query to get list of data between start and end time
	@Query("SELECT p FROM PowerUsage p WHERE p.fromTime BETWEEN ?1 AND ?2 AND p.toTime BETWEEN ?1 AND ?2")
	List<PowerUsage> findByDateBetween(LocalDateTime start, LocalDateTime end);

}
