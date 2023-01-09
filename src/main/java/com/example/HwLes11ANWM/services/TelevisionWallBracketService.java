package com.example.HwLes11ANWM.services;

import com.example.HwLes11ANWM.dtos.TelevisionDto;
import com.example.HwLes11ANWM.dtos.WallBracketDto;
import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.Television;
import com.example.HwLes11ANWM.models.TelevisionWallBracket;
import com.example.HwLes11ANWM.models.TelevisionWallBracketKey;
import com.example.HwLes11ANWM.models.WallBracket;
import com.example.HwLes11ANWM.repositories.TelevisionRepository;
import com.example.HwLes11ANWM.repositories.TelevisionWallBracketRepository;
import com.example.HwLes11ANWM.repositories.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class TelevisionWallBracketService {


// Deze klasse bevat de service methodes van TelevisionWallBracketController.
// Deze klasse wijkt af van de andere service-klassen, omdat deze in 3 verschillende controllers wordt ge-autowired.

    @Autowired
    private TelevisionRepository televisionRepository;

    @Autowired
    private WallBracketRepository wallBracketRepository;

    @Autowired
    private TelevisionWallBracketRepository televisionWallBracketRepository;

    public Collection<TelevisionDto> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionDto> dtos = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            Television television = televisionWallbracket.getTelevision();
            TelevisionDto televisionDto = new TelevisionDto();

            televisionDto.setType(television.getType());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setName(television.getName());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setAvailableSize(television.getAvailableSize());
            televisionDto.setRefreshRate(television.getRefreshRate());
            televisionDto.setScreenType(television.getScreenType());
            televisionDto.setScreenQuality(television.getScreenQuality());
            televisionDto.setSmartTv(television.getSmartTv());
            televisionDto.setWifi(television.getWifi());
            televisionDto.setVoiceControl(television.getVoiceControl());
            televisionDto.setHdr(television.getHdr());
            televisionDto.setBluetooth(television.getBluetooth());
            televisionDto.setAmbiLight(television.getAmbiLight());
            televisionDto.setOriginalStock(television.getOriginalStock());
            televisionDto.setSold(television.getSold());

            dtos.add(televisionDto);
        }
        return dtos;
    }

    public Collection<WallBracketDto> getWallBracketsByTelevisionId(Long televisionId) {
        Collection<WallBracketDto> dtos = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByTelevisionId(televisionId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            WallBracket wallBracket = televisionWallbracket.getWallBracket();
            var dto = new WallBracketDto();

            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());

            dtos.add(dto);
        }
        return dtos;
    }


    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        var televisionWallBracket = new TelevisionWallBracket();
        if (!televisionRepository.existsById(televisionId)) {
            throw new RecordNotFoundException();
        }
        Television television = televisionRepository.findById(televisionId).orElse(null);
        if (!wallBracketRepository.existsById(wallBracketId)) {
            throw new RecordNotFoundException();
        }
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);
        televisionWallBracket.setTelevision(television);
        televisionWallBracket.setWallBracket(wallBracket);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        televisionWallBracket.setId(id);
        televisionWallBracketRepository.save(televisionWallBracket);

        return id;
    }

}
