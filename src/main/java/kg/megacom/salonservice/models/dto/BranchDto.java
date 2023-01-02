package kg.megacom.salonservice.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class BranchDto {

    private Long id;
    private String address;
    private double lat;
    private double lon;
    private boolean active;
    private SalonDto salon;
    private List<PhoneDto> phones;

}
