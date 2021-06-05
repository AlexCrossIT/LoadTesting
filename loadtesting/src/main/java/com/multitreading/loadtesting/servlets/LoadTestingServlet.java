package com.multitreading.loadtesting.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.interfaces.LoadTestingControllerBehavior;

public class LoadTestingServlet extends LoadTestingHttpServlet{

	private static final long serialVersionUID = 1L;

	@Inject private LoadTestingControllerBehavior loadTestingControllerBehavior;
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		out.println("Load testing is started");
		
		loadTestingControllerBehavior.runLoadTesting();
		
	}
	
}
