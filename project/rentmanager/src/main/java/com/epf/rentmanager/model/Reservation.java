package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	private long id;
	private long clientId;
	private long vehicleId;
	private LocalDate debut;
	private LocalDate fin;
	
	public Reservation() {
		
	}
	
	public Reservation(long clientId, long vehicleId, LocalDate debut, LocalDate fin) {
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Reservation(long id, long clientId, long vehicleId, LocalDate debut, LocalDate fin) {
		this.id = id;
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.debut = debut;
		this.fin = fin;
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", clientId=" + clientId + ", vehicleId=" + vehicleId + ", debut=" + debut + ", fin=" + fin + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

}