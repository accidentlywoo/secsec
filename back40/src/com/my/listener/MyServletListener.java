package com.my.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletListener implements ServletContextListener {
	
    public MyServletListener() {
    	System.out.println("MyServletListener 객체 생성됨");
    }
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("MyServletListener contextInitialized() 호출");
    	ServletContext servletContext = sce.getServletContext();
    	servletContext.setInitParameter("customerEnv", "customers.properties");
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("MyServletListener contextDestroyed() 호출");
    }
}
