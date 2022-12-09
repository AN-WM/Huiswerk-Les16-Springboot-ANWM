package com.example.HwLes11ANWM.services;

import com.example.HwLes11ANWM.dtos.CIModuleDto;
import com.example.HwLes11ANWM.dtos.CIModuleInputDto;
import com.example.HwLes11ANWM.exceptions.DuplicateRecordException;
import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.CIModule;
import com.example.HwLes11ANWM.repositories.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CIModuleService {

    @Autowired
    private CIModuleRepository ciModuleRepository;

    public Iterable<CIModuleDto> getAllCIModules() {
        Iterable<CIModule> allCIModules = ciModuleRepository.findAll();
        ArrayList<CIModuleDto> newCIModuleList = new ArrayList<>();

        for (CIModule c : allCIModules) {
            newCIModuleList.add(fromCIModule(c));
        }

        return newCIModuleList;
    }

    public CIModuleDto getCIModule(Long id) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);
        if (ciModule.isEmpty()) {
            throw new IndexOutOfBoundsException(String.format("ID %d was not found", id));
        } else {
            CIModule ciModule1 = ciModule.get();
            return fromCIModule(ciModule1);
        }
    }

    public Long saveCIModule(CIModuleInputDto ciModuleInputDto) {
        String type = ciModuleInputDto.getType();
        if (ciModuleRepository.findByType(type) != null) {
            throw new DuplicateRecordException(String.format("A CI-Module with type %s already exists", type));
        } else {
            CIModule savedCIModule = ciModuleRepository.save(toCIModule(ciModuleInputDto));
            return savedCIModule.getId();
        }
    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto ciModuleInputDto) {
        if (ciModuleRepository.existsById(id)) {
            CIModule convertedCIModule = toCIModule(ciModuleInputDto);
            convertedCIModule.setId(id);
            ciModuleRepository.save(convertedCIModule);
            return fromCIModule(convertedCIModule);
        } else {
            throw new IndexOutOfBoundsException(String.format("CI-Module with id %d was not found, cannot update", id));
        }
    }

    public ResponseEntity<Object> deleteCIModule(Long id) {
        if (ciModuleRepository.existsById(id)) {
            ciModuleRepository.deleteById(id);
            return ResponseEntity.ok("CIModule deleted from database");
        } else {
            throw new RecordNotFoundException(String.format("CIModule with id %d was not found", id));
        }
    }

    public CIModule toCIModule(CIModuleInputDto dto) {
        var ciModule = new CIModule();
        ciModule.setName(dto.getName());
        ciModule.setType(dto.getType());
        ciModule.setPrice(dto.getPrice());

        return ciModule;
    }

    // Dit is de vertaalmethode van CIModule naar CIModuleDto
    public CIModuleDto fromCIModule(CIModule ciModule) {
        CIModuleDto dto = new CIModuleDto();

        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        return dto;
    }
}
