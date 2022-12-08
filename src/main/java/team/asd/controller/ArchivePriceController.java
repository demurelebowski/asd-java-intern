package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ArchivePriceDto;
import team.asd.entity.ArchivePrice;
import team.asd.service.ArchivePriceService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/archive_price")
@ApiOperation("Archive price API")
public class ArchivePriceController {
	@Autowired
	public ArchivePriceService archivePriceService;

	@ApiOperation(value = "Get an archive price by id", notes = "Returns an archive price as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/{archivePriceId}")
	public ArchivePriceDto readById(@PathVariable @ApiParam(value = "Archive price id", example = "2") Integer archivePriceId) {
		ArchivePrice archivePrice = archivePriceService.readById(archivePriceId);
		return ConverterUtil.convertToArchivePriceDto(archivePrice);
	}

	@ApiOperation(value = "Create an archive price", notes = "Returns an archive price with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/")
	public ArchivePriceDto create(@RequestBody @Valid ArchivePriceDto archivePriceDto) {
		ArchivePrice archivePrice = ConverterUtil.convertToArchivePrice(archivePriceDto);
		archivePriceService.create(archivePrice);
		return ConverterUtil.convertToArchivePriceDto(archivePrice);
	}

	@ApiOperation(value = "Update an archive price", notes = "Returns updated archive price")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PutMapping("/")
	public ArchivePriceDto update(@RequestBody @Valid ArchivePriceDto archivePriceDto) {
		ArchivePrice archivePrice = ConverterUtil.convertToArchivePrice(archivePriceDto);
		archivePriceService.update(archivePrice);
		return ConverterUtil.convertToArchivePriceDto(archivePrice);
	}

	@ApiOperation(value = "Delete an archive price", notes = "Sets archive price state to 'Final'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@DeleteMapping("/{archivePriceId}")
	public Boolean delete(@PathVariable @ApiParam(value = "Archive price id", example = "2") Integer archivePriceId) {
		return archivePriceService.delete(archivePriceId);
	}

	@ApiOperation(value = "Create a list of archive prices", notes = "Returns a list of archive prices with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/list")
	public List<ArchivePriceDto> createList(@RequestBody List<@Valid ArchivePriceDto> archivePriceDtoList) {
		List<ArchivePrice> archivePriceList = ConverterUtil.convertToArchivePriceList(archivePriceDtoList);
		archivePriceService.createList(archivePriceList);
		return ConverterUtil.convertToArchivePriceDtoList(archivePriceList);
	}

	@ApiOperation(value = "Get a list of archive prices by reservationId", notes = "Returns a list of archive prices")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list/reservation_id/{reservationId}")
	public List<ArchivePriceDto> getListByReservationId(@PathVariable @ApiParam(value = "Reservation id", example = "4") Integer reservationId) {
		List<ArchivePrice> archivePriceList = archivePriceService.getListByReservationId(reservationId);
		return ConverterUtil.convertToArchivePriceDtoList(archivePriceList);
	}

	@ApiOperation(value = "Get a list of archive prices by provided parameters", notes = "Returns a list of archive prices")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list")
	public List<ArchivePriceDto> getListByParameters(@RequestParam(required = false) @ApiParam(value = "Type", example = "TAX") String type,
			@RequestParam(required = false) @ApiParam(value = "State", example = "Created") String state,
			@RequestParam(required = false) @ApiParam(value = "Name", example = "Name") String name) {
		List<ArchivePrice> archivePriceList = archivePriceService.getListByParameters(type, state, name);
		return ConverterUtil.convertToArchivePriceDtoList(archivePriceList);
	}

	@ApiOperation(value = "Get a list of archive prices by Reservation from_date parameter greater than provided", notes = "Returns a list of archive prices")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list_reservation/{reservationFromDateAtLeast}")
	public List<ArchivePriceDto> getListByReservationFromDateAtLeast(
			@PathVariable @ApiParam(value = "Reservation from_date", example = "2022-11-16") String reservationFromDateAtLeast) {
		List<ArchivePrice> archivePriceList = archivePriceService.getListByReservationFromDateAtLeast(reservationFromDateAtLeast);
		return ConverterUtil.convertToArchivePriceDtoList(archivePriceList);
	}

	@ApiOperation(value = "Update an archive price (delay)", notes = "Returns updated archive price")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PutMapping("/delay")
	public String updateDelay(@RequestBody @Valid ArchivePriceDto archivePriceDto) {
		ArchivePrice archivePrice = ConverterUtil.convertToArchivePrice(archivePriceDto);
		return archivePriceService.updateDelay(archivePrice);
	}
}
