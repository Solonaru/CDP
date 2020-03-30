package com.magenta.retail.customerEntity;

import java.util.List;

public interface BLICustomerDAO {

	public Customer findCustomerById(int customer_id);

	public Customer findCustomerByAccount(String email, String password);

	public Customer findCustomerByEmail(String email);

	public List<Customer> findAllCustomers();

	public void deleteAllCustomers();

	public void deleteCustomerById(int customer_id);

	public void insertCustomer(Customer customer);

	public void updateCustomer(Customer customer);

}
