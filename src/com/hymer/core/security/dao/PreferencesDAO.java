package com.hymer.core.security.dao;

import org.springframework.stereotype.Repository;

import com.hymer.core.DAOHibernate;
import com.hymer.core.security.entity.Preferences;

@Repository
public class PreferencesDAO extends DAOHibernate<Preferences, Long> {

}
