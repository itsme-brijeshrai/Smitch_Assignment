package com.power.service;

import java.time.LocalDateTime;
import java.util.List;

import com.power.model.PowerIn;
import com.power.model.PowerRange;
import com.power.model.PowerUsage;
import com.power.model.UsageDate;

public interface PowerUsageService {
	
	public PowerUsage addPowerUsage(PowerIn power,String key);
	
	public List<PowerUsage> getList(UsageDate us,String key) ;
	
	public PowerRange getPowerRange(UsageDate us,String key);

}
