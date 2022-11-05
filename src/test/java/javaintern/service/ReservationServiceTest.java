package javaintern.service;

import javaintern.dao.TestReservationDao;
import javaintern.entity.Reservation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for ReservationService")
class ReservationServiceTest {
	private Reservation reservation;
	private ReservationService reservationService;
	private TestReservationDao testReservationDao = new TestReservationDao();

	@Test
	void testReadByIdInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.readById(null));
	}

	@Test
	void testCreateInCaseWhenParameterIsNull() {
		assertThrows(MissingParameterException.class, () -> reservationService.delete(reservation));
	}

	@Test
	void testUpdateInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.update(reservation));
	}

	@Test
	void testDeleteInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.update(reservation));
	}

	@BeforeEach
	private void initReservation() {
		reservation = new Reservation();
	}

	@BeforeAll
	private void initReservationService() {
		reservationService = new ReservationService(testReservationDao);
	}
}
