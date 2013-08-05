package com.hymer.core.security.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.security.entity.User;

@Repository
public class UserDAO extends DAOHibernate<User, Long> {
	
}
