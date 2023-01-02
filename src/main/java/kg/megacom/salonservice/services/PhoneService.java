package kg.megacom.salonservice.services;

import kg.megacom.salonservice.models.dto.PhoneDto;

import java.util.List;

public interface PhoneService {

    PhoneDto save(PhoneDto phoneDto);

    List<PhoneDto> saveAll(List<PhoneDto> phoneDtos);

}
