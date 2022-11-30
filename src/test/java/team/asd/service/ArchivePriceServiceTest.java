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
import team.asd.constant.ArchivePriceType;
import team.asd.dao.ArchivePriceDaoDummy;
import team.asd.entity.ArchivePrice;
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
		when(archivePriceDaoDummy.readById(1)).thenReturn(TestResources.getTestArchivePrice(1));
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
			ArchivePrice archivePriceToCreate = invocation.getArgument(0, ArchivePrice.class);
			saveAndPopulateId(archivePriceToCreate);
			return null;
		}).when(archivePriceDaoDummy)
				.create(any(ArchivePrice.class));

		assertNull(archivePriceMock);
		archivePriceService.create(TestResources.getTestArchivePrice(4));
		assertNotNull(archivePriceMock);
		assertNotNull(archivePriceMock.getId());
		verify(archivePriceDaoDummy, atLeast(1)).create(any(ArchivePrice.class));
	}

	private void saveAndPopulateId(ArchivePrice archivePriceToCreate) {
		if (Objects.isNull(archivePriceToCreate)) {
			return;
		}
		archivePriceToCreate.setId(1);
		archivePriceMock = archivePriceToCreate;
	}

	@Test
	void update() {
		assertThrows(ValidationException.class, () -> archivePriceService.update(null));
		assertThrows(ValidationException.class, () -> archivePriceService.update(new ArchivePrice()));
		assertThrows(ValidationException.class, () -> archivePriceService.update(archivePrice));
	}

	@Test
	void testUpdateMock() {
		archivePriceMock = TestResources.getTestArchivePrice(2);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, ArchivePrice.class))
				.map(this::updateArchivePrice)
				.orElse(null)).when(archivePriceDaoDummy)
				.update(any(ArchivePrice.class));

		archivePriceService.update(TestResources.getTestArchivePrice(3));
		Assertions.assertEquals(archivePriceMock.getName(), "Non");
		Assertions.assertEquals(archivePriceMock.getType(), ArchivePriceType.Fee);

		ArchivePrice archivePriceNew = TestResources.getTestArchivePrice(2);
		archivePriceNew.setName("New");
		archivePriceNew.setType(ArchivePriceType.Price);
		archivePriceService.update(archivePriceNew);
		assertNotNull(archivePriceMock);
		Assertions.assertEquals(archivePriceMock.getName(), "New");
		Assertions.assertEquals(archivePriceMock.getType(), ArchivePriceType.Price);

		verify(archivePriceDaoDummy, atLeast(2)).update(any(ArchivePrice.class));
	}

	private ArchivePrice updateArchivePrice(ArchivePrice archivePrice) {
		if (Objects.isNull(archivePrice) || !archivePriceMock.getId()
				.equals(archivePrice.getId())) {
			return archivePrice;
		}

		if (!Objects.isNull(archivePrice.getName())) {
			archivePriceMock.setName(archivePrice.getName());
		}
		if (!Objects.isNull(archivePrice.getType())) {
			archivePriceMock.setType(archivePrice.getType());
		}
		if (!Objects.isNull(archivePrice.getEntityId())) {
			archivePriceMock.setEntityId(archivePrice.getEntityId());
		}
		if (!Objects.isNull(archivePrice.getValue())) {
			archivePriceMock.setValue(archivePrice.getValue());
		}
		if (!Objects.isNull(archivePrice.getState())) {
			archivePriceMock.setState(archivePrice.getState());
		}
		if (!Objects.isNull(archivePrice.getEntityType())) {
			archivePriceMock.setEntityType(archivePrice.getEntityType());
		}
		return archivePriceMock;
	}

	@Test
	void deleteMock() {
		archivePriceMock = TestResources.getTestArchivePrice(3);

		doAnswer(invocation -> Optional.ofNullable(invocation.getArgument(0, Integer.class))
				.map(this::deleteArchivePrice)
				.orElse(null)).when(archivePriceDaoDummy)
				.delete(any(Integer.class));

		Assertions.assertEquals(archivePriceMock.getState(), ArchivePriceState.Created);
		Assertions.assertEquals(archivePriceService.delete(3), true);
		Assertions.assertEquals(archivePriceMock.getState(), ArchivePriceState.Final);
		Assertions.assertEquals(archivePriceService.delete(1), false);

		verify(archivePriceDaoDummy, atLeast(2)).delete(any(Integer.class));
	}

	private Boolean deleteArchivePrice(Integer id) {
		if (Objects.isNull(id) || !id.equals(archivePriceMock.getId())) {
			return false;
		}
		archivePriceMock.setState(ArchivePriceState.Final);
		return true;
	}

	@Test
	void delete() {
		assertThrows(ValidationException.class, () -> archivePriceService.delete(null));
		assertThrows(ValidationException.class, () -> archivePriceService.delete(-9));
	}
}
