package javaintern.controller;

import javaintern.dao.TestReservationDao;
import javaintern.dto.ReservationDto;
import javaintern.entity.Reservation;
import javaintern.service.ReservationService;
import javaintern.util.ConverterUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	private ReservationService reservationService = new ReservationService(new TestReservationDao());

	@ResponseBody
	@GetMapping("/{reservationId}")
	public ResponseEntity<Object> readById(@PathVariable Integer reservationId) {
		try {
			ReservationDto reservationDto = ConverterUtil.convertToReservationDto(reservationService.readById(reservationId));
			return new ResponseEntity<>(reservationDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@PostMapping("/")
	public ResponseEntity<Object> createReservation(@RequestBody ReservationDto reservationDto) {
		try {
			Reservation reservation = reservationService.create(ConverterUtil.convertToReservation(reservationDto));
			return new ResponseEntity<>(ConverterUtil.convertToReservationDto(reservation), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
