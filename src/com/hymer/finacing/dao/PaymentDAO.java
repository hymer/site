package com.hymer.finacing.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.finacing.entity.Payment;

@Repository
public class PaymentDAO extends DAOHibernate<Payment, Long> {
	
}
