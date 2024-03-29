package com.swiftrides.controllers;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiftrides.models.AddRide;
import com.swiftrides.models.City;
import com.swiftrides.models.Ride;
import com.swiftrides.models.Role;
import com.swiftrides.models.User;
import com.swiftrides.models.Vehicle;
import com.swiftrides.services.CityService;
import com.swiftrides.services.RideService;
import com.swiftrides.services.UserService;
import com.swiftrides.services.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RideController 
{
	@Autowired
	RideService rservice;
	
	@Autowired
	CityService cservice;
	
	@Autowired
	VehicleService vservice;
	
	@Autowired
	UserService uservice;
	
	@PostMapping("/addRide")
	public Ride save(@RequestBody AddRide addr)
	{
		Time time_of_departure= addr.getTime_of_departure();
		
		Time time_of_arrival=addr.getTime_of_arival();
		
		Date date_of_journey=addr.getDate_of_journey();
		int price_per_seat=addr.getPrice_per_seat();
		
		int available_seats=addr.getAvailable_seats();
		
		String status=addr.getStatus();
		
		User u=uservice.getUserId(addr.getCarowner_id());
		City start_location=cservice.getCityId(addr.getStart_location());
		City end_location=cservice.getCityId(addr.getEnd_location());
		Vehicle v=vservice.getVehicleId(addr.getVehicle_id());
		
		Ride r=new Ride(date_of_journey,time_of_departure, time_of_arrival, price_per_seat, available_seats, status, u, start_location, end_location, v);
		
		return rservice.saveRide(r);	
	}
	
	@GetMapping("/getallrides")
	public List<Ride> getAll()
	{
		return rservice.getAll();
	}
	/*
	@GetMapping("/getOneRide")
	public Ride getOneRide(@RequestParam("rideid") int rideid)
	{
		return rservice.getOneRide(rideid);
	}
	
	@GetMapping("/getRide/{rid}")
	public Ride getRide(@PathVariable("rid") int rid)
	{
		return rservice.getOneRide(rid);
	}
	
	@GetMapping("/getRidesBetweenTwoCities")
	public List<Ride> getRidesBetweenCities(@RequestParam("start_location") City start_location,@RequestParam("end_location") City end_location)
	{
		return rservice.getRidesBetweenCitiesRange(start_location, end_location);
	}
	
	@GetMapping("getAllRidesFromOneCityToAnotherCityByDate")
	public List<Ride> getAllRidesFromOneCityToAnotherCityByDate(@RequestParam("start_location") City start_location,@RequestParam("end_location") City end_location,@RequestParam("date_of_journey") Date date_of_journey)
	{
		return rservice.getAllRidesFromOneCityToAnotherCityByDate(start_location, end_location, date_of_journey);
	}
	
	@GetMapping("/getAllRidesByDate")
	public List<Ride> getAllRidesByDate(@RequestParam("date_of_journey") Date date_of_journey)
	{
		return rservice.getAllRidesByDate(date_of_journey);
	}
	
	@GetMapping("/getAllRidesByStatus")
	public List<Ride> getAllRidesByStatus(@RequestParam("status") String status)
	{
		return rservice.getAllRidesByStatus(status);
	}
	
	@GetMapping("/getAllRidesById")
	public List<Ride> getAllRidesById(@RequestParam("carowner_id") User carowner_id)
	{
		return rservice.getAllRidesById(carowner_id);
	}
	
	@PutMapping("/changeRideStatusById")
	public int changeRideStatusById(@RequestParam("rid") int rid)
	{
		return rservice.changeRideStatusById(rid);
	}
	*/
}

