package com.epf.rentmanager.ui.servlets.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/edit")
public class ClientEditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4996109534499681501L;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
		try {
			request.setAttribute("user", clientService.findById(Integer.parseInt(request.getParameter("id"))));
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			clientService.update(new Client(
					Integer.parseInt(request.getParameter("id").toString()),
					request.getParameter("nom").toString(),
					request.getParameter("prenom").toString(),
					request.getParameter("email").toString(),
					LocalDate.parse(request.getParameter("naissance").toString(), formatter)
			));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("../users");
	}
}