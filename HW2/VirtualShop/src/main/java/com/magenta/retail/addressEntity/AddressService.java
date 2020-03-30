package com.magenta.retail.addressEntity;

import java.util.ArrayList;
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

@Path("/address")
public class AddressService {
	private BLAddressDAO addressDAO = new BLAddressDAO();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> get() {
		List<Address> list = new ArrayList<Address>();
		list = addressDAO.findAllAddresses();
		return list;
	}

	@GET
	@Path("/{address_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Address getById(@PathParam("address_id") int address_id) {
		return addressDAO.findAddressById(address_id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertAddress(Address address) {
		addressDAO.insertAddress(address);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateAddress(Address address) {
		addressDAO.updateAddress(address);
	}

	@DELETE
	@Path("/{address_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAddress(@PathParam("address_id") int address_id) {
		addressDAO.deleteAddressById(address_id);
	}

	@DELETE
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAddresses() {
		addressDAO.deleteAllAddresses();
	}

}
