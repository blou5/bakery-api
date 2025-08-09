package org.example.dto.request.update;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WithdrawalsUpdateDTO {
    private Integer withdrawalId;
    private Integer logId;
    private Integer amount;
    private String reason;
    private LocalDateTime date;
    private String person;
    private String notes;
}

