package com.multitreading.loadtesting.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.contexts.ApplicationContext;
import com.multitreading.loadtesting.factories.BeanFactory;
import com.multitreading.loadtesting.interfaces.ApplicationContextBehavior;
import com.multitreading.loadtesting.interfaces.BeanFactoryBehavior;
import com.multitreading.loadtesting.interfaces.LoadTestingControllerBehavior;

public class LoadTestingServlet extends LoadTestingHttpServlet{

	private static final long serialVersionUID = 1L;

	@Inject private LoadTestingControllerBehavior loadTestingControllerBehavior;
	
	/*
	public void init() throws ServletException {

		ApplicationContextBehavior applicationContextBehavior = new ApplicationContext();
		BeanFactoryBehavior beanFactoryBehavior = new BeanFactory(applicationContextBehavior);
		applicationContextBehavior.setBeanFactory(beanFactoryBehavior);
		
		Annotation[] annotations = new Annotation[0];
		
		this.loadTestingControllerBehavior = applicationContextBehavior.getBean(LoadTestingControllerBehavior.class, annotations);
		
		
    }*/
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		out.println("Load testing is started");
		
		loadTestingControllerBehavior.runLoadTesting();
		
	}
	
}
