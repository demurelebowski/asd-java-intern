package javaintern.service;

import javaintern.dao.TestReservationDao;
import javaintern.entity.Reservation;
import javaintern.exceptions.NonValidIdException;
import javaintern.exceptions.ReservationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for ReservationService")
class ReservationServiceTest {
	private Reservation reservation;
	private static ReservationService reservationService;
	private static TestReservationDao testReservationDao = new TestReservationDao();

	@Test
	void testReadByIdInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.readById(null));
	}

	@Test
	void testCreateInCaseWhenParameterIsNull() {
		assertThrows(MissingParameterException.class, () -> reservationService.create(reservation));
	}

	@Test
	void testUpdateInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.update(reservation));
	}

	@Test
	void testDeleteInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.delete(reservation));
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
