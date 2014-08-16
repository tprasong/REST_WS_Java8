package com.rest.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="address") 
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
	@XmlElement
	private String street;
	@XmlElement
	private String city;
	@XmlElement
	private String state;
	@XmlElement
	private String zip;
	@XmlElement
	private String country;

	public Address() {
	}

	public String getStreet() {
		java.util.List<Address> sList = new ArrayList<Address>();
		sList.stream().allMatch(a -> a.getStreet()=="abc");
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}