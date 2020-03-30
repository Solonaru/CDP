package com.magenta.retail.customerEntity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.magenta.retail.display.DisplayData;
import com.magenta.retail.utils.Utils;

public class BLCustomerDAO implements BLICustomerDAO {
	private EntityManager entityManager;
	private DisplayData displayData;

	public BLCustomerDAO() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VirtualShop");
		entityManager = entityManagerFactory.createEntityManager();
		displayData = new DisplayData();
	}

	public Customer findCustomerById(int customer_id) {
		displayData.printMessage(Utils.FIND_CUSTOMER_BY_ID_MESSAGE + customer_id);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Customer customer = entityManager.find(Customer.class, customer_id);
		transaction.commit();
		return customer;
	}

	public Customer findCustomerByAccount(String email, String password) {
		List<Customer> listOfCustomers = findAllCustomers();
		for (Customer customer : listOfCustomers) {
			if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
				return customer;
			}
		}
		return null;
	}

	public Customer findCustomerByEmail(String email) {
		List<Customer> listOfCustomers = findAllCustomers();
		for (Customer customer : listOfCustomers) {
			if (customer.getEmail().equals(email)) {
				return customer;
			}
		}
		return null;
	}

	public void insertCustomer(Customer customer) {
		displayData.printMessage(Utils.INSERT_CUSTOMER_MESSAGE);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(customer);
		transaction.commit();
	}

	public void deleteCustomerById(int customer_id) {
		displayData.printMessage(Utils.DELETE_CUSTOMER_BY_ID_MESSAGE + customer_id);
		EntityTransaction deleteAllCustomersTransaction = entityManager.getTransaction();
		deleteAllCustomersTransaction.begin();
		Customer customer = entityManager.find(Customer.class, customer_id);
		entityManager.remove(customer);
		deleteAllCustomersTransaction.commit();
	}

	public void updateCustomer(Customer customer) {
		displayData.printMessage(Utils.UPDATE_CUSTOMER_MESSAGE + customer.getId());
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(customer);
		transaction.commit();
	}

	public List<Customer> findAllCustomers() {
		displayData.printMessage(Utils.FIND_ALL_CUSTOMERS_MESSAGE);
		return entityManager.createQuery("SELECT c FROM Customer c").getResultList();
	}

	public void deleteAllCustomers() {
		displayData.printMessage(Utils.DELETE_ALL_CUSTOMERS_MESSAGE);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNativeQuery("DELETE FROM customer");
		query.executeUpdate();
		transaction.commit();
	}

}
