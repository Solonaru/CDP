package com.magenta.retail.display;

import java.util.List;

import org.apache.log4j.Logger;

import com.magenta.retail.addressEntity.Address;
import com.magenta.retail.customerEntity.Customer;

public class DisplayData {

	public static final Logger LOGGER = Logger.getLogger(DisplayData.class);

	/* ----- METHODS ----- */
	public void printMessage(String message) {
		LOGGER.info(message);

	}

	public void showAddress(String message, List<Address> list) {
		String details = message;
		for (Address a : list) {
			details += a.toString() + "\n\t\t\t\t\t\t\t  ";
		}

		LOGGER.info(details);

	}

	public void showCustomers(String message, List<Customer> list) {
		String details = message;
		for (Customer a : list) {
			details += a.toString() + "\n\t\t\t\t\t\t\t\t";
		}

		LOGGER.info(details);
	}

}
