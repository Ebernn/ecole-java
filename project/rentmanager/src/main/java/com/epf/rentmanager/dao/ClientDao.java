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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {
	
	private ClientDao() {}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String FIND_CLIENTS_BY_VEHICLE = "SELECT * FROM Client INNER JOIN Reservation ON Reservation.client_id=Client.id WHERE Reservation.vehicle_id=?;";
	
	/**
	 * Créé un client dans la base de données
	 * @param client
	 * @return l'identifiant du client
	 * @throws DaoException
	 */
	public long create(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_QUERY);
			preparedStatement.setString(1, client.getNom());
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));			
			long id = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * Met à jour un client dans la base de données
	 * @param client
	 * @return l'identifiant du client
	 * @throws DaoException
	 */
	public long update(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(EDIT_CLIENT_QUERY);
			preparedStatement.setString(1, client.getNom());
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));
			preparedStatement.setLong(5, client.getId());
			long id = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * Supprime un client de la base de données
	 * @param l'identifiant du client
	 * @return
	 * @throws DaoException
	 */
	public long delete(long id) throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_QUERY);
		) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
	}

	/**
	 * Recherche et renvoie un client à partir de son identifiant (s'il existe)
	 * @param l'identifiant du client
	 * @return client
	 * @throws DaoException
	 */
	public Optional<Client> findById(long id) throws DaoException {
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
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
	 * Recherche et renvoie les clients liés à un véhicule
	 * @param l'identifiant du véhicule
	 * @return la liste de clients
	 * @throws DaoException
	 */
	public List<Client> findByVehicleId(long vehicle_id) throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENTS_BY_VEHICLE);
		) {
			preparedStatement.setLong(1, vehicle_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
				clients.add(instanceFromResult(resultSet));
		} catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		}
		return clients;
	}

	/**
	 * Recherche et renvoie tous les clients de la base de données
	 * @return la liste de clients
	 * @throws DaoException
	 */
	public List<Client> findAll() throws DaoException {
		 List<Client> clients = new ArrayList<Client>();
		 try (
			 Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENTS_QUERY);
			 ResultSet resultSet = preparedStatement.executeQuery();
		 ) {
			 while(resultSet.next())
				 clients.add(instanceFromResult(resultSet));
		 } catch (SQLException e) {
			 throw new DaoException(e.getMessage());
		 }		 
		 return clients;
	}
	
	/**
	 * Génère une instance de Client à partir du résultat d'une recherche dans la base de données
	 * @param résultat de la recherche
	 * @return une instance de Client
	 * @throws SQLException
	 */
	private Client instanceFromResult(ResultSet resultSet) throws SQLException {
		return new Client(
			resultSet.getLong("id"),
			resultSet.getString("nom"),
			resultSet.getString("prenom"),
			resultSet.getString("email"),
			resultSet.getDate("naissance").toLocalDate()
		);
	}

}
