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
import team.asd.dto.ReservationConfirmationDto;
import team.asd.entity.ReservationConfirmation;
import team.asd.service.ReservationConfirmationService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/reservation_confirmation")
@ApiOperation("Reservation confirmation API")
public class ReservationConfirmationController {
	@Autowired
	public ReservationConfirmationService reservationConfirmationService;

	@ApiOperation(value = "Get a reservation confirmation by id", notes = "Returns a reservation confirmation as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/{reservationConfirmationId}")
	public ReservationConfirmationDto readById(
			@PathVariable @ApiParam(value = "Reservation confirmation id", example = "7") Integer reservationConfirmationId) {
		ReservationConfirmation reservationConfirmation = reservationConfirmationService.readById(reservationConfirmationId);
		return ConverterUtil.convertToReservationConfirmationDto(reservationConfirmation);
	}

	@ApiOperation(value = "Create a reservation confirmation", notes = "Returns a reservation confirmation with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/")
	public ReservationConfirmationDto createReservationConfirmation(@RequestBody @Valid ReservationConfirmationDto reservationConfirmationDto) {
		ReservationConfirmation reservationConfirmation = ConverterUtil.convertToReservationConfirmation(reservationConfirmationDto);
		reservationConfirmationService.create(reservationConfirmation);
		return ConverterUtil.convertToReservationConfirmationDto(reservationConfirmation);
	}

	@ApiOperation(value = "Update a reservation confirmation", notes = "Returns updated reservation confirmation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PutMapping("/")
	public ReservationConfirmationDto updateReservationConfirmation(@RequestBody @Valid ReservationConfirmationDto reservationConfirmationDto) {
		ReservationConfirmation reservationConfirmation = ConverterUtil.convertToReservationConfirmation(reservationConfirmationDto);
		reservationConfirmationService.update(reservationConfirmation);
		return ConverterUtil.convertToReservationConfirmationDto(reservationConfirmation);
	}

	@ApiOperation(value = "Delete a reservation confirmation", notes = "Deletes reservation confirmation record by provided id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@DeleteMapping("/{reservationConfirmationId}")
	public Boolean deleteReservationConfirmation(
			@PathVariable @ApiParam(value = "Reservation confirmation id", example = "5") Integer reservationConfirmationId) {
		return reservationConfirmationService.delete(reservationConfirmationId);
	}

	@ApiOperation(value = "Get a list of reservation confirmations by reservation id", notes = "Returns a list of reservation confirmations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("reservation/{reservationId}")
	public List<ReservationConfirmationDto> getListByReservationId(@PathVariable @ApiParam(value = "Reservation id", example = "44") Integer reservationId) {
		List<ReservationConfirmation> reservationConfirmationList = reservationConfirmationService.getListByReservationId(reservationId);
		return ConverterUtil.convertToReservationConfirmationDtoList(reservationConfirmationList);
	}

	@ApiOperation(value = "Get a list of reservation confirmations by confirmation id and range of dates", notes = "Returns a list of reservation confirmations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/confirmationId")
	public List<ReservationConfirmationDto> getListByConfirmationIdAndDateRange(
			@RequestParam(name = "confirmation_id") @ApiParam(value = "Confirmation id", example = "7") String confirmationId,
			@RequestParam(required = false, name = "from_date") @ApiParam(name = "from_date", value = "Start point for filtering by created_date", example = "2022-11-16 07:30:11") String dateStart,
			@RequestParam(required = false, name = "to_date") @ApiParam(name = "to_date", value = "End point for filtering by created_date", example = "2022-11-16 09:30:11") String dateEnd) {
		List<ReservationConfirmation> reservationConfirmationList = reservationConfirmationService.getListByConfirmationIdAndDateRange(confirmationId,
				dateStart, dateEnd);
		return ConverterUtil.convertToReservationConfirmationDtoList(reservationConfirmationList);
	}

	@ApiOperation(value = "Get a list of reservation confirmations where reservation state 'Failed'", notes = "Returns a list of reservation confirmations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"), @ApiResponse(code = 400, message = "Unknown error") })
	@GetMapping("/reservation")
	public List<ReservationConfirmationDto> getListByFailedReservation() {
		List<ReservationConfirmation> reservationConfirmationList = reservationConfirmationService.getListByFailedReservation();
		return ConverterUtil.convertToReservationConfirmationDtoList(reservationConfirmationList);
	}
}
