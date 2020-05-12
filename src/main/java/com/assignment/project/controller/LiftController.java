package com.assignment.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.project.model.LiftModel;
import com.assignment.project.model.TimeModel;
import com.assignment.project.service.LiftServiceImpl;

@RestController
@RequestMapping("/smartkent/liftsimulation")
@CrossOrigin(origins = "localhost", maxAge = 3600) 
public class  LiftController{
	
	
	@Autowired
	LiftServiceImpl liftservice;
	
	
	//select the floors that enter by the user
	@GetMapping("/calculation/{pickupfloor}/{dropupfloor}")
		public TimeModel getTransByLimitandAccno(@PathVariable(value = "pickupfloor") int pickupfloor, @PathVariable(value = "dropupfloor")int dropupfloor){
		
		//pass the floor no to service method
		TimeModel time = liftservice.calculateMethod(pickupfloor, dropupfloor);
		return time;
	}
	
	
}