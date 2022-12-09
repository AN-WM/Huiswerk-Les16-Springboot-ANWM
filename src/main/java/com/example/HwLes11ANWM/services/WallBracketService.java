package com.example.HwLes11ANWM.services;

import com.example.HwLes11ANWM.dtos.WallBracketDto;
import com.example.HwLes11ANWM.dtos.WallBracketInputDto;
import com.example.HwLes11ANWM.exceptions.DuplicateRecordException;
import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.WallBracket;
import com.example.HwLes11ANWM.repositories.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WallBracketService {

    @Autowired
    private WallBracketRepository wallBracketRepository;

    public Iterable<WallBracketDto> getAllWallBrackets() {
        Iterable<WallBracket> allWallBrackets = wallBracketRepository.findAll();
        ArrayList<WallBracketDto> newWallBracketList = new ArrayList<>();

        for (WallBracket t : allWallBrackets) {
            newWallBracketList.add(fromWallBracket(t));
        }

        return newWallBracketList;
    }

    public WallBracketDto getWallBracket(Long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);
        if (wallBracket.isEmpty()) {
            throw new IndexOutOfBoundsException(String.format("ID %d was not found", id));
        } else {
            WallBracket wallBracket1 = wallBracket.get();
            return fromWallBracket(wallBracket1);
        }
    }

    public Long saveWallBracket(WallBracketInputDto wallBracketInputDto) {
        String name = wallBracketInputDto.getName();
        if (wallBracketRepository.findByName(name) != null) {
            throw new DuplicateRecordException(String.format("A wallBracket with name %s already exists", name));
        } else {
            WallBracket savedWallBracket = wallBracketRepository.save(toWallBracket(wallBracketInputDto));
            return savedWallBracket.getId();
        }
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {
        if (wallBracketRepository.existsById(id)) {
            WallBracket convertedWallBracket = toWallBracket(wallBracketInputDto);
            convertedWallBracket.setId(id);
            wallBracketRepository.save(convertedWallBracket);
            return fromWallBracket(convertedWallBracket);
        } else {
            throw new IndexOutOfBoundsException(String.format("WallBracket with id %d was not found, cannot update", id));
        }
    }

    public ResponseEntity<Object> deleteWallBracket(Long id) {
        if (wallBracketRepository.existsById(id)) {
            wallBracketRepository.deleteById(id);
            return ResponseEntity.ok("WallBracket deleted from database");
        } else {
            throw new RecordNotFoundException(String.format("WallBracket with id %d was not found", id));
        }
    }

    public WallBracket toWallBracket(WallBracketInputDto dto){
        var wallBracket = new WallBracket();
        wallBracket.setName(dto.getName());
        wallBracket.setPrice(dto.getPrice());
        wallBracket.setSize(dto.getSize());
        wallBracket.setAdjustable(dto.getAdjustable());

        return wallBracket;
    }

    // Dit is de vertaalmethode van WallBracket naar WallBracketDto
    public WallBracketDto fromWallBracket(WallBracket wallBracket){
        WallBracketDto dto = new WallBracketDto();

        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());

        return dto;
    }
}
