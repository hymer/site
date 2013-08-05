package com.hymer.core.workflow.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.workflow.entity.Node;

@Repository
public class NodeDAO extends DAOHibernate<Node, Long> {
	
}
