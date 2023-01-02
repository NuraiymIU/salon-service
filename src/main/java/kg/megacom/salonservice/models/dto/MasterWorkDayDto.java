package kg.megacom.salonservice.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MasterWorkDayDto {

    private Long id;
    private MasterDto master;
    private BranchDto branch;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate workDay;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime endTime;

    private LocalDate addDate;
    private LocalDate editDate;
    private AdminDto admin;

}
