package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ReservationService;

@Repository
public class VehicleDao {
	
	@Autowired
	private ReservationService reservationService;
	
	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String EDIT_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, modele=?, nb_places=? WHERE id=?;";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLE_QUERY = "SELECT COUNT(id) AS count FROM Vehicle";
	private static final String FIND_VEHICLES_BY_CLIENT = "SELECT * FROM Vehicle INNER JOIN Reservation ON Reservation.vehicle_id=Vehicle.id WHERE Reservation.client_id=?;";

	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VEHICLE_QUERY);
			preparedStatement.setString(1, vehicle.getConstructeur());
			preparedStatement.setString(2, vehicle.getModele());
			preparedStatement.setLong(3, vehicle.getNb_places());
			long id = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	public long update(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(EDIT_VEHICLE_QUERY);
			preparedStatement.setString(1, vehicle.getConstructeur());
			preparedStatement.setString(2, vehicle.getModele());
			preparedStatement.setLong(3, vehicle.getNb_places());
			preparedStatement.setLong(4, vehicle.getId());
			long id = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}

	public long delete(long id) throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
		) {
			Iterator<Reservation> reservations = reservationService.findByVehicle(id).iterator();
			while(reservations.hasNext()) reservationService.delete((int) reservations.next().getId());
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException | ServiceException e) {
			 throw new DaoException(e.getMessage());
		}
	}

	public Optional<Vehicle> findById(long id) throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
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
	
	public List<Vehicle> findByClientId(long client_id) throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLES_BY_CLIENT);
		) {
			preparedStatement.setLong(1, client_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
				vehicles.add(instanceFromResult(resultSet));
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
		return vehicles;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
		) {
			while(resultSet.next())
				vehicles.add(instanceFromResult(resultSet));
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}		 
		return vehicles;
	}
	
	public int count() throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_VEHICLE_QUERY);
		) {
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt("count");
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	private Vehicle instanceFromResult(ResultSet resultSet) throws SQLException {
		Vehicle vehicle = new Vehicle(
			resultSet.getLong("id"),
			resultSet.getString("constructeur"),
			resultSet.getString("modele"),
			resultSet.getInt("nb_places")
		);
 	    return vehicle;
	}

}
