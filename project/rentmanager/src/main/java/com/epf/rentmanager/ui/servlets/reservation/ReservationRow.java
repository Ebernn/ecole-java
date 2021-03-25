package com.epf.rentmanager.ui.servlets.reservation;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class ReservationRow {
	private long id;
	private String vehicle;
	private String user;
	private String start;
	private String end;
	public ReservationRow(Reservation reservation, ClientService clientService, VehicleService vehicleService) throws ServiceException {
		this.id = reservation.getId();
		final Vehicle vehicle = vehicleService.findById(reservation.getVehicleId());
		this.vehicle = vehicle.getConstructeur();
		String modele = vehicle.getModele();
		if (modele != null) {
			this.vehicle += " " + modele;
		}
		final Client user = clientService.findById(reservation.getClientId());
		this.user = user.getPrenom() + " " + user.getNom();
		this.start = reservation.getDebut().toString();
		this.end = reservation.getFin().toString();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}
