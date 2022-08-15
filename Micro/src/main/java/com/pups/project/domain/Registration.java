package com.pups.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRATIONS")
public class Registration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="EVENT_ID")
	private Integer event_id;
	
	@Column(name="CUSTOMER_ID")
	private Integer customer_id;
	
	@Column(name="REGISTRATION_DATE")
	private String registration_date;
	
	@Column(name="NOTES")
	private String notes;
	
	public long getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getEvent_id() {
		return event_id;
	}


	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}


	public Integer getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}


	public String getRegistration_date() {
		return registration_date;
	}


	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
