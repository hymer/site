package com.hymer.contract.dao;

import org.springframework.stereotype.Repository;

import com.hymer.contract.entity.ItemInContract;
import com.hymer.core.DAOHibernate;

@Repository
public class ItemInContractDAO extends DAOHibernate<ItemInContract, Long> {
}
