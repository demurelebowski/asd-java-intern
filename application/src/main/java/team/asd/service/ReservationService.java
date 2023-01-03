package team.asd.service;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.constant.ReservationState;
import team.asd.dao.PartyDao;
import team.asd.dao.ProductDao;
import team.asd.dao.ReservationDao;
import team.asd.dto.ReservationDto;
import team.asd.dto.ReservationReportDto;
import team.asd.dto.ReservationUserReadableDto;
import team.asd.entity.Product;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;
import team.asd.util.ConverterUtil;

import java.util.List;
import java.util.Objects;

import team.asd.entity.Party;

@Service
public class ReservationService {
	private final ReservationDao reservationDao;
	private PartyDao partyDao;
	private ProductDao productDao;

	public ReservationService(ReservationDao reservationDao, PartyDao partyDao, ProductDao productDao) {
		this.reservationDao = reservationDao;
		this.partyDao = partyDao;
		this.productDao = productDao;
	}

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

	public List<ReservationReportDto> getReservationReport(Integer reservationId, Integer page, Integer itemsPerPage) {
		Integer firstRow = null;
		if (ObjectUtils.allNotNull(page, itemsPerPage)) {
			firstRow = (page * itemsPerPage) - itemsPerPage;
		}
		return reservationDao.getReservationReport(reservationId, firstRow, itemsPerPage);
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

	public ReservationUserReadableDto readReservationUserReadableDtoById(Integer reservationId) {
		validateId(reservationId);
		Reservation reservation = reservationDao.readById(reservationId);
		if (Objects.isNull(reservation)) {
			return null;
		}
		Party organization = partyDao.readById(reservation.getOrganizationId());
		Party agent = partyDao.readById(reservation.getAgentId());
		Party customer = partyDao.readById(reservation.getCustomerId());
		Product product = productDao.readById(reservation.getProductId());

		ReservationDto reservationDto = ConverterUtil.convertToReservationDto(reservation);
		ReservationUserReadableDto reservationUserReadableDto = ReservationUserReadableDto.builder()
				.id(reservationDto.getId())
				.price(reservationDto.getPrice())
				.currency(reservationDto.getCurrency())
				.quote(reservationDto.getQuote())
				.fromDate(reservationDto.getFromDate())
				.toDate(reservationDto.getToDate())
				.guests(reservationDto.getGuests())
				.notes(reservationDto.getNotes())
				.build();

		if (organization != null) {
			reservationUserReadableDto.setOrganizationName(organization.getName());
		}
		if (agent != null) {
			reservationUserReadableDto.setAgentName(agent.getName());
		}
		if (customer != null) {
			reservationUserReadableDto.setCustomerName(customer.getName());
		}
		if (product != null) {
			reservationUserReadableDto.setProductName(product.getName());
		}
		return reservationUserReadableDto;
	}
}
