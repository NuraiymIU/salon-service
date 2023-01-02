package kg.megacom.salonservice.models.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDay {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
