package com.pups.project.repository;
import org.springframework.data.repository.CrudRepository;
import com.pups.project.domain.Registration;

public interface RegistrationsRepository extends CrudRepository<Registration, Long> {
	
}
