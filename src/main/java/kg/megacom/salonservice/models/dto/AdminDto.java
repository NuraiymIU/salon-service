package kg.megacom.salonservice.models.dto;

import lombok.Data;

@Data
public class AdminDto {

    private Long id;
    private String name;
    private String login;
    private String password;
    private boolean status;

}
