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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {
	@GetMapping("/{reservationId}")
	@ResponseBody
	public ResponseEntity<Object> getReservation(@PathVariable Integer reservationId) {
		try {
			ReservationService reservationService = new ReservationService(new TestReservationDao());
			Reservation reservation = reservationService.readById(reservationId);
			ReservationDto reservationDto = ConverterUtil.convertToReservationDto(reservation);
			return new ResponseEntity<>(reservationDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
