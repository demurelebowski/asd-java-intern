package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArchivePriceDto {
	@Min(value = 1, message = "id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Archive price ID", example = "1", required = true)
	@JsonProperty("id")
	private Integer id;

	@Size(max = 15, message = "entity_type is invalid.")
	@ApiModelProperty(notes = "Type of entity to which this record belongs", example = "Reservation, Payment, Modification")
	@JsonProperty("entity_type")
	private String entityType;

	@Min(value = 1, message = "entity_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "ID of entity to which this record belongs", example = "1")
	@JsonProperty("entity_id")
	private Integer entityId;

	@Size(max = 45, message = "name is invalid.")
	@ApiModelProperty(notes = "Name of archived price record or calculation element", example = "Name", required = true)
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "state is invalid.")
	@ApiModelProperty(notes = "State of current record (Created, Final). Created means that current item is active, Final means that record was deleted (finalised)", example = "Created, Final")
	@JsonProperty("state")
	private String state;

	@Size(max = 15, message = "type is invalid.")
	@ApiModelProperty(notes = "Type of record", example = "TAX, FEE, PRICE, INFO, COMMISSION")
	@JsonProperty("type")
	private String type;

	@JsonProperty("value")
	@ApiModelProperty(notes = "Value of record if applied", example = "88.66")
	private Double value;

	@JsonProperty("version")
	@ApiModelProperty(notes = "Date when record was updated last time")
	private String version;
}
