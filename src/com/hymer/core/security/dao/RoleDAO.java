package com.hymer.core.security.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.security.entity.Role;

@Repository
public class RoleDAO extends DAOHibernate<Role, Long> {
	
}
