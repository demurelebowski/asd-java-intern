package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ReservationReportDto;
import team.asd.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping(value = "/reservation_report")
@ApiOperation("Reservation report API")
public class ReservationReportController {
	@Autowired
	public ReservationService reservationService;

	@ApiOperation(value = "Get a reservation report by reservationId", notes = "Returns reservation report as per the reservation id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/")
	public List<ReservationReportDto> readById(
			@RequestParam(name = "reservationId", required = false) @ApiParam(value = "reservation id", example = "1") Integer reservationId,
			@RequestParam(name = "page", required = false) @ApiParam(value = "page", example = "2") Integer page,
			@RequestParam(name = "itemsPerPage", required = false) @ApiParam(value = "items per page", example = "10") Integer itemsPerPage) {
		return reservationService.getReservationReport(reservationId, page, itemsPerPage);
	}
}
