package com.hymer.supplier.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.supplier.entity.Supplier;

@Repository
public class SupplierDAO extends DAOHibernate<Supplier, Long> {
	
}
