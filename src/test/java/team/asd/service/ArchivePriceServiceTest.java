package team.asd.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import team.asd.dao.TestArchivePriceDao;
import team.asd.entity.ArchivePrice;
import team.asd.exceptions.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for ArchivePriceService")
class ArchivePriceServiceTest {
	private static ArchivePriceService archivePriceService;
	private ArchivePrice archivePrice;

	@BeforeAll
	public static void setUpClass() {
		archivePriceService = new ArchivePriceService(new TestArchivePriceDao());
	}

	@BeforeEach
	public void initArchivePrice() {
		archivePrice = new ArchivePrice();
		archivePrice.setEntityId(8);
	}

	@Test
	void testReadById() {
		assertThrows(ValidationException.class, () -> archivePriceService.readById(null));
		assertThrows(ValidationException.class, () -> archivePriceService.readById(-3));
	}

	@Test
	void testCreateProduct() {
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
	void delete() {
		assertThrows(ValidationException.class, () -> archivePriceService.delete(null));
		assertThrows(ValidationException.class, () -> archivePriceService.delete(-9));
	}
}
