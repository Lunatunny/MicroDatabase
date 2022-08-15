package com.pups.project.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pups.project.domain.Customer;
import com.pups.project.domain.Registration;

@RestController
@RequestMapping("/registrations")
public class RegistrationApi {
	
	public ArrayList<Registration> registrationRepository = new ArrayList<Registration>();
	
	public RegistrationApi() {
		registrationRepository.add(new Registration(1,1,2,"2022-08-10T19:37:03.973+0000","Email me. I need to test if my email works."));
		registrationRepository.add(new Registration(2,2,5,"2022-08-10T19:37:03.973+0000","I have two monitors but only one works."));
		registrationRepository.add(new Registration(3,3,2,"2022-08-10T19:37:03.973+0000","My key.boa8/rd en;ters ran..do/m ke[ys."));
	}
	
	@GetMapping("")
	public ArrayList<Registration> getAllCustomers(){
		return registrationRepository;
	}
	
	@GetMapping("/{id}")
	public Registration getCustomer(@PathVariable int id){
		for(int i = 0; i < registrationRepository.size(); i++) {
			Registration registrationTemp = registrationRepository.get(i);
			if (registrationTemp.getId() == id) {
				return registrationTemp;
			}
		}
		return new Registration(0,0,0,"","");
	}
}