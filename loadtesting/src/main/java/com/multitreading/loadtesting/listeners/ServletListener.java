package com.multitreading.loadtesting.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.contexts.ApplicationContext;
import com.multitreading.loadtesting.factories.BeanFactory;
import com.multitreading.loadtesting.interfaces.ApplicationContextBehavior;
import com.multitreading.loadtesting.interfaces.BeanFactoryBehavior;

@WebListener
public class ServletListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		/*ApplicationContextBehavior applicationContextBehavior = null;
		BeanFactoryBehavior beanFactoryBehavior = new BeanFactory(applicationContextBehavior);
		applicationContextBehavior.setBeanFactory(beanFactoryBehavior);*/
		
	}

	
}
