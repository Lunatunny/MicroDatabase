package com.pups.project.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.pups.project.domain.Event;
import com.pups.project.repository.EventsRepository;

@RestController
@RequestMapping("/events")
public class EventApi {
	
	@Autowired
	EventsRepository eventsRepository;
	
	@RequestMapping("")
	public Iterable<Event> getAllCustomers(){
		return eventsRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Event> getEvent(@PathVariable long id){
		return eventsRepository.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event newEvent, UriComponentsBuilder uri){
		if (newEvent.getId() != 0 || newEvent.getCode()==null || newEvent.getTitle()==null || newEvent.getDescription()==null) {
			return ResponseEntity.badRequest().build();
		}
		newEvent=eventsRepository.save(newEvent);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putEvent(@RequestBody Event newEvent, @PathVariable("id") long id){
		if (newEvent.getId()!=id || newEvent.getCode()==null || newEvent.getTitle()==null || newEvent.getDescription()==null) {
			return ResponseEntity.badRequest().build();
		}
		newEvent=eventsRepository.save(newEvent);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable("id") long id){
		if (eventsRepository.existsById(id)) {
			eventsRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}