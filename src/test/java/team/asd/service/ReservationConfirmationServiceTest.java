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

@DisplayName("Test methods for ReservationConfirmationService")
@ExtendWith(MockitoExtension.class)
class ReservationConfirmationServiceTest {
	private ReservationConfirmationService reservationConfirmationService;

	private ReservationConfirmation reservationConfirmation;
	@Mock
	private ReservationConfirmationDaoImplementation reservationConfirmationDaoImplementation;

	private static ReservationConfirmation reservationConfirmationMock;

	@BeforeEach
	public void init() {
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
		when(reservationConfirmationDaoImplementation.readById(1)).thenReturn(TestResources.getTestResourcesReservationConfirmation(1));
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
			ReservationConfirmation reservationConfirmationToCreate = invocation.getArgument(0, ReservationConfirmation.class);
			saveAndPopulateId(reservationConfirmationToCreate);
			return null;
		}).when(reservationConfirmationDaoImplementation)
				.create(any(ReservationConfirmation.class));

		assertNull(reservationConfirmationMock);
		reservationConfirmationService.create(TestResources.getTestResourcesReservationConfirmation(4));
		assertNotNull(reservationConfirmationMock);
		assertNotNull(reservationConfirmationMock.getId());
		verify(reservationConfirmationDaoImplementation, atLeast(1)).create(any(ReservationConfirmation.class));
	}

	private void saveAndPopulateId(ReservationConfirmation reservationConfirmationToCreate) {
		if (Objects.isNull(reservationConfirmationToCreate)) {
			return;
		}
		reservationConfirmationToCreate.setId(1);
		reservationConfirmationMock = reservationConfirmationToCreate;
	}

	@Test
	void update() {
		assertThrows(ValidationException.class, () -> reservationConfirmationService.update(null));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.update(new ReservationConfirmation()));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.update(reservationConfirmation));
	}

	@Test
	void testUpdateMock() {
		reservationConfirmationMock = TestResources.getTestResourcesReservationConfirmation(3);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, ReservationConfirmation.class))
				.map(this::updateReservationConfirmation)
				.orElse(null)).when(reservationConfirmationDaoImplementation)
				.update(any(ReservationConfirmation.class));

		reservationConfirmationService.update(TestResources.getTestResourcesReservationConfirmation(2));
		Assertions.assertEquals(reservationConfirmationMock.getReservationId(), 22);
		Assertions.assertEquals(reservationConfirmationMock.getConfirmationId(), "5333");

		ReservationConfirmation reservationConfirmationNew = TestResources.getTestResourcesReservationConfirmation(3);
		reservationConfirmationNew.setReservationId(55);
		reservationConfirmationNew.setConfirmationId("666");
		reservationConfirmationService.update(reservationConfirmationNew);
		assertNotNull(reservationConfirmationMock);
		Assertions.assertEquals(reservationConfirmationMock.getReservationId(), 55);
		Assertions.assertEquals(reservationConfirmationMock.getConfirmationId(), "666");

		verify(reservationConfirmationDaoImplementation, atLeast(2)).update(any(ReservationConfirmation.class));
	}

	private ReservationConfirmation updateReservationConfirmation(ReservationConfirmation reservationConfirmation) {
		if (Objects.isNull(reservationConfirmation) || !reservationConfirmationMock.getId()
				.equals(reservationConfirmation.getId())) {
			return reservationConfirmation;
		}
		if (!Objects.isNull(reservationConfirmation.getReservationId())) {
			reservationConfirmationMock.setReservationId(reservationConfirmation.getReservationId());
		}
		if (!Objects.isNull(reservationConfirmation.getConfirmationId())) {
			reservationConfirmationMock.setConfirmationId(reservationConfirmation.getConfirmationId());
		}
		if (!Objects.isNull(reservationConfirmation.getChannelPartnerId())) {
			reservationConfirmationMock.setChannelPartnerId(reservationConfirmation.getChannelPartnerId());
		}
		if (!Objects.isNull(reservationConfirmation.getCreatedDate())) {
			reservationConfirmationMock.setCreatedDate(reservationConfirmation.getCreatedDate());
		}

		return reservationConfirmationMock;
	}

	@Test
	void deleteMock() {
		reservationConfirmationMock = TestResources.getTestResourcesReservationConfirmation(3);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, Integer.class))
				.map(this::deleteReservationConfirmation)
				.orElse(null)).when(reservationConfirmationDaoImplementation)
				.delete(any(Integer.class));

		reservationConfirmationService.delete(45);
		assertNotNull(reservationConfirmationMock);

		reservationConfirmationService.delete(3);
		assertNull(reservationConfirmationMock);

		verify(reservationConfirmationDaoImplementation, atLeast(2)).delete(any(Integer.class));
	}

	private Boolean deleteReservationConfirmation(Integer id) {
		if (Objects.isNull(id) || !id.equals(reservationConfirmationMock.getId())) {
			return false;
		}
		reservationConfirmationMock = null;
		return true;
	}

	@Test
	void delete() {
		assertThrows(ValidationException.class, () -> reservationConfirmationService.delete(null));
		assertThrows(ValidationException.class, () -> reservationConfirmationService.delete(-9));
	}
}
