package com.pups.project.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri){
		if (newCustomer.getId()!=0 || newCustomer.getName()==null || newCustomer.getPass()==null || newCustomer.getEmail()==null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer=customersRepository.save(newCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable("id") long id){
		if (newCustomer.getId()!=id || newCustomer.getName()==null || newCustomer.getEmail()==null || newCustomer.getPass()==null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer=customersRepository.save(newCustomer);
		return ResponseEntity.ok().build();
	}
	
}