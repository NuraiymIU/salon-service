package kg.megacom.salonservice.controllers;

import kg.megacom.salonservice.models.dto.MasterDto;
import kg.megacom.salonservice.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @PostMapping("/save")
    public MasterDto save(@RequestBody MasterDto masterDto){
    return masterService.save(masterDto);
    }

    @PostMapping("/update")
    public MasterDto update(@RequestBody MasterDto masterDto){
        return masterService.update(masterDto);
    }

    @GetMapping("/all")
    public List<MasterDto> findAll() {
        return masterService.findAll();
    }

    @GetMapping("/{id}")
    public MasterDto findById(@PathVariable Long id) {
        return masterService.findById(id);
    }

}
