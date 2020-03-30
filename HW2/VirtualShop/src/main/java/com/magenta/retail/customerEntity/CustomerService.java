package com.magenta.retail.customerEntity;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class CustomerService {

	private BLCustomerDAO customerDAO = new BLCustomerDAO();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		return customerDAO.findAllCustomers();
	}

	@GET
	@Path("/{customer_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerById(@PathParam("customer_id") int customer_id) {
		return customerDAO.findCustomerById(customer_id);
	}

	@GET
	@Path("/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByEmail(@PathParam("email") String email) {
		return customerDAO.findCustomerByEmail(email);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertCustomer(Customer customer) {
		customerDAO.insertCustomer(customer);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
	}

	@DELETE
	@Path("/{customer_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCustomer(@PathParam("customer_id") int customer_id) {
		customerDAO.deleteCustomerById(customer_id);
	}

	@DELETE
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCustomers() {
		customerDAO.deleteAllCustomers();
	}

}
