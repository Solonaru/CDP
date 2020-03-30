package com.magenta.retail.addressEntity;

import java.util.List;

public interface BLIAddressDAO {

	public Address findAddressById(int address_id);

	public List<Address> findAllAddresses();

	public void deleteAllAddresses();

	public void insertAddress(Address address);

	public void updateAddress(Address address);

	public void deleteAddressById(int address_id);

}