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
	
	/**
	 * Créé un véhicule dans la base de données
	 * @param véhicule
	 * @return l'identifiant du véhicule
	 * @throws DaoException
	 */
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			isValid(vehicle);
			return vehicleDao.create(vehicle);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Met à jour un véhicule dans la base de données
	 * @param véhicule
	 * @return l'identifiant du véhicule
	 * @throws DaoException
	 */
	public long update(Vehicle vehicle) throws ServiceException {
		try {
			isValid(vehicle);
			return vehicleDao.update(vehicle);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Supprime un véhicule de la base de données
	 * @param l'identifiant du véhicule
	 * @return
	 * @throws DaoException
	 */
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

	/**
	 * Recherche et renvoie un véhicule à partir de son identifiant (s'il existe)
	 * @param l'identifiant du véhicule
	 * @return client
	 * @throws DaoException
	 */
	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id).get();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Recherche et renvoie les véhicules liés à un client
	 * @param l'identifiant du client
	 * @return la liste des véhicules
	 * @throws DaoException
	 */
	public List<Vehicle> findByClient(long client_id) throws ServiceException {
		try {
			return vehicleDao.findByClientId(client_id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Recherche et renvoie tous les véhicules de la base de données
	 * @return la liste des véhicules
	 * @throws DaoException
	 */
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Compte le nombre de véhicules dans la base de données
	 * @return le nombre de véhicules dans la base de données
	 * @throws DaoException
	 */
	public int count() throws ServiceException {
		try {
			return vehicleDao.count();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Vérifie le respect des contraintes avant d'insérer le véhicule dans la base de données
	 * @param véhicule
	 * @throws ServiceException
	 */
	private void isValid(Vehicle vehicle) throws ServiceException {
		if (FormatChecker.isBlank(vehicle.getConstructeur()))
			throw new ServiceException("Le constructeur est vide");
		if (FormatChecker.isBlank(vehicle.getModele()))
			throw new ServiceException("Le modèle est vide");
		if(vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9)
			throw new ServiceException("Le nombre de places du véhicule doit être compris entre 2 et 9");
	}
}
