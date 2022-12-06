package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ReservationDto;
import team.asd.entity.Reservation;
import team.asd.service.ReservationService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	@Autowired
	public ReservationService reservationService;

	@GetMapping("/{reservationId}")
	public ReservationDto readById(@PathVariable Integer reservationId) {
		Reservation reservation = reservationService.readById(reservationId);
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@PostMapping("/")
	public ReservationDto createReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = ConverterUtil.convertToReservation(reservationDto);
		reservationService.create(reservation);
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@PutMapping("/")
	public ReservationDto updateReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = ConverterUtil.convertToReservation(reservationDto);
		reservationService.update(reservation);
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@DeleteMapping("/{reservationId}")
	public Boolean deleteReservation(@PathVariable Integer reservationId) {
		return reservationService.delete(reservationId);
	}

	@GetMapping("/list")
	public List<ReservationDto> getListByParameters(@RequestParam(name = "product_id") Integer productId,
			@RequestParam(required = false, name = "organization_id") Integer organizationId,
			@RequestParam(required = false, name = "agent_id") Integer agentId) {
		List<Reservation> reservationList = reservationService.getListByParameters(productId, organizationId, agentId);
		return ConverterUtil.convertToReservationDtoList(reservationList);
	}

	@GetMapping("/list_dates")
	public List<ReservationDto> getListByDates(@RequestParam(name = "from_date") String fromDate, @RequestParam(name = "to_date") String toDate,
			@RequestParam(required = false, name = "state") String state) {
		List<Reservation> reservationList = reservationService.getListByDates(fromDate, toDate, state);
		return ConverterUtil.convertToReservationDtoList(reservationList);
	}
}
