package com.hymer.customer.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.customer.entity.Customer;

@Repository
public class CustomerDAO extends DAOHibernate<Customer, Long> {
	
}
