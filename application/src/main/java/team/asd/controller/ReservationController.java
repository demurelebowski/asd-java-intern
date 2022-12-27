package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import team.asd.dto.ReservationUserReadableDto;
import team.asd.entity.Reservation;
import team.asd.service.ReservationService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation")
@ApiOperation("Reservation API")
public class ReservationController {
	@Autowired
	public ReservationService reservationService;

	@ApiOperation(value = "Get a reservation by id", notes = "Returns reservation as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/{reservationId}")
	public ReservationDto readById(@PathVariable @ApiParam(value = "Reservation id", example = "3") Integer reservationId) {
		Reservation reservation = reservationService.readById(reservationId);
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@ApiOperation(value = "Get a reservation by id", notes = "Returns reservation as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/user_readable/{reservationId}")
	public ReservationUserReadableDto readReservationUserReadableDtoById(@PathVariable @ApiParam(value = "Reservation id", example = "3") Integer reservationId) {
		return reservationService.readReservationUserReadableDtoById(reservationId);
	}

	@ApiOperation(value = "Create a reservation", notes = "Returns a reservation with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/")
	public ReservationDto createReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = ConverterUtil.convertToReservation(reservationDto);
		reservationService.create(reservation);
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@ApiOperation(value = "Update a reservation", notes = "Returns updated reservation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PutMapping("/")
	public ReservationDto updateReservation(@RequestBody @Valid ReservationDto reservationDto) {
		Reservation reservation = ConverterUtil.convertToReservation(reservationDto);
		reservationService.update(reservation);
		return ConverterUtil.convertToReservationDto(reservation);
	}

	@ApiOperation(value = "Delete a reservation", notes = "Sets reservation state to 'Cancelled'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@DeleteMapping("/{reservationId}")
	public Boolean deleteReservation(@PathVariable @ApiParam(value = "Reservation id", example = "9") Integer reservationId) {
		return reservationService.delete(reservationId);
	}

	@ApiOperation(value = "Get a list of reservations by parameters", notes = "Returns a list of reservations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list")
	public List<ReservationDto> getListByParameters(@RequestParam(name = "product_id") @ApiParam(value = "Product id", example = "33") Integer productId,
			@RequestParam(required = false, name = "organization_id") @ApiParam(value = "Organization id", example = "45") Integer organizationId,
			@RequestParam(required = false, name = "agent_id") @ApiParam(value = "Agent id", example = "23") Integer agentId) {
		List<Reservation> reservationList = reservationService.getListByParameters(productId, organizationId, agentId);
		return ConverterUtil.convertToReservationDtoList(reservationList);
	}

	@ApiOperation(value = "Get a list of reservations by name and dates", notes = "Returns a list of reservations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list_dates")
	public List<ReservationDto> getListByDates(
			@RequestParam(name = "from_date") @ApiParam(value = "Date when booking starts (included)", example = "2022-11-01") String fromDate,
			@RequestParam(name = "to_date") @ApiParam(value = "Date when booking ends (excluded)", example = "2022-11-01") String toDate,
			@RequestParam(required = false, name = "state") @ApiParam(value = "State", example = "Confirmed") String state) {
		List<Reservation> reservationList = reservationService.getListByDates(fromDate, toDate, state);
		return ConverterUtil.convertToReservationDtoList(reservationList);
	}
}
