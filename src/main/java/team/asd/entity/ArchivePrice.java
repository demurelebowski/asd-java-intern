package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.constant.EntityType;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArchivePrice {
    private Integer id;
    private EntityType entityType;
    private Integer entityId;
    private String name;
    private ArchivePriceState state;
    private ArchivePriceType type;
    private Double value;
    private Date version;
}
