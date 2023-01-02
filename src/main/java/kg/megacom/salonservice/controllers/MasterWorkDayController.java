package kg.megacom.salonservice.controllers;

import kg.megacom.salonservice.models.dto.MasterWorkDayDto;
import kg.megacom.salonservice.services.MasterWorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master-work-days")
public class MasterWorkDayController {

    @Autowired
    private MasterWorkDayService masterWorkDayService;

    @PostMapping("/save")
    public MasterWorkDayDto save(@RequestBody MasterWorkDayDto masterWorkDayDto){ return masterWorkDayService.save(masterWorkDayDto); }

    @PostMapping("/update")
    public MasterWorkDayDto update(@RequestBody MasterWorkDayDto masterWorkDayDto) {
        return masterWorkDayService.update(masterWorkDayDto);
    }

    @GetMapping("/all")
    public List<MasterWorkDayDto> findAll() {
        return masterWorkDayService.findAll();
    }

    @GetMapping("/{id}")
    public MasterWorkDayDto findById(@PathVariable Long id) {
        return masterWorkDayService.findById(id);
    }

}
