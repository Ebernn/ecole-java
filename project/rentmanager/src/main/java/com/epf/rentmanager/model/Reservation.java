package com.epf.rentmanager.model;

import java.sql.Date;

public class Reservation {
	private long id;
	private long clientId;
	private long vehicleId;
	private Date debut;
	private Date fin;
	
	public Reservation() {
		
	}
	
	public Reservation(long clientId, long vehicleId, Date debut, Date fin) {
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Reservation(long id, long clientId, long vehicleId, Date debut, Date fin) {
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

	public void setId(int id) {
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

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}
}