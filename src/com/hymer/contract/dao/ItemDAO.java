package com.hymer.contract.dao;

import org.springframework.stereotype.Repository;

import com.hymer.contract.entity.Item;
import com.hymer.core.DAOHibernate;

@Repository
public class ItemDAO extends DAOHibernate<Item, Long> {
}
