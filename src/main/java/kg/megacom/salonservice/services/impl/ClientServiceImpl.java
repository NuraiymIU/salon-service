package kg.megacom.salonservice.services.impl;

import kg.megacom.salonservice.dao.ClientRepo;
import kg.megacom.salonservice.exceptions.NotFound;
import kg.megacom.salonservice.mappers.ClientMapper;
import kg.megacom.salonservice.models.dto.ClientDto;
import kg.megacom.salonservice.models.entity.Client;
import kg.megacom.salonservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public ClientDto save(ClientDto clientDto) {
            return ClientMapper.INSTANCE.toDto(clientRepo.save(ClientMapper.INSTANCE.toEntity(clientDto)));
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        if(!clientRepo.existsById(clientDto.getId())){
            throw new NotFound("Клиент не найден!");
        }
        return ClientMapper.INSTANCE.toDto(clientRepo.save(ClientMapper.INSTANCE.toEntity(clientDto)));
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = clientRepo.findAll();
        List<ClientDto> clientDtos = clients
                .stream()
                .map(x-> ClientMapper.INSTANCE.toDto(x))
                .collect(Collectors.toList());
        return clientDtos;
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new NotFound("Клиент не найден!"));
        return ClientMapper.INSTANCE.toDto(client);
    }

}
