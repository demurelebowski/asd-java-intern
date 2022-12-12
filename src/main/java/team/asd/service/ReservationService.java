package team.asd.service;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.constant.ReservationState;
import team.asd.dao.ReservationDao;
import team.asd.entity.Reservation;
import team.asd.entity.ReservationReport;
import team.asd.exceptions.ValidationException;
import team.asd.util.ConverterUtil;

import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public Reservation readById(Integer id) {
        validateId(id);
        return reservationDao.readById(id);
    }

    public void create(Reservation reservation) {
        validateReservationCreation(reservation);
        reservationDao.create(reservation);
    }

    public void update(Reservation reservation) {
        validateReservation(reservation);
        validateId(reservation.getId());
        reservationDao.update(reservation);
    }

    public Boolean delete(Integer id) {
        validateId(id);
        return reservationDao.delete(id);
    }

    public List<Reservation> getListByParameters(Integer productId, Integer organizationId, Integer agentId) {
        if (Objects.isNull(productId)) {
            throw new ValidationException("product_id not provided.");
        }
        return reservationDao.getListByParameters(productId, organizationId, agentId);
    }

    public List<Reservation> getListByDates(String fromDate, String toDate, String state) {
        return reservationDao.getListByDates(ConverterUtil.localDateFromString(fromDate), ConverterUtil.localDateFromString(toDate),
                EnumUtils.getEnumIgnoreCase(ReservationState.class, state));
    }

    public ReservationReport getReservationReport(Integer reservationId) {
        validateId(reservationId);

        return ReservationReport.builder().reservation(reservationDao.readById(reservationId)).
                archivePriceList(reservationDao.getArchivePriceList(reservationId))
                .reservationConfirmation(reservationDao.getReservationConfirmation(reservationId))
                .build();
    }

    private void validateId(Integer id) {
        if (Objects.isNull(id) || id < 0) {
            throw new ValidationException("Invalid id.");
        }
    }

    private void validateReservationCreation(Reservation reservation) {
        validateReservation(reservation);
        if (ObjectUtils.anyNull(reservation.getOrganizationId(), reservation.getCustomerId(), reservation.getAgentId(), reservation.getProductId(),
                reservation.getFromDate(), reservation.getToDate(), reservation.getPrice(), reservation.getQuote(), reservation.getCurrency())) {
            throw new ValidationException("One of the required parameters not found.");
        }
    }

    private void validateReservation(Reservation reservation) {
        if (Objects.isNull(reservation)) {
            throw new ValidationException("Reservation is null");
        }
    }
}
