package team.asd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.asd.dao.ReservationConfirmationDaoImplementation;
import team.asd.entity.ReservationConfirmation;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test methods for ReservationConfirmationService")
@ExtendWith(MockitoExtension.class)
class ReservationConfirmationServiceTest {
	private ReservationConfirmationService reservationConfirmationService;

	private ReservationConfirmation reservationConfirmation;
	@Mock
	private ReservationConfirmationDaoImplementation reservationConfirmationDaoImplementation;

	private static ReservationConfirmation reservationConfirmationMock;

	@BeforeEach
	public void initArchivePrice() {
		MockitoAnnotations.openMocks(this);
		reservationConfirmationService = new ReservationConfirmationService(reservationConfirmationDaoImplementation);
		reservationConfirmation = new ReservationConfirmation();
		reservationConfirmation.setId(-1);
		reservationConfirmationMock = null;
	}

	@Test
	void readById() {
		assertThrows(ValidationException.class, () -> reservationConfirmationService.readById(null));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.readById(-3));
	}

	@Test
	void testReadByIdNotNull() {
		when(reservationConfirmationService.readById(1)).thenReturn(TestResources.getTestResourcesReservationConfirmation(1));
		ReservationConfirmation reservationConfirmationMok = reservationConfirmationService.readById(1);
		Assertions.assertNotNull(reservationConfirmationMok);
		Assertions.assertEquals(reservationConfirmationMok.getReservationId(), 2);
		Assertions.assertEquals(reservationConfirmationMok.getConfirmationId(), "0345");
		verify(reservationConfirmationDaoImplementation, atLeast(1)).readById(any(Integer.class));
	}

	@Test
	void testReadByIdInvalid() {
		assertThrows(ValidationException.class, () -> reservationConfirmationService.readById(null));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.readById(-3));
	}

	@Test
	void testCreate() {
		assertThrows(ValidationException.class, () -> reservationConfirmationService.create(null));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.create(new ReservationConfirmation()));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.create(reservationConfirmation));
	}

	@Test
	void testCreateMock() {
		doAnswer(invocation -> {
			reservationConfirmationMock = TestResources.getTestResourcesReservationConfirmation(1);
			return null;
		}).when(reservationConfirmationDaoImplementation)
				.create(any(ReservationConfirmation.class));

		when(reservationConfirmationDaoImplementation.readById(1)).thenAnswer(invocationOnMock -> reservationConfirmationMock);

		assertNull(reservationConfirmationService.readById(1));
		reservationConfirmationService.create(TestResources.getTestResourcesReservationConfirmation(1));
		assertNotNull(reservationConfirmationService.readById(1));
		verify(reservationConfirmationDaoImplementation, atLeast(2)).readById(any(Integer.class));
		verify(reservationConfirmationDaoImplementation, atLeast(1)).create(any(ReservationConfirmation.class));
	}

	@Test
	void update() {
	}

	@Test
	void delete() {
	}
}