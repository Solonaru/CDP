package com.magenta.retail.runner;

import java.util.List;

import com.magenta.retail.addressEntity.Address;
import com.magenta.retail.addressEntity.BLAddressDAO;
import com.magenta.retail.addressEntity.BLIAddressDAO;
import com.magenta.retail.customerEntity.BLCustomerDAO;
import com.magenta.retail.customerEntity.BLICustomerDAO;
import com.magenta.retail.customerEntity.Customer;
import com.magenta.retail.display.DisplayData;

public class RunnerDAO {

	public static void main(String args[]) {
		BLICustomerDAO customerDAO = new BLCustomerDAO();
		BLIAddressDAO addressDAO = new BLAddressDAO();
		DisplayData displayData = new DisplayData();

		/* Operations on Address table */
		Address address1 = new Address();
		address1.setStreetName("Primaverii");
		address1.setNumber("237");
		address1.setCity("Iasi");

		Address address2 = new Address();
		address2.setStreetName("Mihai Eminescu");
		address2.setNumber("153");
		address2.setCity("Iasi");

//		addressDAO.insertAddress(address1);
//		addressDAO.insertAddress(address2);

		List<Address> listOfAddress = addressDAO.findAllAddresses();
		displayData.showAddress("Address List: ", listOfAddress);

		/* Operations on Customer table */
		Customer customer1 = new Customer();
		customer1.setFirstName("Ioana");
		customer1.setLastName("Popescu");
		customer1.setPhoneNumber("0742849555");
		customer1.setEmail("ioana_popescu@yahoo.com");
		customer1.setPassword("17041996");
		customer1.addCostumerAddress(address1);
		customer1.addCostumerAddress(address2);
		customerDAO.insertCustomer(customer1);

		List<Customer> listOfCustomers = customerDAO.findAllCustomers();
		displayData.showCustomers("List of Customers:  ", listOfCustomers);

//		customerDAO.deleteAllCustomers();
//		addressDAO.deleteAllAddresses();
	}
}
