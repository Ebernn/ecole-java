package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	
	/**
	 * Créé une réservation dans la base de données
	 * @param reservation
	 * @return l'identifiant de la réservation
	 * @throws DaoException
	 */
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESERVATION_QUERY);
			preparedStatement.setLong(1, reservation.getClientId());
			preparedStatement.setLong(2, reservation.getVehicleId());		
			preparedStatement.setDate(3, Date.valueOf(reservation.getDebut()));
			preparedStatement.setDate(4, Date.valueOf(reservation.getFin()));
			long id = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * Met à jour une réservation dans la base de données
	 * @param reservation
	 * @return l'identifiant de la réservation
	 * @throws DaoException
	 */
	public long update(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(EDIT_RESERVATION_QUERY);
			preparedStatement.setLong(1, reservation.getClientId());
			preparedStatement.setLong(2, reservation.getVehicleId());
			preparedStatement.setDate(3, Date.valueOf(reservation.getDebut()));
			preparedStatement.setDate(4, Date.valueOf(reservation.getFin()));
			preparedStatement.setLong(5, reservation.getId());
			long id = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * Suppression d'une réservation à partir de son identifiant
	 * @param l'identifiant de la réservation
	 * @return
	 * @throws DaoException
	 */
	public long delete(long id) throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
		) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * Recherche et renvoie une réservation à partir de son identifiant (si elle existe)
	 * @param l'identifiant de la réservation
	 * @return réservation
	 * @throws DaoException
	 */
	public Optional<Reservation> findById(long id) throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_QUERY);
		) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return Optional.ofNullable(instanceFromResult(resultSet));
			return Optional.empty();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * Recherche et renvoie l'ensemble des réservations liées à un client
	 * @param l'identifiant du client
	 * @return la liste des réservations
	 * @throws DaoException
	 */
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			preparedStatement.setLong(1, clientId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
				reservations.add(instanceFromResult(resultSet));
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}		 
		return reservations;
	}
	
	/**
	 * Recherche et renvoie l'ensemble des réservations liées à un véhicule
	 * @param l'identifiant du véhicule
	 * @return la liste des réservations
	 * @throws DaoException
	 */
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			preparedStatement.setLong(1, vehicleId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
				reservations.add(instanceFromResult(resultSet));
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}		 
		return reservations;
	}

	/**
	 * Recherche et renvoie toutes les réservations de la base de données
	 * @return la liste des réservations
	 * @throws DaoException
	 */
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
		) {
			while(resultSet.next())
				reservations.add(instanceFromResult(resultSet));
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}		 
		return reservations;
	}
	
	/**
	 * Génère une instance de Reservation à partir du résultat d'une recherche dans la base de données
	 * @param résultat de la recherche
	 * @return une instance de Reservation
	 * @throws SQLException
	 */
	private Reservation instanceFromResult(ResultSet resultSet) throws SQLException {
		return new Reservation(
			resultSet.getLong("id"),
			resultSet.getLong("client_id"),
			resultSet.getLong("vehicle_id"),
			resultSet.getDate("debut").toLocalDate(),
			resultSet.getDate("fin").toLocalDate()
		);
	}

}
