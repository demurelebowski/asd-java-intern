package javaintern.controller;

import javaintern.dto.ReservationDto;
import javaintern.entity.Reservation;
import javaintern.service.ReservationService;
import javaintern.util.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	private final ReservationService reservationService;

	public ReservationController(@Autowired ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/{reservationId}")
	public ResponseEntity<Object> readById(@PathVariable Integer reservationId) {
		try {
			ReservationDto reservationDto = ConverterUtil.convertToReservationDto(reservationService.readById(reservationId));
			return new ResponseEntity<>(reservationDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/")
	public ReservationDto createReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = reservationService.create(ConverterUtil.convertToReservation(reservationDto));
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@PutMapping("/")
	public ResponseEntity<Object> updateReservation(@RequestBody ReservationDto reservationDto) {
		try {
			Reservation reservation = reservationService.update(ConverterUtil.convertToReservation(reservationDto));
			return new ResponseEntity<>(ConverterUtil.convertToReservationDto(reservation), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{reservationId}")
	public ResponseEntity<Object> deleteReservation(@PathVariable Integer reservationId) {
		try {
			return new ResponseEntity<>(reservationService.delete(reservationId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
