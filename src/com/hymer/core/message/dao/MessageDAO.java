package com.hymer.core.message.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.message.entity.Message;

@Repository
public class MessageDAO extends DAOHibernate<Message, Long> {

}
