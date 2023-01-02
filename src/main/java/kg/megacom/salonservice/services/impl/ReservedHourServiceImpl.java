package kg.megacom.salonservice.services.impl;

import kg.megacom.salonservice.dao.ReservedHourRepo;
import kg.megacom.salonservice.exceptions.NotFound;
import kg.megacom.salonservice.mappers.ReservedHourMapper;
import kg.megacom.salonservice.models.dto.ReservedHourDto;
import kg.megacom.salonservice.models.entity.ReservedHour;
import kg.megacom.salonservice.models.json.GetReservedHours;
import kg.megacom.salonservice.models.json.MasterDay;
import kg.megacom.salonservice.services.EmailService;
import kg.megacom.salonservice.services.MasterWorkDayService;
import kg.megacom.salonservice.services.ReservedHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservedHourServiceImpl implements ReservedHourService {

    @Autowired
    private ReservedHourRepo reservedHourRepo;

    @Autowired
    private MasterWorkDayService masterWorkDayService;

    @Autowired
    private EmailService emailService;

    @Override
    public ReservedHourDto save(ReservedHourDto reservedHourDto) {
        boolean inTime = masterWorkDayService.inTime(reservedHourDto.getMasterWorkDay().getId(), reservedHourDto.getStartTime(), reservedHourDto.getEndTime());
        if (!inTime) {

            throw new RuntimeException("Не входит в диапозон!");

        }
        List<ReservedHour> reservedHourList = reservedHourRepo.findAllByMasterWorkDayId(reservedHourDto.getMasterWorkDay().getId());
        boolean isFreeTime = checkFreeTime(reservedHourList, reservedHourDto.getStartTime(), reservedHourDto.getEndTime());
        if (isFreeTime) {
            throw new RuntimeException("Нет свободного времени!");
        }
        emailService.sendMail("cartoonchaka@gmail.com",
                "Запись в салон",
                "Ваш заказ принят!!!");
        return ReservedHourMapper.INSTANCE.toDto(reservedHourRepo.save(ReservedHourMapper.INSTANCE.toEntity(reservedHourDto)));

    }

    private boolean checkFreeTime(List<ReservedHour> reservedHourList, LocalDateTime startTime, LocalDateTime endTime) {
        return reservedHourList.stream().anyMatch(x ->
                (x.getStartTime().isEqual(startTime) || x.getEndTime().isEqual(endTime))
                        || (x.getStartTime().isBefore(startTime) && x.getEndTime().isAfter(endTime))
                        || (x.getStartTime().isAfter(startTime) && x.getEndTime().isBefore(endTime))
        );

    }

    @Override
    public List<ReservedHourDto> findAll() {
        List<ReservedHour> reservedHours = reservedHourRepo.findAll();
        List<ReservedHourDto> reservedHourDtos = reservedHours
                .stream()
                .map(x -> ReservedHourMapper.INSTANCE.toDto(x))
                .collect(Collectors.toList());
        return reservedHourDtos;
    }

    @Override
    public ReservedHourDto findById(Long id) {
        ReservedHour reservedHour = reservedHourRepo.findById(id)
                .orElseThrow(() -> new NotFound("Заказ не найден!"));
        return ReservedHourMapper.INSTANCE.toDto(reservedHour);
    }

    @Override
    public ReservedHourDto update(ReservedHourDto reservedHourDto) {
        if (!reservedHourRepo.existsById(reservedHourDto.getId())) {
            throw new NotFound("Заказ не найден!");
        }
        return ReservedHourMapper.INSTANCE.toDto(reservedHourRepo.save(ReservedHourMapper.INSTANCE.toEntity(reservedHourDto)));
    }

    @Override
    public GetReservedHours findByMasterWorkDayId(Long masterWorkDayId) {
        List<ReservedHour> reservedHourList = reservedHourRepo.findAll();
        List<MasterDay> masterDays = new ArrayList<>();

        GetReservedHours getReservedHours = new GetReservedHours();
        reservedHourList.stream().forEach(x->{
            getReservedHours.setMasterName(x.getMasterWorkDay().getMaster().getName());
            getReservedHours.setWorkDay(x.getMasterWorkDay().getWorkDay());

            MasterDay masterDay = new MasterDay();
            masterDay.setStartTime(x.getStartTime());
            masterDay.setEndTime(x.getEndTime());
            masterDays.add(masterDay);

        });
        getReservedHours.setReservedHours(masterDays);

        return getReservedHours;
    }

}