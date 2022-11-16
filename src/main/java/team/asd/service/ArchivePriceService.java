package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import team.asd.dao.ArchivePriceDao;
import team.asd.entity.ArchivePrice;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;

import java.util.Objects;

public class ArchivePriceService {
	private final ArchivePriceDao archivePriceDao;

	public ArchivePriceService(ArchivePriceDao archivePriceDao) {
		this.archivePriceDao = archivePriceDao;
	}

	public Reservation readById(Integer id) {
		validateId(id);
		return archivePriceDao.readById(id);
	}

	public void create(ArchivePrice archivePrice) {
		validateArchivePriceCreation(archivePrice);
		archivePriceDao.create(archivePrice);
	}

	public void update(ArchivePrice archivePrice) {
		validateId(archivePrice.getId());
		archivePriceDao.update(archivePrice);
	}

	public Boolean delete(Integer id) {
		validateId(id);
		return archivePriceDao.delete(id);
	}

	private void validateId(Integer id) {
		if (Objects.isNull(id) || id < 0) {
			throw new ValidationException("Invalid id.");
		}
	}

	private void validateArchivePriceCreation(ArchivePrice archivePrice) {
		if (Objects.isNull(archivePrice)) {
			throw new ValidationException("Archive price is null");
		}
		if (ObjectUtils.anyNull(archivePrice.getEntityId(), archivePrice.getName())) {
			throw new ValidationException("One of the required parameters not found.");
		}
	}
}
