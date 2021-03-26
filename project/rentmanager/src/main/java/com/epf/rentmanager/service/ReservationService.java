package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ReservationDao;


@Service
public class ReservationService {
	public static int maxRentLength = 30;

	@Autowired
	private ReservationDao reservationDao;
	
	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	public long create(Reservation reservation) throws ServiceException {
		try {
			if (canRentNdays(reservation.getVehicleId(), maxRentLength - reservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS))) {
				return reservationDao.create(reservation);
			} else {
				throw new ServiceException("On ne peut pas réserver une même voiture " + maxRentLength + " jours de suite.");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long update(Reservation reservation) throws ServiceException {
		try {
			return reservationDao.update(reservation);
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
	
	public Reservation findById(long id) throws ServiceException {
		try {
			return reservationDao.findById(id).get();
		} catch (RuntimeException | DaoException e) {
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
	
	private boolean canRentNdays(long vehicleId, long max) {
		try {
			List<Reservation> reservations = reservationDao.findResaByVehicleId(vehicleId);
			reservations.sort((r1, r2) -> r2.getDebut().compareTo(r1.getDebut()));
			LocalDate now = LocalDate.now();
			LocalDate prevDate = now;
			for (Reservation r : reservations) {
				if (r.getDebut().until(now, ChronoUnit.DAYS) >= max) return false;
				if (prevDate.until(r.getFin(), ChronoUnit.DAYS) > 0) return true;
				prevDate = r.getDebut();
	        }
			return true;
		} catch (DaoException e) {
			e.printStackTrace();
			return false;
		}
	}
}
