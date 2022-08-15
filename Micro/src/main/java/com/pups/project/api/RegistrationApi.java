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

import com.pups.project.domain.Registration;
import com.pups.project.repository.RegistrationsRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationApi {
	
	@Autowired
	RegistrationsRepository registrationsRepository;
	
	@GetMapping("")
	public Iterable<Registration> getAllRegistration(){
		return registrationsRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Registration> getCRegistration(@PathVariable long id){
		return registrationsRepository.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration newRegistration, UriComponentsBuilder uri){
		if (newRegistration.getId() != 0 || newRegistration.getCustomer_id()==null || newRegistration.getEvent_id() == null || newRegistration.getRegistration_date()==null || newRegistration.getNotes()==null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration=registrationsRepository.save(newRegistration);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newRegistration.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putCustomer(@RequestBody Registration newRegistration, @PathVariable("id") long id){
		if (newRegistration.getId() != id || newRegistration.getCustomer_id()==null || newRegistration.getEvent_id() == null || newRegistration.getRegistration_date()==null || newRegistration.getNotes()==null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration=registrationsRepository.save(newRegistration);
		return ResponseEntity.ok().build();
	}
}