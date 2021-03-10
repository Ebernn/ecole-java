package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/edit")
public class VehicleEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp");
		try {
			request.setAttribute("vehicle", VehicleService.getInstance().findById(Integer.parseInt(request.getParameter("id"))));
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			VehicleService.getInstance().update(new Vehicle(
				Integer.parseInt(request.getParameter("id").toString()),
				request.getParameter("manufacturer").toString(),
				request.getParameter("modele").toString(),
				Integer.parseInt(request.getParameter("seats").toString())
			));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("../cars");
	}
}
