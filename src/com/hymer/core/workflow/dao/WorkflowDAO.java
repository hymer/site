package com.hymer.core.workflow.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.workflow.entity.Workflow;

@Repository
public class WorkflowDAO extends DAOHibernate<Workflow, Long> {
	
}
