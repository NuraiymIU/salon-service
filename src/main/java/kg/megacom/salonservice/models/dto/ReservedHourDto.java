package kg.megacom.salonservice.models.dto;

import kg.megacom.salonservice.models.entity.StatusReserved;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReservedHourDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MasterWorkDayDto masterWorkDay;
    private ClientDto client;

    @Enumerated(EnumType.STRING)
    private StatusReserved status;
    private AdminDto admin;

}
