package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.utils.FormatChecker;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {
	
	@Autowired
	private ClientDao clientDao;
	
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	public long create(Client client) throws ServiceException {
		try {
			if (FormatChecker.isBlank(client.getNom()) || FormatChecker.isBlank(client.getPrenom()))
				throw new ServiceException("Nom / prénom vide");
			if (!FormatChecker.isValidEmailAddress(client.getEmail()))
				throw new ServiceException("Format d'email invalide");
			client.setNom(client.getNom().toUpperCase());
			return clientDao.create(client);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long update(Client client) throws ServiceException {
		try {
			if (FormatChecker.isBlank(client.getNom()) || FormatChecker.isBlank(client.getPrenom()))
				throw new ServiceException("Nom / prénom vide");
			if (!FormatChecker.isValidEmailAddress(client.getEmail()))
				throw new ServiceException("Format d'email invalide");
			client.setNom(client.getNom().toUpperCase());
			return clientDao.update(client);
		} catch (ServiceException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public long delete(int id) throws ServiceException {
		try {
			return clientDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Client findById(long id) throws ServiceException {
		try {
			return clientDao.findById(id).get();
		} catch (RuntimeException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Client> findByVehicle(long vehicle_id) throws ServiceException {
		try {
			return clientDao.findByVehicleId(vehicle_id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
