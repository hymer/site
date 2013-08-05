package com.hymer.core;

import org.springframework.context.ApplicationContext;

public class SpringHelper {
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public static Object getBean(String beanId) throws Exception {
		return context.getBean(beanId);
	}

	public static <T> T getBean(Class<T> beanClass) throws Exception {
		return context.getBean(beanClass);
	}

	public static void setContext(ApplicationContext ctx) {
		context = ctx;
	}
}
