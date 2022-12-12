package team.asd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationReportDto {
    ReservationDto reservationDto;
    ReservationConfirmationInfoDto reservationConfirmationInfoDto;
    List<ArchivePriceInfoDto> archivePriceDtoList;
}
