package com.pups.project.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pups.project.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerApi {
	
	public ArrayList<Customer> customerRepository = new ArrayList<Customer>();
	
	public CustomerApi() {
		customerRepository.add(new Customer(1,"Amy Stake","123!","amystake@gmail.com"));
		customerRepository.add(new Customer(2,"Barb Dwyer","123!","barbdwyer@gmail.com"));
		customerRepository.add(new Customer(3,"Chris P Bacon","123!","chrispbacon@gmail.com"));
		customerRepository.add(new Customer(4,"Ella Vader","123!","ellavader@gmail.com"));
		customerRepository.add(new Customer(5,"Lee King","123!","leeking@gmail.com"));
		customerRepository.add(new Customer(6,"Robyn Banks","123!","robynbanks@gmail.com"));
	}
	
	@RequestMapping("")
	public ArrayList<Customer> getAllCustomers(){
		return customerRepository;
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable int id){
		for(int i = 0; i < customerRepository.size(); i++) {
			Customer customerTemp = customerRepository.get(i);
			if (customerTemp.getId() == id) {
				return customerTemp;
			}
		}
		return new Customer(0,"","","");
	}
}