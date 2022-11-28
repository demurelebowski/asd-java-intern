package team.asd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.constant.EntityType;
import team.asd.dao.ArchivePriceDaoDummy;
import team.asd.entity.ArchivePrice;
import team.asd.exceptions.ValidationException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Test methods for ArchivePriceService")
@ExtendWith(MockitoExtension.class)
class ArchivePriceServiceTest {

	private ArchivePriceService archivePriceService;

	private ArchivePrice archivePrice;
	@Mock
	private ArchivePriceDaoDummy archivePriceDaoDummy;

	@BeforeEach
	public void initArchivePrice() {
		archivePriceService = new ArchivePriceService(archivePriceDaoDummy);
		archivePrice = new ArchivePrice();
		archivePrice.setId(-1);
	}

	@Test
	void testReadByIdNotNull() {
		when(archivePriceService.readById(1)).thenReturn(
				new ArchivePrice(1, EntityType.Payment, 4, "Mok", ArchivePriceState.Created, ArchivePriceType.Price, 9.9, new Date()));
		ArchivePrice archivePriceMok = archivePriceService.readById(1);
		Assertions.assertNotNull(archivePriceMok);
		Assertions.assertEquals(archivePriceMok.getName(), "Mok");
		Assertions.assertEquals(archivePriceMok.getValue(), 9.9);
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
	void update() {
		assertThrows(ValidationException.class, () -> archivePriceService.update(null));
		assertThrows(ValidationException.class, () -> archivePriceService.update(new ArchivePrice()));
		assertThrows(ValidationException.class, () -> archivePriceService.update(archivePrice));
	}

	@Test
	void deleteValid() {
		when(archivePriceService.delete(11)).thenReturn(true);
		Boolean deleteSuccess = archivePriceService.delete(11);
		Assertions.assertNotNull(deleteSuccess);
		Assertions.assertEquals(deleteSuccess, true);
	}

	@Test
	void delete() {
		assertThrows(ValidationException.class, () -> archivePriceService.delete(null));
		assertThrows(ValidationException.class, () -> archivePriceService.delete(-9));
	}
}
