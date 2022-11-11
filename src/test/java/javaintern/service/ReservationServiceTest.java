package javaintern.service;

import team.asd.dao.TestReservationDao;
import team.asd.entity.Reservation;
import team.asd.exceptions.MissingParameterException;
import team.asd.exceptions.NonValidIdException;
import team.asd.exceptions.WrongParameterException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import team.asd.service.ReservationService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for ReservationService")
class ReservationServiceTest {
	private Reservation reservation;
	private static ReservationService reservationService;
	private static TestReservationDao testReservationDao = new TestReservationDao();

	@Test
	void testReadByIdInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.readById(null));
		assertThrows(NonValidIdException.class, () -> reservationService.readById(-11));
	}

	@Test
	void testCreateInCaseWhenOneOfParametersIsNull() {
		assertThrows(MissingParameterException.class, () -> reservationService.create(reservation));
	}

	@Test
	void testMethodsInCaseWhenParameterIsNull() throws NonValidIdException, MissingParameterException, WrongParameterException {
		assertNull(reservationService.create(null));
		assertNull(reservationService.update(null));
	}

	@Test
	void testCreateInCaseWhenParameterIsInvalid() {
		reservation = Reservation.builder()
				.agentId(11)
				.id(11)
				.customerId(2)
				.organizationId(6)
				.productId(-6)
				.price(666.50)
				.quote(666.33)
				.currency("USD")
				.fromDate(LocalDate.now())
				.toDate(LocalDate.now())
				.build();

		assertThrows(WrongParameterException.class, () -> reservationService.create(reservation));
	}

	@Test
	void testUpdateInCaseWhenIdParameterIsNull() {
		assertThrows(NonValidIdException.class, () -> reservationService.update(reservation));
	}

	@Test
	void testDeleteInCaseWhenIdParameterIsInvalid() {
		assertThrows(NonValidIdException.class, () -> reservationService.delete(null));
		assertThrows(NonValidIdException.class, () -> reservationService.delete(-1));
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
