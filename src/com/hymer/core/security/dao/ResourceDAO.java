package com.hymer.core.security.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.security.entity.Resource;

@Repository
public class ResourceDAO extends DAOHibernate<Resource, Long> {
	
}
