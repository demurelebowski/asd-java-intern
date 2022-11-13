package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ReservationDto;
import team.asd.entity.Reservation;
import team.asd.service.ReservationService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	@Autowired
	public ReservationService reservationService;

	@GetMapping("/{reservationId}")
	public ReservationDto readById(@PathVariable Integer reservationId) {
		return ConverterUtil.convertToReservationDto(reservationService.readById(reservationId));
	}

	@PostMapping("/")
	public ReservationDto createReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = reservationService.create(ConverterUtil.convertToReservation(reservationDto));
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@PutMapping("/")
	public ReservationDto updateReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = reservationService.update(ConverterUtil.convertToReservation(reservationDto));
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@DeleteMapping("/{reservationId}")
	public Boolean deleteReservation(@PathVariable Integer reservationId) {
		return reservationService.delete(reservationId);
	}
}
