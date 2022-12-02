package team.asd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.asd.constant.ReservationState;
import team.asd.dao.ReservationDaoDummy;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test methods for ReservationService")
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
	private static ReservationService reservationService;
	private static Reservation reservationMock;
	private Reservation reservation;
	@Mock
	private ReservationDaoDummy testReservationDao;

	@BeforeEach
	public void initArchivePrice() {
		MockitoAnnotations.openMocks(this);
		reservationService = new ReservationService(testReservationDao);
		reservation = new Reservation();
		reservation.setId(-1);
		reservationMock = null;
	}

	@Test
	void testReadByIdInCaseWhenIdParameterIsInvalid() {
		assertThrows(ValidationException.class, () -> reservationService.readById(null));
		assertThrows(ValidationException.class, () -> reservationService.readById(-11));
	}

	@Test
	void testReadByIdNotNull() {
		when(testReservationDao.readById(1)).thenReturn(TestResources.getTestResourcesReservation(1));
		Reservation reservationMock = reservationService.readById(1);
		Assertions.assertNotNull(reservationMock);
		Assertions.assertEquals(reservationMock.getAgentId(), 54);
		Assertions.assertEquals(reservationMock.getPrice(), 77.88);
		verify(testReservationDao, atLeast(1)).readById(any(Integer.class));
	}

	@Test
	void testCreateInCaseWhenOneOfParametersIsNull() {
		assertThrows(ValidationException.class, () -> reservationService.create(null));
		assertThrows(ValidationException.class, () -> reservationService.create(reservation));
	}

	@Test
	void testCreateMock() {
		doAnswer(invocation -> {
			Reservation ReservationToCreate = invocation.getArgument(0, Reservation.class);
			saveAndPopulateId(ReservationToCreate);
			return null;
		}).when(testReservationDao)
				.create(any(Reservation.class));

		assertNull(reservationMock);
		reservationService.create(TestResources.getTestResourcesReservation(4));
		assertNotNull(reservationMock);
		assertNotNull(reservationMock.getId());
		verify(testReservationDao, atLeast(1)).create(any(Reservation.class));
	}

	private void saveAndPopulateId(Reservation reservationToCreate) {
		if (Objects.isNull(reservationToCreate)) {
			return;
		}
		reservationToCreate.setId(1);
		reservationMock = reservationToCreate;
	}

	@Test
	void testUpdateInCaseWhenIdParameterIsNull() {
		assertThrows(ValidationException.class, () -> reservationService.update(null));
		assertThrows(ValidationException.class, () -> reservationService.update(reservation));
	}

	@Test
	void testUpdateMock() {
		reservationMock = TestResources.getTestResourcesReservation(2);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, Reservation.class))
				.map(this::updateReservation)
				.orElse(null)).when(testReservationDao)
				.update(any(Reservation.class));

		reservationService.update(TestResources.getTestResourcesReservation(3));
		Assertions.assertEquals(reservationMock.getPrice(), 66.5);
		Assertions.assertEquals(reservationMock.getCustomerId(), 65);

		Reservation reservationNew = TestResources.getTestResourcesReservation(2);
		reservationNew.setPrice(8.88);
		reservationNew.setCustomerId(99);
		reservationService.update(reservationNew);
		assertNotNull(reservationMock);
		Assertions.assertEquals(reservationMock.getPrice(), 8.88);
		Assertions.assertEquals(reservationMock.getCustomerId(), 99);

		verify(testReservationDao, atLeast(2)).update(any(Reservation.class));
	}

	private Reservation updateReservation(Reservation reservation) {
		if (Objects.isNull(reservation) || !reservationMock.getId()
				.equals(reservation.getId())) {
			return reservation;
		}

		if (Objects.nonNull(reservation.getOrganizationId())) {
			reservationMock.setOrganizationId(reservation.getOrganizationId());
		}
		if (Objects.nonNull(reservation.getAgentId())) {
			reservationMock.setAgentId(reservation.getAgentId());
		}
		if (Objects.nonNull(reservation.getCustomerId())) {
			reservationMock.setCustomerId(reservation.getCustomerId());
		}
		if (Objects.nonNull(reservation.getProductId())) {
			reservationMock.setProductId(reservation.getProductId());
		}
		if (Objects.nonNull(reservation.getState())) {
			reservationMock.setState(reservation.getState());
		}
		if (Objects.nonNull(reservation.getFromDate())) {
			reservationMock.setFromDate(reservation.getFromDate());
		}
		if (Objects.nonNull(reservation.getToDate())) {
			reservationMock.setToDate(reservation.getToDate());
		}
		if (Objects.nonNull(reservation.getPrice())) {
			reservationMock.setPrice(reservation.getPrice());
		}
		if (Objects.nonNull(reservation.getQuote())) {
			reservationMock.setQuote(reservation.getQuote());
		}
		if (Objects.nonNull(reservation.getGuests())) {
			reservationMock.setGuests(reservation.getGuests());
		}
		if (Objects.nonNull(reservation.getCurrency())) {
			reservationMock.setCurrency(reservation.getCurrency());
		}
		if (Objects.nonNull(reservation.getNotes())) {
			reservationMock.setNotes(reservation.getNotes());
		}
		return reservationMock;
	}

	@Test
	void testDeleteInCaseWhenIdParameterIsInvalid() {
		assertThrows(ValidationException.class, () -> reservationService.delete(null));
		assertThrows(ValidationException.class, () -> reservationService.delete(-1));
	}

	@Test
	void deleteMock() {
		reservationMock = TestResources.getTestResourcesReservation(3);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, Integer.class))
				.map(this::deleteReservation)
				.orElse(null)).when(testReservationDao)
				.delete(any(Integer.class));

		Assertions.assertEquals(reservationMock.getState(), ReservationState.FullyPaid);
		Assertions.assertEquals(reservationService.delete(3), true);
		Assertions.assertEquals(reservationMock.getState(), ReservationState.Cancelled);
		Assertions.assertEquals(reservationService.delete(1), false);

		verify(testReservationDao, atLeast(2)).delete(any(Integer.class));
	}

	private Boolean deleteReservation(Integer id) {
		if (Objects.isNull(id) || !id.equals(reservationMock.getId())) {
			return false;
		}
		reservationMock.setState(ReservationState.Cancelled);
		return true;
	}
}
