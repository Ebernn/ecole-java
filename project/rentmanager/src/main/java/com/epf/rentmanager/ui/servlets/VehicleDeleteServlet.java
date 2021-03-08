package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/delete")
public class VehicleDeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/delete.jsp");
		try {
			request.setAttribute("vehicle", VehicleService.getInstance().findById(Integer.parseInt(request.getParameter("id"))));
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		try {
			if (request.getParameter("confirm") != null)
				VehicleService.getInstance().delete(Integer.parseInt(request.getParameter("id").toString()));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
