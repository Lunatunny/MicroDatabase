package com.pups.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.pups.project.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}
