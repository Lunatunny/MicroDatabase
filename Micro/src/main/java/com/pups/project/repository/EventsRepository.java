package com.pups.project.repository;
import org.springframework.data.repository.CrudRepository;
import com.pups.project.domain.Event;

public interface EventsRepository extends CrudRepository<Event, Long> {
	
}
