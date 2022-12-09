package com.example.HwLes11ANWM.services;

import com.example.HwLes11ANWM.dtos.TelevisionDto;
import com.example.HwLes11ANWM.dtos.TelevisionInputDto;
import com.example.HwLes11ANWM.exceptions.DuplicateRecordException;
import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.CIModule;
import com.example.HwLes11ANWM.models.RemoteController;
import com.example.HwLes11ANWM.models.Television;
import com.example.HwLes11ANWM.repositories.CIModuleRepository;
import com.example.HwLes11ANWM.repositories.RemoteControllerRepository;
import com.example.HwLes11ANWM.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CIModuleRepository ciModuleRepository;

    @Autowired
    public TelevisionService (TelevisionRepository televisionRepository,
                              RemoteControllerRepository remoteControllerRepository,
                              CIModuleRepository ciModuleRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
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
            throw new IndexOutOfBoundsException(String.format("ID %d was not found", id));
        } else {
            Television television1 = television.get();
            return fromTelevision(television1);
        }
    }

    public Long saveTelevision(TelevisionInputDto televisionInputDto) {
        String type = televisionInputDto.getType();
        if (televisionRepository.findByType(type) != null) {
            throw new DuplicateRecordException(String.format("A television with type %s already exists", type));
        } else {
            Television savedTelevision = televisionRepository.save(toTelevision(televisionInputDto));
            return savedTelevision.getId();
        }
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) {
        if (televisionRepository.existsById(id)) {
            Television convertedTelevision = toTelevision(televisionInputDto);
            convertedTelevision.setId(id);
            televisionRepository.save(convertedTelevision);
            return fromTelevision(convertedTelevision);
        } else {
            throw new IndexOutOfBoundsException(String.format("Television with id %d was not found, cannot update", id));
        }
    }

    public ResponseEntity<Object> deleteTelevision(Long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
            return ResponseEntity.ok("Television deleted from database");
        } else {
            throw new RecordNotFoundException(String.format("Television with id %d was not found", id));
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

    // Dit is de vertaalmethode van Television naar TelevisionDto
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

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            Television television = optionalTelevision.get();
            RemoteController remoteController = optionalRemoteController.get();

            television.setRemoteController(remoteController);
            televisionRepository.save(television);

            //Persoonlijke aanvulling om de foutmeldingen af te vangen en te verduidelijken
        } else if (optionalTelevision.isEmpty() && optionalRemoteController.isPresent()){
            throw new RecordNotFoundException(String.format("The television with id %d was not found", id));
        } else if (optionalRemoteController.isEmpty() && optionalTelevision.isPresent()) {
            throw new RecordNotFoundException(String.format("The remote controller with id %d was not found", remoteControllerId));
        } else {
            throw new RecordNotFoundException("This television and remote controller could not be found");
        }
    }

//    De formule voor een verkeerd om One to Many. Oeps.
//    public void assignCIModuleToTelevision(Long televisionId, Long ciModuleId) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
//        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(ciModuleId);
//
//        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
//            Television television = optionalTelevision.get();
//            CIModule ciModule = optionalCIModule.get();
//            List<CIModule> ciModuleList = television.getCiModuleList();
//
//            ciModuleList.add(ciModule);
//            television.setCiModuleList(ciModuleList);
//            televisionRepository.save(television);
//        }
//    }

    public void assignCIModuleToTelevision(Long televisionId, Long ciModuleId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(ciModuleId);

        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            Television television = optionalTelevision.get();
            CIModule ciModule = optionalCIModule.get();

            television.setCiModule(ciModule);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException("Either the television or the CI-Module could not be found");
        }
    }
}
