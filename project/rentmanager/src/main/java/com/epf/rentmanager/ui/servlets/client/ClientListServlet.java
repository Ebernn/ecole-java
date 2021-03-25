package com.epf.rentmanager.ui.servlets.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.ClientService;

@WebServlet("/users")
public class ClientListServlet extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2123408241137935308L;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
		try {
			request.setAttribute("users", clientService.findAll());
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
