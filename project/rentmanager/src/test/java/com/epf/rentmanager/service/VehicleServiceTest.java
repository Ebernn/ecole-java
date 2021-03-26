package com.epf.rentmanager.service;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
	@InjectMocks
	private ReservationService reservationService;

	@Mock
	private ReservationDao reservationDao;
	
	@Test
	public void resa30days_should_fail_when_30days() {
		try {
			LocalDate now = LocalDate.now();
			List<Reservation> reservations = (List<Reservation>) Arrays.asList(
					new Reservation(3L, 1L, now.minusDays(30L), now.minusDays(26L)),
					new Reservation(0L, 1L, now.minusDays(25L), now.minusDays(21L)),
					new Reservation(2L, 1L, now.minusDays(20L), now.minusDays(16L)),
					new Reservation(2L, 1L, now.minusDays(15L), now.minusDays(11L)),
					new Reservation(3L, 1L, now.minusDays(10L), now.minusDays(6L)),
					new Reservation(1L, 1L, now.minusDays(5L), now.minusDays(0L))
				);
			when(this.reservationDao.findResaByVehicleId(1L)).thenReturn(reservations);
			Assertions.assertThrows(ServiceException.class, () -> reservationService.create(new Reservation(1L, 1L, now, now.plusDays(1L))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void resa30days_should_not_fail_when_not30days() {
		try {
			LocalDate now = LocalDate.now();
			List<Reservation> reservations = (List<Reservation>) Arrays.asList(
					new Reservation(3L, 2L, now.minusDays(30L), now.minusDays(26L)),
					new Reservation(0L, 2L, now.minusDays(25L), now.minusDays(22L)), // 21L -> 22L
					new Reservation(2L, 2L, now.minusDays(20L), now.minusDays(16L)),
					new Reservation(2L, 2L, now.minusDays(15L), now.minusDays(11L)),
					new Reservation(3L, 2L, now.minusDays(10L), now.minusDays(6L)),
					new Reservation(1L, 2L, now.minusDays(5L), now.minusDays(0L))
				);
			when(this.reservationDao.findResaByVehicleId(2L)).thenReturn(reservations);
			Assertions.assertDoesNotThrow(() -> reservationService.create(new Reservation(2L, 2L, now, now.plusDays(1L))));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

}
