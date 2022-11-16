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
	}

	@Test
	void readById() {
	}

	@Test
	void testCreateProduct() {
		assertThrows(ValidationException.class, () -> archivePriceService.create(null));
		assertThrows(ValidationException.class, () -> archivePriceService.create(new ArchivePrice()));
		assertThrows(ValidationException.class, () -> archivePriceService.create(archivePrice));
	}

	@Test
	void update() {
	}

	@Test
	void delete() {
	}
}
