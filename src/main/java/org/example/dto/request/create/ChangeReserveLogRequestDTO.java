package org.example.dto.request.create;
import lombok.Data;
import org.example.entity.ChangeReserveLog.ReserveType;

@Data
public class ChangeReserveLogRequestDTO {
    private Integer denomination;
    private Integer quantity;
    private ReserveType reserveType;
}