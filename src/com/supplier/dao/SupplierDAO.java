package com.supplier.dao;

import com.supplier.model.Supplier;

public interface SupplierDAO {

	public boolean supplierSignUp(Supplier supplier);
	
	public Supplier getSupplierDetails(Supplier supplier);
}
