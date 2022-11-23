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
import team.asd.dto.ReservationConfirmationDto;
import team.asd.entity.ReservationConfirmation;
import team.asd.service.ReservationConfirmationService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reservation_confirmation")
public class ReservationConfirmationController {
	@Autowired
	public ReservationConfirmationService reservationConfirmationService;

	@GetMapping("/{reservationConfirmationId}")
	public ReservationConfirmationDto readById(@PathVariable Integer reservationConfirmationId) {
		ReservationConfirmation reservationConfirmation = reservationConfirmationService.readById(reservationConfirmationId);
		return ConverterUtil.convertToReservationConfirmationDto(reservationConfirmation);
	}

	@PostMapping("/")
	public ReservationConfirmationDto createReservationConfirmation(@RequestBody @Valid ReservationConfirmationDto reservationConfirmationDto) {
		ReservationConfirmation reservationConfirmation = ConverterUtil.convertToReservationConfirmation(reservationConfirmationDto);
		reservationConfirmationService.create(reservationConfirmation);
		return ConverterUtil.convertToReservationConfirmationDto(reservationConfirmation);
	}

	@PutMapping("/")
	public ReservationConfirmationDto updateReservationConfirmation(@RequestBody @Valid ReservationConfirmationDto reservationConfirmationDto) {
		ReservationConfirmation reservationConfirmation = ConverterUtil.convertToReservationConfirmation(reservationConfirmationDto);
		reservationConfirmationService.update(reservationConfirmation);
		return ConverterUtil.convertToReservationConfirmationDto(reservationConfirmation);
	}

	@DeleteMapping("/{reservationConfirmationId}")
	public Boolean deleteReservationConfirmation(@PathVariable Integer reservationConfirmationId) {
		return reservationConfirmationService.delete(reservationConfirmationId);
	}
}
