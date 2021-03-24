package com.epf.rentmanager.ui.servlets.vehicle;

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
		try {
			VehicleService.getInstance().delete(Integer.parseInt(request.getParameter("id").toString()));
		} catch (NumberFormatException | ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("../cars");
	}
}
