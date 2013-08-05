package com.hymer.core.workflow.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.workflow.entity.WorkflowInstance;

@Repository
public class WorkflowInstanceDAO extends DAOHibernate<WorkflowInstance, Long> {
	
}
