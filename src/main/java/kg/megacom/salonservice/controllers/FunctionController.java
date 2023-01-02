package kg.megacom.salonservice.controllers;

import kg.megacom.salonservice.models.dto.BranchDto;
import kg.megacom.salonservice.models.dto.MasterWorkDayDto;
import kg.megacom.salonservice.models.json.GetMasters;
import kg.megacom.salonservice.models.json.GetReservedHours;
import kg.megacom.salonservice.services.BranchService;
import kg.megacom.salonservice.services.MasterWorkDayService;
import kg.megacom.salonservice.services.ReservedHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/func")
public class FunctionController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private MasterWorkDayService masterWorkDayService;

    @Autowired
    private ReservedHourService reservedHourService;

    @GetMapping("/getBranchesBySalon{id}")
    public List<BranchDto> findBySalonId(@PathVariable Long id) {
        return branchService.findBySalon(id);
    }

    @GetMapping("/getMasterWorkDaysByMaster{id}")
    public List<MasterWorkDayDto> findByMasterId(@PathVariable Long id) {
        return masterWorkDayService.findByMaster(id);
    }

    @GetMapping("/getMastersByBranchIdAndWorkDay")
    public List<GetMasters> getMasters(@RequestParam Long branchId, @RequestParam("localDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workDay) {
        System.out.println(workDay);
        return masterWorkDayService.getMasterWorkDay(branchId, workDay);
    }

    @GetMapping("/getAllReservedHoursByMasterWorkDayId")
    public GetReservedHours get(@RequestParam Long masterWorkDayId) {
        return reservedHourService.findByMasterWorkDayId(masterWorkDayId);
    }

}
