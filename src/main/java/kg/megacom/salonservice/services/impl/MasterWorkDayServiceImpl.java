package kg.megacom.salonservice.services.impl;

import kg.megacom.salonservice.dao.MasterWorkDayRepo;
import kg.megacom.salonservice.exceptions.NotFound;
import kg.megacom.salonservice.mappers.MasterWorkDayMapper;
import kg.megacom.salonservice.models.dto.MasterWorkDayDto;
import kg.megacom.salonservice.models.entity.MasterWorkDay;
import kg.megacom.salonservice.models.json.GetMasters;
import kg.megacom.salonservice.services.MasterWorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterWorkDayServiceImpl implements MasterWorkDayService {

    @Autowired
    private MasterWorkDayRepo masterWorkDayRepo;

    @Override
    public MasterWorkDayDto save(MasterWorkDayDto masterWorkDayDto) {
        return MasterWorkDayMapper.INSTANCE.toDto(masterWorkDayRepo.save(MasterWorkDayMapper.INSTANCE.toEntity(masterWorkDayDto)));
    }

    @Override
    public MasterWorkDayDto update(MasterWorkDayDto masterWorkDayDto) {
        if(!masterWorkDayRepo.existsById(masterWorkDayDto.getId())){
            throw new NotFound("Рабочий график не найден!");
        }
        return MasterWorkDayMapper.INSTANCE.toDto(masterWorkDayRepo.save(MasterWorkDayMapper.INSTANCE.toEntity(masterWorkDayDto)));
    }

    @Override
    public List<MasterWorkDayDto> findAll() {
        List<MasterWorkDay> masterWorkDays = masterWorkDayRepo.findAll();
        List<MasterWorkDayDto> masterWorkDayDtos = masterWorkDays
                .stream()
                .map(x -> MasterWorkDayMapper.INSTANCE.toDto(x))
                .collect(Collectors.toList());
        return masterWorkDayDtos;
    }

    @Override
    public MasterWorkDayDto findById(Long id) {
        MasterWorkDay masterWorkDay = masterWorkDayRepo.findById(id)
                .orElseThrow(() -> new NotFound("Рабочий график не найден!"));
        return MasterWorkDayMapper.INSTANCE.toDto(masterWorkDay);
    }

    @Override
    public List<MasterWorkDayDto> findByMaster(Long id) {
     return MasterWorkDayMapper.INSTANCE.toDtos(masterWorkDayRepo.findAllByMasterId(id));
    }

    @Override
    public List<GetMasters> getMasterWorkDay(Long branchId, LocalDate workDay) {
        List<MasterWorkDay> masterWorkDays = masterWorkDayRepo.findAll();
        List<GetMasters> masterDtos = new ArrayList<>();
       masterWorkDays.stream().forEach(x-> {
           GetMasters masterDto = new GetMasters();
            masterDto.setMasterId(x.getMaster().getId());
            masterDto.setName(x.getMaster().getName());
            masterDto.setWorkDay(x.getWorkDay());
            masterDto.setStartTime(x.getStartTime());
            masterDto.setEndTime(x.getEndTime());
            masterDtos.add(masterDto);
        });
        return masterDtos;
    }

    @Override
    public boolean inTime(Long id, LocalDateTime startTime, LocalDateTime endTime) {
            return masterWorkDayRepo.existsByIdAndStartTimeIsLessThanEqualAndEndTimeGreaterThanEqual(id, startTime, endTime);
        }


}
