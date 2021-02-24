package com.epf.rentmanager.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao VehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.VehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null)
			instance = new VehicleService();
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			if (vehicle.getConstructeur().isBlank() || vehicle.getNbPlaces() >= 1)
				throw new ServiceException("Constructeur vide / nb de places inférieur à 1");
			return VehicleDao.create(vehicle);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return VehicleDao.findById(id).get();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return VehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
