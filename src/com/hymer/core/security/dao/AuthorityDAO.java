package com.hymer.core.security.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.security.entity.Authority;

@Repository
public class AuthorityDAO extends DAOHibernate<Authority, Long> {
	
}
