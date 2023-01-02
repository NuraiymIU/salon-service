package kg.megacom.salonservice.services.impl;

import kg.megacom.salonservice.dao.SalonRepo;
import kg.megacom.salonservice.exceptions.NotFound;
import kg.megacom.salonservice.mappers.SalonMapper;
import kg.megacom.salonservice.models.dto.SalonDto;
import kg.megacom.salonservice.models.entity.Salon;
import kg.megacom.salonservice.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonRepo salonRepo;

    @Override
    public SalonDto save(SalonDto salonDto) {
        return SalonMapper.INSTANCE.toDto(salonRepo.save(SalonMapper.INSTANCE.toEntity(salonDto)));
    }

    @Override
    public SalonDto update(SalonDto salonDto) {
        if(!salonRepo.existsById(salonDto.getId())){
            throw new NotFound("Салон не найден!");
        }
        return SalonMapper.INSTANCE.toDto(salonRepo.save(SalonMapper.INSTANCE.toEntity(salonDto)));
    }

    @Override
    public List<SalonDto> findAll() {
            List<Salon> salons = salonRepo.findAll();
            List<SalonDto> salonDtos = salons
                    .stream()
                    .map(x-> SalonMapper.INSTANCE.toDto(x))
                    .collect(Collectors.toList());
        return salonDtos;
    }

    @Override
    public SalonDto findById(Long id) {
        Salon salon = salonRepo.findById(id)
                .orElseThrow(() -> new NotFound("Салон не найден!"));
        return SalonMapper.INSTANCE.toDto(salon);
    }

    @Override
    public void delete(Long salon_id) {

            Salon salon = salonRepo.findById(salon_id)
                    .orElseThrow(() -> new NotFound("Салон не найден!"));

            salon.setActive(false);
            salonRepo.save(salon);
    }
}
