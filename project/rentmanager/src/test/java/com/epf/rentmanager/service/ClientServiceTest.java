package com.epf.rentmanager.service;

/*import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.jupiter.api.Assertions;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
	@InjectMocks
	private ClientService clientService;

	@Mock
	private ClientDao clientDao;
	
	@Test
	void findAll_should_fail_when_dao_throws_exception() {
		try {
			when(this.clientDao.findAll()).thenThrow(DaoException.class);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		Assertions.assertThrows(ServiceException.class, () -> clientService.findAll());
	}

}*/
