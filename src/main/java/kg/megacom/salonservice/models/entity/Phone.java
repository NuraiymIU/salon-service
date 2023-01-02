package kg.megacom.salonservice.models.entity;

import kg.megacom.salonservice.models.entity.Branch;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}
