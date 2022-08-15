package com.pups.project.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pups.project.domain.Customer;
import com.pups.project.repository.CustomersRepository;

@RestController
@RequestMapping("/customers")
public class CustomerApi {
	
	@Autowired
	CustomersRepository customersRepository;
	
	@RequestMapping("")
	public Iterable<Customer> getAllCustomers(){
		return customersRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Customer> getCustomer(@PathVariable long id){
		return customersRepository.findById(id);
	}
}