package com.hymer.contract.dao;

import org.springframework.stereotype.Repository;

import com.hymer.contract.entity.Contract;
import com.hymer.core.DAOHibernate;

@Repository
public class ContractDAO extends DAOHibernate<Contract, Long> {

}
