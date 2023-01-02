package kg.megacom.salonservice.services;

import kg.megacom.salonservice.models.dto.MasterWorkDayDto;
import kg.megacom.salonservice.models.json.GetMasters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MasterWorkDayService {

    MasterWorkDayDto save(MasterWorkDayDto masterWorkDayDto);

    MasterWorkDayDto update(MasterWorkDayDto masterWorkDayDto);

    List<MasterWorkDayDto> findAll();

    MasterWorkDayDto findById(Long id);

    List<MasterWorkDayDto> findByMaster(Long id);

    List<GetMasters> getMasterWorkDay(Long branchId, LocalDate workDay);

    boolean inTime(Long id, LocalDateTime startTime, LocalDateTime endTime);

}
