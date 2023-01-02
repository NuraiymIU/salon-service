package kg.megacom.salonservice.models.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReservedHours {

    private String masterName;
    private LocalDate workDay;
    private List<MasterDay> reservedHours;

}
