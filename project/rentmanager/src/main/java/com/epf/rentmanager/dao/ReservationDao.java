package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESERVATION_QUERY);
			preparedStatement.setLong(1, reservation.getClientId());
			preparedStatement.setLong(2, reservation.getVehicleId());		
			preparedStatement.setDate(3, Date.valueOf(reservation.getDebut()));
			preparedStatement.setDate(4, Date.valueOf(reservation.getFin()));
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
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

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
		) {
			preparedStatement.setLong(1, clientId);
			while(resultSet.next())
				reservations.add(instanceFromResult(resultSet));
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}		 
		return reservations;
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
		) {
			preparedStatement.setLong(1, vehicleId);
			while(resultSet.next())
				reservations.add(instanceFromResult(resultSet));
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}		 
		return reservations;
	}

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
