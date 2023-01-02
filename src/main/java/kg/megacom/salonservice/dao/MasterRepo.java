package kg.megacom.salonservice.dao;

import kg.megacom.salonservice.models.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepo extends JpaRepository<Master, Long> {

}
