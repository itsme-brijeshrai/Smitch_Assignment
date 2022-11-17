package com.power.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.power.model.PowerIn;
import com.power.model.PowerRange;
import com.power.model.PowerUsage;
import com.power.model.UsageDate;
import com.power.model.UserSession;
import com.power.repository.PowerUsageDao;
import com.power.repository.UserSessionDao;

@Service
public class PowerUsageServiceImpl implements PowerUsageService{
	
	@Autowired
	PowerUsageDao powerUsageDao;
	@Autowired
	UserSessionDao userSessionDAO;

	@Override
	public PowerUsage addPowerUsage(PowerIn power,String key) {
		PowerUsage usage=new PowerUsage();
		usage.setFromTime(power.getFromTime());
		usage.setToTime(power.getToTime());
		usage.setApplianceType(power.getApplianceType());
		
		int i= (int) ChronoUnit.MINUTES.between(power.getFromTime(), power.getToTime());
		int h=i/60;
		int m=i%60;
		LocalTime duration=LocalTime.of(h, m);
		
		usage.setDuration(duration);
		int unitc=0;
		if(power.getApplianceType().equalsIgnoreCase("low-power"))
			unitc=2;
		else if(power.getApplianceType().equalsIgnoreCase("mid-power"))
			unitc=3;
		else if(power.getApplianceType().equalsIgnoreCase("high-power"))
			unitc=4;
		else
			throw new RuntimeException("Enter valid Appliance type");
		
		double unitconsumed=((double)i/60)*unitc;
		usage.setUnitConsumed(unitconsumed);
		
		
		return powerUsageDao.save(usage);
	}

	
	
	
	@Override
	public List<PowerUsage> getList(UsageDate us,String key) {
		 Optional<UserSession> optCurrUser1= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser1.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		 Optional<UserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	
		List<PowerUsage> list=powerUsageDao.findByDateBetween(us.getFromTime(), us.getToTime());
		if(list.size()>0)
			return list;
		else
			throw new RuntimeException("No data");
	}

	
	
	
	@Override
	public PowerRange getPowerRange(UsageDate us,String key) {
		 Optional<UserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
		List<PowerUsage> list=powerUsageDao.findByDateBetween(us.getFromTime(),us.getToTime());
		if(list.size()>0)
		{
			int i= (int) ChronoUnit.DAYS.between(us.getFromTime(),us.getToTime());
			double total=0;
			for(PowerUsage l:list)
			{
				total+=l.getUnitConsumed();
			}
			PowerRange pr=new PowerRange();
			pr.setDay(i);
			pr.setUnitconsumed(total);
			return pr;
		}
		else
			throw new RuntimeException("No data");
		
	}
	

}
