package kg.megacom.salonservice.models.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMasters {

    private Long masterId;
    private String name;
    private LocalDate workDay;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
