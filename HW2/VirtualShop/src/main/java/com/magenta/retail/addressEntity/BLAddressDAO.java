package com.magenta.retail.addressEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.magenta.retail.display.DisplayData;
import com.magenta.retail.utils.Utils;

public class BLAddressDAO implements BLIAddressDAO {

	private EntityManager entityManager;
	private DisplayData displayData;

	public BLAddressDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VirtualShop");
		entityManager = emf.createEntityManager();
		displayData = new DisplayData();
	}

	public Address findAddressById(int address_id) {
		displayData.printMessage(Utils.FIND_ADDRESS_BY_ID_MESSAGE + address_id);
		EntityTransaction findAddressByIdTransaction = entityManager.getTransaction();
		findAddressByIdTransaction.begin();
		Address address = entityManager.find(Address.class, address_id);
		findAddressByIdTransaction.commit();
		return address;
	}

	public List<Address> findAllAddresses() {
		displayData.printMessage(Utils.FIND_ALL_ADDRESSES_MESSAGE);
		return entityManager.createQuery("SELECT a FROM Address a").getResultList();
	}

	public void deleteAllAddresses() {
		displayData.printMessage(Utils.DELETE_ALL_ADDRESSES_MESSAGE);
		EntityTransaction deleteAllAddressesTransaction = entityManager.getTransaction();
		deleteAllAddressesTransaction.begin();
		Query deleteQuery = entityManager.createQuery("DELETE FROM Address");
		deleteQuery.executeUpdate();
		deleteAllAddressesTransaction.commit();
	}

	public void deleteAddressById(int address_id) {
		displayData.printMessage(Utils.DELETE_ADDRESS_BY_ID_MESSAGE + address_id);
		EntityTransaction deleteAllAddressesTransaction = entityManager.getTransaction();
		deleteAllAddressesTransaction.begin();
		Address address = entityManager.getReference(Address.class, address_id);
		if (address != null) {
			entityManager.remove(address);
		}
		deleteAllAddressesTransaction.commit();
	}

	public void insertAddress(Address address) {
		displayData.printMessage(Utils.INSERT_ADDRESS_MESSAGE);
		EntityTransaction insertAddressTransaction = entityManager.getTransaction();
		insertAddressTransaction.begin();
		entityManager.persist(address);
		insertAddressTransaction.commit();
	}

	public void updateAddress(Address address) {
		displayData.printMessage(Utils.UPDATE_ADDRESS_MESSAGE + address.getId());
		EntityTransaction updateAddressTransaction = entityManager.getTransaction();
		updateAddressTransaction.begin();
		entityManager.merge(address);
		updateAddressTransaction.commit();
	}

}