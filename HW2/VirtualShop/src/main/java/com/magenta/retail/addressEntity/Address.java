package com.magenta.retail.addressEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import com.magenta.retail.customerEntity.Customer;

@Entity
@Table(name = "Address")
@XmlRootElement
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", updatable = false, nullable = false)
	private int address_id;
	private String street_name;
	private String number;
	private String city;

	@ManyToMany(mappedBy = "addresses")
	@CascadeOnDelete
	private List<Customer> customers = new ArrayList<Customer>();

	/* ----- GETTERS & SETTERS ----- */
	public int getId() {
		return address_id;
	}

	public void setId(int addressId) {
		this.address_id = addressId;
	}

	public String getStreetName() {
		return street_name;
	}

	public void setStreetName(String streetName) {
		this.street_name = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/* ----- METHODS ----- */
	public void addCostumers(Customer customer) {
		customers.add(customer);
	}

	public String toString() {
		return "{Address ID:" + this.address_id + ", Street Name: " + this.street_name + ", Number: " + this.number
				+ ", City: " + this.city + "}";
	}

}
