package kg.megacom.salonservice.controllers;

import kg.megacom.salonservice.models.dto.ReservedHourDto;
import kg.megacom.salonservice.services.ReservedHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reserved-hours")
public class ReservedHourController {

    @Autowired
    private ReservedHourService reservedHourService;

    @PostMapping("/save")
    public ReservedHourDto save(@RequestBody ReservedHourDto reservedHourDto) {
        return reservedHourService.save(reservedHourDto);
    }

    @PostMapping("/update")
    public ReservedHourDto update(@RequestBody ReservedHourDto adminDto) {
        return reservedHourService.update(adminDto);
    }

    @GetMapping("/all")
    public List<ReservedHourDto> findAll() {
        return reservedHourService.findAll();
    }

    @GetMapping("/{id}")
    public ReservedHourDto findById(@PathVariable Long id) {
        return reservedHourService.findById(id);
    }

}
