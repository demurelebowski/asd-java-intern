package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArchivePriceInfoDto {
    @Size(max = 15, message = "entity_type is invalid.")
    @ApiModelProperty(notes = "Type of entity to which this record belongs", example = "Reservation, Payment, Modification")
    @JsonProperty("entity_type")
    private String entityType;

    @Size(max = 45, message = "name is invalid.")
    @ApiModelProperty(notes = "Name of archived price record or calculation element", example = "Name", required = true)
    @JsonProperty("name")
    private String name;

    @Size(max = 15, message = "type is invalid.")
    @ApiModelProperty(notes = "Type of record", example = "TAX, FEE, PRICE, INFO, COMMISSION")
    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    @ApiModelProperty(notes = "Value of record if applied", example = "88.66")
    private Double value;
}
