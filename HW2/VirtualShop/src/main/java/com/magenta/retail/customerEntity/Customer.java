package com.magenta.retail.customerEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import com.magenta.retail.addressEntity.Address;

@Entity
@Table(name = "Customer")
@XmlRootElement
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", updatable = false, nullable = false)
	private int customer_id;
	private String first_name;
	private String last_name;
	private String phone_number;
	private String email;
	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CUSTOMER_ADDRESS", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"), inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"))
	@CascadeOnDelete
	private List<Address> addresses = new ArrayList<Address>();;

	/* ----- GETTERS & SETTERS ----- */
	public int getId() {
		return customer_id;
	}

	public void setId(int customerId) {
		this.customer_id = customerId;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phone_number = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	/* ----- METHODS ----- */
	public void addCostumerAddress(Address costumerAddress) {
		addresses.add(costumerAddress);
	}

	public String toString() {
		String details = "{Customer id: " + this.customer_id + ", First name: " + this.first_name + ", Last name: "
				+ this.last_name + ",Email: " + this.email + ",Phone number: " + this.phone_number;
		if (addresses.size() != 0) {
			details += ", Address List = " + addresses + "}";
		} else {
			details += "}";
		}
		return details;
	}
}
