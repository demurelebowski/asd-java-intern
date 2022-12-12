package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ReservationReportDto;
import team.asd.entity.ReservationReport;
import team.asd.service.ReservationService;
import team.asd.util.ConverterUtil;

@RestController
@RequestMapping(value = "/reservation_report")
@ApiOperation("Reservation report API")
public class ReservationReportController {
    @Autowired
    public ReservationService reservationService;

    @ApiOperation(value = "Get a reservation report by reservationId", notes = "Returns reservation report as per the reservation id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 400, message = "Invalid parameter was provided")})
    @GetMapping("/{reservationId}")
    public ReservationReportDto readById(@PathVariable @ApiParam(value = "Reservation id", example = "12") Integer reservationId) {
        ReservationReport reservationReport = reservationService.getReservationReport(reservationId);
        return ConverterUtil.convertToReservationReportDto(reservationReport);
    }
}
