package kg.megacom.salonservice.dao;

import kg.megacom.salonservice.models.entity.MasterWorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MasterWorkDayRepo extends JpaRepository<MasterWorkDay, Long>{

    List<MasterWorkDay> findAllByBranchIdAndWorkDayIs(Long branchId, LocalDate date);

    List<MasterWorkDay> findAllByMasterId(Long masterId);

    boolean existsByIdAndStartTimeAfterAndEndTimeBefore(Long id, LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByIdAndStartTimeIsLessThanEqualAndEndTimeGreaterThanEqual(Long id, LocalDateTime startTime, LocalDateTime endTime);

}
