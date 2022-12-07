package team.asd.service;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.dao.ArchivePriceDao;
import team.asd.entity.ArchivePrice;
import team.asd.exceptions.ValidationException;
import team.asd.util.ConverterUtil;

import java.util.List;
import java.util.Objects;

@Service
public class ArchivePriceService {
	private final ArchivePriceDao archivePriceDao;

	public ArchivePriceService(ArchivePriceDao archivePriceDao) {
		this.archivePriceDao = archivePriceDao;
	}

	public ArchivePrice readById(Integer id) {
		validateId(id);
		return archivePriceDao.readById(id);
	}

	public void create(ArchivePrice archivePrice) {
		validateArchivePriceCreation(archivePrice);
		archivePriceDao.create(archivePrice);
	}

	public void update(ArchivePrice archivePrice) {
		validateArchivePrice(archivePrice);
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
		validateArchivePrice(archivePrice);
		if (ObjectUtils.anyNull(archivePrice.getEntityId(), archivePrice.getName())) {
			throw new ValidationException("One of the required parameters not found.");
		}
	}

	private void validateArchivePrice(ArchivePrice archivePrice) {
		if (Objects.isNull(archivePrice)) {
			throw new ValidationException("Archive price is null");
		}
	}

	public void createList(List<ArchivePrice> archivePriceList) {
		archivePriceList.forEach(this::validateArchivePriceCreation);
		archivePriceDao.createList(archivePriceList);
	}

	public List<ArchivePrice> getListByReservationId(Integer reservationId) {
		return archivePriceDao.getListByReservationId(reservationId);
	}

	public List<ArchivePrice> getListByParameters(String type, String state, String name) {
		if (ObjectUtils.allNull(type, state, name)) {
			throw new ValidationException("At least one parameter should be not null");
		}

		return archivePriceDao.getListByParameters(EnumUtils.getEnumIgnoreCase(ArchivePriceType.class, type),
				EnumUtils.getEnumIgnoreCase(ArchivePriceState.class, state), name);
	}

	public List<ArchivePrice> getListByReservationFromDateAtLeast(String reservationFromDateAtLeast) {
		return archivePriceDao.getListByReservationFromDateAtLeast(ConverterUtil.localDateFromString(reservationFromDateAtLeast));
	}
}
