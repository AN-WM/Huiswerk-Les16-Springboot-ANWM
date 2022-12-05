package com.example.HwLes11ANWM.services;

import com.example.HwLes11ANWM.dto.TelevisionDto;
import com.example.HwLes11ANWM.dto.TelevisionInputDto;
import com.example.HwLes11ANWM.exceptions.DuplicateRecordException;
import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.Television;
import com.example.HwLes11ANWM.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TelevisionService {

    @Autowired
    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository tvRepos) {
        this.televisionRepository = tvRepos;
    }

    public Iterable<TelevisionDto> getAllTelevisions() {
        Iterable<Television> allTelevisions = televisionRepository.findAll();
        ArrayList<TelevisionDto> newTelevisionList = new ArrayList<>();

        for (Television t : allTelevisions) {
            newTelevisionList.add(fromTelevision(t));
        }

        return newTelevisionList;
    }

    public TelevisionDto getTelevision(Long id) {
        Optional<Television> television = televisionRepository.findById(id);
        if (television.isEmpty()) {
            throw new IndexOutOfBoundsException("ID " + id + " was not found");
        } else {
            Television television1 = television.get();
            return fromTelevision(television1);
        }
    }

    public Long saveTelevision(Television television) {
        String type = television.getType();
        if (televisionRepository.findByType(type) != null) {
            throw new DuplicateRecordException("A television with type " + type + " already exists");
        } else {
            Television savedTelevision = televisionRepository.save(television);
            return savedTelevision.getId();
        }
    }

    public TelevisionDto updateTelevision(Long id, Television television) {
        if (televisionRepository.existsById(id)) {
            television.setId(id);
            televisionRepository.save(television);
            return fromTelevision(television);
        } else {
            throw new IndexOutOfBoundsException("No tv with id " + id + " was found, cannot update");
        }
    }

    public ResponseEntity<Object> deleteTelevision(Long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
            return ResponseEntity.ok("Television deleted from database");
        } else {
            throw new RecordNotFoundException("Television with id " + id + " was not found");
        }
    }

    public Television toTelevision(TelevisionInputDto dto){
        var television = new Television();
        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());

        return television;
    }

    // Dit is de vertaal methode van Television naar TelevisionDto
    public TelevisionDto fromTelevision(Television television){
        TelevisionDto dto = new TelevisionDto();

        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getWifi());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        return dto;
    }
}
