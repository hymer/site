package com.hymer.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
       ServletContext context = event.getServletContext();
       try {
           initContextUtil(context);
          
       } catch (Exception ex) {
           ex.printStackTrace();
       }
    }
 
    public void contextDestroyed(ServletContextEvent sce) {
 
    }
 
      
    private void initContextUtil(ServletContext context) throws Exception{
           ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
           SpringHelper.setContext(ctx);
    }
}
