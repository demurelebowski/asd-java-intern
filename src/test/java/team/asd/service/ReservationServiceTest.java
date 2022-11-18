package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import team.asd.dao.ReservationDaoDummy;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for ReservationService")
class ReservationServiceTest {
	private Reservation reservation;
	private static ReservationService reservationService;
	private static final ReservationDaoDummy testReservationDao = new ReservationDaoDummy();

	@Test
	void testReadByIdInCaseWhenIdParameterIsInvalid() {
		assertThrows(ValidationException.class, () -> reservationService.readById(null));
		assertThrows(ValidationException.class, () -> reservationService.readById(-11));
	}

	@Test
	void testCreateInCaseWhenOneOfParametersIsNull() {
		assertThrows(ValidationException.class, () -> reservationService.create(reservation));
	}

	@Test
	void testUpdateInCaseWhenIdParameterIsNull() {
		assertThrows(ValidationException.class, () -> reservationService.update(reservation));
	}

	@Test
	void testDeleteInCaseWhenIdParameterIsInvalid() {
		assertThrows(ValidationException.class, () -> reservationService.delete(null));
		assertThrows(ValidationException.class, () -> reservationService.delete(-1));
	}

	@BeforeEach
	public void initReservation() {
		reservation = new Reservation();
	}

	@BeforeAll
	public static void initReservationService() {
		reservationService = new ReservationService(testReservationDao);
	}
}
