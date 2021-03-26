package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.utils.FormatChecker;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private ReservationService reservationService;
		
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			isValid(vehicle);
			return vehicleDao.create(vehicle);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long update(Vehicle vehicle) throws ServiceException {
		try {
			isValid(vehicle);
			return vehicleDao.update(vehicle);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long delete(int id) throws ServiceException {
		try {
			// Peut être davantage optimisé
			List<Reservation> reservations = reservationService.findByVehicle(id);
			for(Reservation reservation : reservations) {
				reservationService.delete(reservation.getId());
			}
			return vehicleDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id).get();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Vehicle> findByClient(long client_id) throws ServiceException {
		try {
			return vehicleDao.findByClientId(client_id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int count() throws ServiceException {
		try {
			return vehicleDao.count();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	private void isValid(Vehicle vehicle) throws ServiceException {
		if (FormatChecker.isBlank(vehicle.getConstructeur()))
			throw new ServiceException("Le constructeur est vide");
		if (FormatChecker.isBlank(vehicle.getModele()))
			throw new ServiceException("Le modèle est vide");
		if(vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9)
			throw new ServiceException("Le nombre de places du véhicule doit être compris entre 2 et 9");
	}
}
