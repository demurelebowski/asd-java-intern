package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.ReservationConfirmationDao;
import team.asd.entity.ReservationConfirmation;
import team.asd.exceptions.ValidationException;
import team.asd.util.ConverterUtil;

import java.util.List;
import java.util.Objects;

@Service
public class ReservationConfirmationService {
	private final ReservationConfirmationDao reservationConfirmationDao;

	public ReservationConfirmationService(ReservationConfirmationDao reservationConfirmationDao) {
		this.reservationConfirmationDao = reservationConfirmationDao;
	}

	public ReservationConfirmation readById(Integer id) {
		validateId(id);
		return reservationConfirmationDao.readById(id);
	}

	public void create(ReservationConfirmation reservationConfirmation) {
		validateReservationCreation(reservationConfirmation);
		reservationConfirmationDao.create(reservationConfirmation);
	}

	public void update(ReservationConfirmation reservationConfirmation) {
		validateReservation(reservationConfirmation);
		validateId(reservationConfirmation.getId());
		reservationConfirmationDao.update(reservationConfirmation);
	}

	public Boolean delete(Integer id) {
		validateId(id);
		return reservationConfirmationDao.delete(id);
	}

	public List<ReservationConfirmation> getListByReservationId(Integer reservationId) {
		return reservationConfirmationDao.getListByReservationId(reservationId);
	}

	public List<ReservationConfirmation> getListByConfirmationIdAndDateRange(String confirmationId, String dateStart, String dateEnd) {
		return reservationConfirmationDao.getListByConfirmationIdAndDateRange(confirmationId, ConverterUtil.localDateTimeFromString(dateStart),
				ConverterUtil.localDateTimeFromString(dateEnd));
	}

	public List<ReservationConfirmation> getListByFailedReservation() {
		return reservationConfirmationDao.getListByFailedReservation();
	}

	private void validateId(Integer id) {
		if (Objects.isNull(id) || id < 0) {
			throw new ValidationException("Invalid id.");
		}
	}

	private void validateReservationCreation(ReservationConfirmation reservationConfirmation) {
		validateReservation(reservationConfirmation);
		if (ObjectUtils.anyNull(reservationConfirmation.getReservationId(), reservationConfirmation.getChannelPartnerId(),
				reservationConfirmation.getConfirmationId())) {
			throw new ValidationException("One of the required parameters not found.");
		}
	}

	private void validateReservation(ReservationConfirmation reservationConfirmation) {
		if (Objects.isNull(reservationConfirmation)) {
			throw new ValidationException("reservation_confirmation is null");
		}
	}

}
