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
	
	/**
	 * Créé une réservation dans la base de données
	 * @param reservation
	 * @return l'identifiant de la réservation
	 * @throws DaoException
	 */
	public long create(Reservation reservation) throws ServiceException {
		try {
			isValid(reservation);
			return reservationDao.create(reservation);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Met à jour une réservation dans la base de données
	 * @param reservation
	 * @return l'identifiant de la réservation
	 * @throws DaoException
	 */
	public long update(Reservation reservation) throws ServiceException {
		try {
			isValid(reservation);
			return reservationDao.update(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Suppression d'une réservation à partir de son identifiant
	 * @param l'identifiant de la réservation
	 * @return
	 * @throws DaoException
	 */
	public long delete(long l) throws ServiceException {
		try {
			return reservationDao.delete(l);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Recherche et renvoie une réservation à partir de son identifiant (si elle existe)
	 * @param l'identifiant de la réservation
	 * @return réservation
	 * @throws DaoException
	 */
	public Reservation findById(long id) throws ServiceException {
		try {
			return reservationDao.findById(id).get();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Recherche et renvoie l'ensemble des réservations liées à un client
	 * @param l'identifiant du client
	 * @return la liste des réservations
	 * @throws DaoException
	 */
	public List<Reservation> findByClient(long clientId) throws ServiceException {
		try {
			return reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Recherche et renvoie l'ensemble des réservations liées à un véhicule
	 * @param l'identifiant du véhicule
	 * @return la liste des réservations
	 * @throws DaoException
	 */
	public List<Reservation> findByVehicle(long vehicleId) throws ServiceException {
		try {
			return reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Recherche et renvoie toutes les réservations de la base de données
	 * @return la liste des réservations
	 * @throws DaoException
	 */
	public List<Reservation> findAll() throws ServiceException {
		try {
			return reservationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Vérifie le respect des contraintes avant d'insérer la reservation dans la base de données
	 * @param reservation
	 * @throws ServiceException
	 */
	private void isValid(Reservation reservation) throws ServiceException {
		if(!canRentToday(reservation))
			throw new ServiceException("On ne peut pas réserver une même voiture deux fois le même jour");
		if(!canRentLength(reservation))
			throw new ServiceException("On ne peut pas réserver une même voiture 7 jours de suite par le même utilisateur");
		if (!canRentNdays(reservation.getVehicleId(), maxRentLength - reservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS)))
			throw new ServiceException("On ne peut pas réserver une même voiture " + maxRentLength + " jours de suite.");
	}
	
	/**
	 * Un même client ne peut pas réserver une voiture pour plus de 7 jours
	 * @param reservation
	 * @return
	 */
	private boolean canRentLength(Reservation reservation) {
		return reservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS) < 7;
	}
	
	/**
	 * Une même voiture ne peut être réservée deux fois dans la même journée
	 * @param reservation
	 * @return
	 */
	private boolean canRentToday(Reservation reservation) {
		List<Reservation> reservations;
		try {
			reservations = this.findByVehicle(reservation.getVehicleId());
			for (Reservation ireservation : reservations) {
	            if (ireservation.getFin().until(reservation.getFin(), ChronoUnit.DAYS) > 0 && reservation.getDebut().until(ireservation.getFin(), ChronoUnit.DAYS) > 0 ||
	            		reservation.getFin().until(ireservation.getFin(), ChronoUnit.DAYS) > 0 && ireservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS) > 0) return false;
	        }
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Un véhicule ne peut être réservée pour plus de X jours consécutifs
	 * @param l'identifiant du véhicule
	 * @param X
	 * @return
	 */
	private boolean canRentNdays(long vehicleId, long max) {
		try {
			List<Reservation> reservations = reservationDao.findResaByVehicleId(vehicleId);
			reservations.sort((r1, r2) -> r2.getDebut().compareTo(r1.getDebut()));
			LocalDate now = LocalDate.now();
			LocalDate prevDate = now;
			for (Reservation r : reservations) {
				if (r.getDebut().until(now, ChronoUnit.DAYS) >= max) return false;
				if (r.getFin().until(prevDate, ChronoUnit.DAYS) > 1) return true;
				prevDate = r.getDebut();
	        }
			return true;
		} catch (DaoException e) {
			e.printStackTrace();
			return false;
		}
	}
}
