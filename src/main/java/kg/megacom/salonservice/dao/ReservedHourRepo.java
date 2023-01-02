package kg.megacom.salonservice.dao;

import kg.megacom.salonservice.models.entity.ReservedHour;
import kg.megacom.salonservice.models.json.GetReservedHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedHourRepo extends JpaRepository<ReservedHour, Long> {

    List<ReservedHour> findAllByMasterWorkDayId(Long masterWorkDayId);

}


