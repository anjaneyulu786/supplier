package com.supplier.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.supplier.model.Supplier;
import com.supplier.util.DataRetrival;

@Repository
public class SupplierDAOImpl implements SupplierDAO {

	public static final String SUPPLIER_SIGNUP = "INSERT INTO SUPPLIER_DETAILS(USER_NAME, PASSWORD, MOBILE_NUMBER, LOCATION, CREATED_DATE) VALUES(?,?,?,?,NOW())";

	public static final String GET_SUPPLIER_DETAILS = "SELECT ID, USER_NAME, MOBILE_NUMBER, LOCATION, STATUS FROM SUPPLIER_DETAILS WHERE USER_NAME=? AND PASSWORD=?";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean supplierSignUp(Supplier supplier) {
		// TODO Auto-generated method stub
		boolean isCreated = false;
		int i = jdbcTemplate.update(SUPPLIER_SIGNUP, supplier.getUserName(), supplier.getPassword(),
				supplier.getMobileNumber(), supplier.getLocation());

		if (i > 0) {
			isCreated = true;
		}
		return isCreated;
	}

	@Override
	public Supplier getSupplierDetails(Supplier supplier) {
		// TODO Auto-generated method stub
		Supplier supplierDetails = null;
		List<Map<String, Object>> retrievedDetails = jdbcTemplate.queryForList(GET_SUPPLIER_DETAILS,
				supplier.getUserName(), supplier.getPassword());

		if (!retrievedDetails.isEmpty()) {
			for (Map<String, Object> map : retrievedDetails) {
				supplierDetails = retrieveSupplier(map);
			}
		}

		return supplierDetails;
	}

	private Supplier retrieveSupplier(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Supplier supplier = new Supplier();
		supplier.setId(DataRetrival.getIntValue("ID", map));
		supplier.setUserName(DataRetrival.getStringValue("USER_NAME", map));
		supplier.setMobileNumber(DataRetrival.getStringValue("MOBILE_NUMBER", map));
		supplier.setLocation(DataRetrival.getStringValue("LOCATION", map));
		supplier.setStatus(DataRetrival.getStringValue("STATUS", map));
		return supplier;
	}

}
