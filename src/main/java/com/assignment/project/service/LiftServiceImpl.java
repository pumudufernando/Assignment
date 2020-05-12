package com.assignment.project.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.assignment.project.model.Lift;
import com.assignment.project.model.LiftModel;
import com.assignment.project.model.TimeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Repository
public class LiftServiceImpl {
	
	
	//log files 
	private static final Logger logger = LogManager.getLogger(LiftServiceImpl.class);

	//method to calculate the time
	public TimeModel calculateMethod(int startfloor,int endfloor) {
		
		String direction;
		String liftId;
		String person;
		String floor;
		String state = "idle";
		
		
		DateFormat dateFormat = new SimpleDateFormat("mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		String systemtime = dateFormat.format(date);
		
		
		
		int timedif = endfloor - startfloor;
		
		int xtime = timedif * 3 + 4;
		
		int time = Math.abs(xtime);
		

		
		int lifttime = Integer.valueOf(systemtime) + Integer.valueOf(time);
		
		if(lifttime > Integer.valueOf(systemtime)) {
			
			liftId = "1";
			
		}else {
			
			liftId = "2";
		}
		

		
		System.out.println("time "+timedif);
		
		
		if(timedif>0) {
			direction = "up";
			state = "TO_DROPOFF";
			person = "1";
		}else if(timedif<0) {
			
			direction= "down";
			state="TO_PICKUP";
			person = "1";
		}else {
			direction="NAN";
			state="IDLE";
			person="0";
		}
		
		
		
		TimeModel model = new TimeModel();
		
		Lift obj = new Lift();
		
		obj.setFloor(String.valueOf(endfloor));
		obj.setDirection(direction);
		obj.setLiftId(liftId);
		obj.setState(state);
		obj.setPerson(person);
		
		//logger.info("created.");
		
		model.setTime(time);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String json="";
    	String json2="";
		try {
			json = ow.writeValueAsString(obj);
			json2 = ow.writeValueAsString(model);
						
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("model"+json);
		System.out.println("Time"+json2);
		logger.info(json);
		return model;
	}

}
