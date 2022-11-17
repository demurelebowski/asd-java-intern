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
import team.asd.dto.ArchivePriceDto;
import team.asd.entity.ArchivePrice;
import team.asd.service.ArchivePriceService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/archive_price")
public class ArchivePriceController {
	@Autowired
	public ArchivePriceService archivePriceService;

	@GetMapping("/{archivePriceId}")
	public ArchivePriceDto readById(@PathVariable Integer archivePriceId) {
		ArchivePrice archivePrice = archivePriceService.readById(archivePriceId);
		return ConverterUtil.convertToArchivePriceDto(archivePrice);
	}

	@PostMapping("/")
	public ArchivePriceDto create(@RequestBody @Valid ArchivePriceDto archivePriceDto) {
		ArchivePrice archivePrice = ConverterUtil.convertToArchivePrice(archivePriceDto);
		archivePriceService.create(archivePrice);
		return ConverterUtil.convertToArchivePriceDto(archivePrice);
	}

	@PutMapping("/")
	public ArchivePriceDto update(@RequestBody @Valid ArchivePriceDto archivePriceDto) {
		ArchivePrice archivePrice = ConverterUtil.convertToArchivePrice(archivePriceDto);
		archivePriceService.update(archivePrice);
		return ConverterUtil.convertToArchivePriceDto(archivePrice);
	}

	@DeleteMapping("/{archivePriceId}")
	public Boolean delete(@PathVariable Integer archivePriceId) {
		return archivePriceService.delete(archivePriceId);
	}
}

