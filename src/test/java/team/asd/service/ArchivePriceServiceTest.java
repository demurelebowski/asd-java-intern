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
import team.asd.constant.EntityType;
import team.asd.dao.ArchivePriceDaoDummy;
import team.asd.entity.ArchivePrice;
import team.asd.exceptions.ValidationException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        archivePriceMock = null;
        archivePrice.setId(-1);
    }

    @Test
    void testReadByIdNotNull() {
        when(archivePriceService.readById(1)).thenReturn(
                TestResources.getTestArchivePrice(1));
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
    void testCreateMock() {
        doAnswer(invocation -> {
            archivePriceMock = TestResources.getTestArchivePrice(1);
            return null;
        }).when(archivePriceDaoDummy).create(any(ArchivePrice.class));

        when(archivePriceDaoDummy.readById(1)).thenAnswer(invocationOnMock -> archivePriceMock);

        assertNull(archivePriceService.readById(1));
        archivePriceService.create(TestResources.getTestArchivePrice(1));
        assertNotNull(archivePriceService.readById(1));
        verify(archivePriceDaoDummy, atLeast(2)).readById(1);
        verify(archivePriceDaoDummy, atLeast(1)).create(any(ArchivePrice.class));
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
