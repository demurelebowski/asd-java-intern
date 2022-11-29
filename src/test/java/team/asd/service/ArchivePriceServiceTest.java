package team.asd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.asd.constant.ArchivePriceState;
import team.asd.dao.ArchivePriceDaoDummy;
import team.asd.entity.ArchivePrice;
import team.asd.exceptions.ValidationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test methods for ArchivePriceService")
@ExtendWith(MockitoExtension.class)
class ArchivePriceServiceTest {

	private ArchivePriceService archivePriceService;

	private ArchivePrice archivePrice;
	@Mock
	private ArchivePriceDaoDummy archivePriceDaoDummy;

	private static ArchivePrice archivePriceMock;

	@BeforeEach
	public void initArchivePrice() {
		MockitoAnnotations.openMocks(this);
		archivePriceService = new ArchivePriceService(archivePriceDaoDummy);
		archivePrice = new ArchivePrice();
		archivePrice.setId(-1);
		archivePriceMock = null;
	}

	@Test
	void testReadByIdNotNull() {
		when(archivePriceService.readById(1)).thenReturn(TestResources.getTestArchivePrice(1));
		ArchivePrice archivePriceMok = archivePriceService.readById(1);
		Assertions.assertNotNull(archivePriceMok);
		Assertions.assertEquals(archivePriceMok.getName(), "Mok");
		Assertions.assertEquals(archivePriceMok.getValue(), 9.9);
		verify(archivePriceDaoDummy, atLeast(1)).readById(any(Integer.class));
	}

	@Test
	void testReadByIdInvalid() {
		assertThrows(ValidationException.class, () -> archivePriceService.readById(null));
		assertThrows(ValidationException.class, () -> archivePriceService.readById(-3));
	}

	@Test
	void testCreate() {
		assertThrows(ValidationException.class, () -> archivePriceService.create(null));
		assertThrows(ValidationException.class, () -> archivePriceService.create(new ArchivePrice()));
		assertThrows(ValidationException.class, () -> archivePriceService.create(archivePrice));
	}

	@Test
	void testCreateMock() {
		doAnswer(invocation -> {
			archivePriceMock = TestResources.getTestArchivePrice(1);
			return null;
		}).when(archivePriceDaoDummy)
				.create(any(ArchivePrice.class));

		when(archivePriceDaoDummy.readById(1)).thenAnswer(invocationOnMock -> archivePriceMock);

		assertNull(archivePriceService.readById(1));
		archivePriceService.create(TestResources.getTestArchivePrice(1));
		assertNotNull(archivePriceService.readById(1));
		verify(archivePriceDaoDummy, atLeast(2)).readById(any(Integer.class));
		verify(archivePriceDaoDummy, atLeast(1)).create(any(ArchivePrice.class));
	}

	@Test
	void update() {
		assertThrows(ValidationException.class, () -> archivePriceService.update(null));
		assertThrows(ValidationException.class, () -> archivePriceService.update(new ArchivePrice()));
		assertThrows(ValidationException.class, () -> archivePriceService.update(archivePrice));
	}

	@Test
	void testUpdateMock() {
		doAnswer(invocation -> {
			archivePriceMock = TestResources.getTestArchivePrice(2);
			return null;
		}).when(archivePriceDaoDummy)
				.create(any(ArchivePrice.class));

		when(archivePriceDaoDummy.readById(2)).thenAnswer(invocationOnMock -> archivePriceMock);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, ArchivePrice.class))
				.map(this::updateNameArchivePrice)
				.orElse(null)).when(archivePriceDaoDummy)
				.update(any(ArchivePrice.class));

		assertNull(archivePriceService.readById(2));
		archivePriceService.create(TestResources.getTestArchivePrice(2));
		assertNotNull(archivePriceService.readById(2));
		Assertions.assertEquals(archivePriceService.readById(2)
				.getName(), "Non");
		ArchivePrice archivePriceNew = TestResources.getTestArchivePrice(2);
		archivePriceNew.setName("New");
		archivePriceService.update(archivePriceNew);
		assertNotNull(archivePriceService.readById(2));
		Assertions.assertEquals(archivePriceService.readById(2)
				.getName(), "New");
		verify(archivePriceDaoDummy, atLeast(1)).update(any(ArchivePrice.class));
		verify(archivePriceDaoDummy, atLeast(3)).readById(any(Integer.class));
	}

	private ArchivePrice updateNameArchivePrice(ArchivePrice archivePrice) {
		archivePriceMock.setName(archivePrice.getName());
		return archivePriceMock;
	}

	@Test
	void deleteValid() {
		when(archivePriceService.delete(11)).thenReturn(true);
		Boolean deleteSuccess = archivePriceService.delete(11);
		Assertions.assertNotNull(deleteSuccess);
		Assertions.assertEquals(deleteSuccess, true);

		doAnswer(invocation -> {
			archivePriceMock = TestResources.getTestArchivePrice(2);
			return null;
		}).when(archivePriceDaoDummy)
				.create(any(ArchivePrice.class));
		doAnswer(invocation -> {
			archivePriceMock.setState(ArchivePriceState.Final);
			return true;
		}).when(archivePriceDaoDummy)
				.delete(any(Integer.class));
		when(archivePriceDaoDummy.readById(2)).thenAnswer(invocationOnMock -> archivePriceMock);

		archivePriceService.create(TestResources.getTestArchivePrice(2));
		Assertions.assertNotEquals(archivePriceService.readById(2)
				.getState(), ArchivePriceState.Final);
		deleteSuccess = archivePriceService.delete(2);
		Assertions.assertEquals(archivePriceService.readById(2)
				.getState(), ArchivePriceState.Final);
		Assertions.assertEquals(deleteSuccess, true);
		verify(archivePriceDaoDummy, atLeast(2)).delete(any(Integer.class));
	}

	@Test
	void delete() {
		assertThrows(ValidationException.class, () -> archivePriceService.delete(null));
		assertThrows(ValidationException.class, () -> archivePriceService.delete(-9));
	}
}
