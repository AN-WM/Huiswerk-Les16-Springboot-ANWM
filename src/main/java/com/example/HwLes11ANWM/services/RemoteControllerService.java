package com.example.HwLes11ANWM.services;

import com.example.HwLes11ANWM.dtos.RemoteControllerDto;
import com.example.HwLes11ANWM.dtos.RemoteControllerInputDto;
import com.example.HwLes11ANWM.exceptions.DuplicateRecordException;
import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.RemoteController;
import com.example.HwLes11ANWM.repositories.RemoteControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RemoteControllerService {
    @Autowired
    private RemoteControllerRepository remoteControllerRepository;

    public Iterable<RemoteControllerDto> getAllRemoteControllers() {
        Iterable<RemoteController> allRemoteControllers = remoteControllerRepository.findAll();
        ArrayList<RemoteControllerDto> newRemoteControllerList = new ArrayList<>();

        for (RemoteController t : allRemoteControllers) {
            newRemoteControllerList.add(fromRemoteController(t));
        }

        return newRemoteControllerList;
    }

    public RemoteControllerDto getRemoteController(Long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);
        if (remoteController.isEmpty()) {
            throw new IndexOutOfBoundsException(String.format("ID %d was not found", id));
        } else {
            RemoteController remoteController1 = remoteController.get();
            return fromRemoteController(remoteController1);
        }
    }

    public Long saveRemoteController(RemoteControllerInputDto remoteControllerInputDto) {
        String name = remoteControllerInputDto.getName();
        if (remoteControllerRepository.findByName(name) != null) {
            throw new DuplicateRecordException(String.format("A remoteController with name %s already exists", name));
        } else {
            RemoteController savedRemoteController = remoteControllerRepository.save(toRemoteController(remoteControllerInputDto));
            return savedRemoteController.getId();
        }
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto remoteControllerInputDto) {
        if (remoteControllerRepository.existsById(id)) {
            RemoteController convertedRemoteController = toRemoteController(remoteControllerInputDto);
            convertedRemoteController.setId(id);
            remoteControllerRepository.save(convertedRemoteController);
            return fromRemoteController(convertedRemoteController);
        } else {
            throw new IndexOutOfBoundsException(String.format("RemoteController with id %d was not found, cannot update", id));
        }
    }

    public ResponseEntity<Object> deleteRemoteController(Long id) {
        if (remoteControllerRepository.existsById(id)) {
            remoteControllerRepository.deleteById(id);
            return ResponseEntity.ok("RemoteController deleted from database");
        } else {
            throw new RecordNotFoundException(String.format("RemoteController with id %d was not found", id));
        }
    }

    public RemoteController toRemoteController(RemoteControllerInputDto dto){
        var remoteController = new RemoteController();
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;
    }

    // Dit is de vertaalmethode van RemoteController naar RemoteControllerDto
    public RemoteControllerDto fromRemoteController(RemoteController remoteController) {
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setName(remoteController.getName());
        dto.setBrand(remoteController.getBrand());
        dto.setPrice(remoteController.getPrice());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setOriginalStock(remoteController.getOriginalStock());

        return dto;
    }
}
