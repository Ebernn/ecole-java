package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ReservationDao;

public class ReservationService {

	private ReservationDao reservationDao;
	public static ReservationService instance;
	
	private ReservationService() {
		this.reservationDao = ReservationDao.getInstance();
	}
	
	public static ReservationService getInstance() {
		if (instance == null)
			instance = new ReservationService();
		return instance;
	}
	
	
	public long create(Reservation reservation) throws ServiceException {
		try {
			return reservationDao.create(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long delete(int id) throws ServiceException {
		try {
			return reservationDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Reservation> findByClient(long clientId) throws ServiceException {
		try {
			return reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Reservation> findByVehicle(long vehicleId) throws ServiceException {
		try {
			return reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Reservation> findAll() throws ServiceException {
		try {
			return reservationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
