package kg.megacom.salonservice.services.impl;

import kg.megacom.salonservice.dao.BranchRepo;
import kg.megacom.salonservice.exceptions.NotFound;
import kg.megacom.salonservice.mappers.BranchMapper;
import kg.megacom.salonservice.models.dto.BranchDto;
import kg.megacom.salonservice.models.entity.Branch;
import kg.megacom.salonservice.services.BranchService;
import kg.megacom.salonservice.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private PhoneService phoneService;

    @Override
    public BranchDto save(BranchDto branchDto) {
        branchDto.setPhones(phoneService.saveAll(branchDto.getPhones()));
        return BranchMapper.INSTANCE.toDto(branchRepo.save(BranchMapper.INSTANCE.toEntity(branchDto)));
    }

    @Override
    public List<BranchDto> findAll() {
        List<Branch> branches = branchRepo.findAll();
        return BranchMapper.INSTANCE.toDtos(branches);
    }

    @Override
    public BranchDto findById(Long id) {
        Branch branch = branchRepo.findById(id)
                .orElseThrow(() -> new NotFound("Филиал не найден!"));
        return BranchMapper.INSTANCE.toDto(branch);
    }

    @Override
    public List<BranchDto> findBySalon(Long id) {
        return BranchMapper.INSTANCE.toDtos(branchRepo.findAllBySalonId(id));
    }

}