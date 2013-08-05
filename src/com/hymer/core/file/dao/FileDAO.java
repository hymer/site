package com.hymer.core.file.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.file.entity.FileEntity;

@Repository
public class FileDAO extends DAOHibernate<FileEntity, Long> {

}
