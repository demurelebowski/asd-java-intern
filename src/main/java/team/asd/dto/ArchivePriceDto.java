package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("id")
	private Integer id;

	@Size(max = 15, message = "entity_type is invalid.")
	@JsonProperty("entity_type")
	private String entityType;

	@Min(value = 1, message = "entity_id {javax.validation.constraints.Min.message}")
	@JsonProperty("entity_id")
	private Integer entityId;

	@Size(max = 45, message = "name is invalid.")
	@JsonProperty("name")
	private String name;

	@Size(max = 15, message = "state is invalid.")
	@JsonProperty("state")
	private String state;

	@Size(max = 15, message = "type is invalid.")
	@JsonProperty("type")
	private String type;

	@JsonProperty("value")
	private Double value;

	@JsonProperty("version")
	private String version;
}
