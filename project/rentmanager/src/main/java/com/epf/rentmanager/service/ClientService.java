package com.epf.rentmanager.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null)
			instance = new ClientService();
		return instance;
	}
	
	
	public long create(Client client) throws ServiceException {
		try {
			if (client.getNom().isBlank() || client.getPrenom().isBlank())
				throw new ServiceException("Nom / prénom vide");
			client.setNom(client.getNom().toUpperCase());
			return clientDao.create(client);
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

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
