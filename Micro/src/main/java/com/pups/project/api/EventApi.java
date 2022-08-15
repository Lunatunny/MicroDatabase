package com.pups.project.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pups.project.domain.Customer;
import com.pups.project.domain.Event;

@RestController
@RequestMapping("/events")
public class EventApi {
	
	public ArrayList<Event> eventRepository = new ArrayList<Event>();
	
	public EventApi() {
		eventRepository.add(new Event(1,"FJ35","Java Complete","Complete Java"));
		eventRepository.add(new Event(2,"EIR9","Email me","I need to test if my email works."));
		eventRepository.add(new Event(3,"D2R4","Monitor trouble","I have two monitors but only one works."));
		eventRepository.add(new Event(4,"G42G","My k.eyboar4d is b4rok,,en","My key.boa8/rd pres5ses en;tters ran..do/m ke[ys it00's ve,r2y an/noying."));
	}
	
	@GetMapping("")
	public ArrayList<Event> getAllCustomers(){
		return eventRepository;
	}
	
	@GetMapping("/{id}")
	public Event getCustomer(@PathVariable int id){
		for(int i = 0; i < eventRepository.size(); i++) {
			Event eventTemp = eventRepository.get(i);
			if (eventTemp.getId() == id) {
				return eventTemp;
			}
		}
		return new Event(0,"","","");
	}
}