package kg.megacom.salonservice.dao;

import kg.megacom.salonservice.models.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {

    List<Branch> findAllBySalonId(Long salonId);

}
