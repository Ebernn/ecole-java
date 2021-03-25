package com.epf.rentmanager.ui.servlets.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5739237977544365030L;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
		try {
			// L'expression lambda serait pertinent ici mais nécessite tomcat -srouce 8 :(
			List<ReservationRow> rows = new ArrayList<ReservationRow>();
	        for (Reservation reservation : reservationService.findAll()) {
	        	rows.add(new ReservationRow(reservation, clientService, vehicleService));
	        }
			request.setAttribute("rents", rows);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}