package com.example.HwLes11ANWM.controllers;

import com.example.HwLes11ANWM.dtos.TelevisionDto;
import com.example.HwLes11ANWM.dtos.WallBracketDto;
import com.example.HwLes11ANWM.dtos.WallBracketInputDto;
import com.example.HwLes11ANWM.services.TelevisionWallBracketService;
import com.example.HwLes11ANWM.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {
    private final WallBracketService wallBracketService;
    private final TelevisionWallBracketService televisionWallBracketService;

    public WallBracketController(WallBracketService wallBracketService,
                                 TelevisionWallBracketService televisionWallBracketService) {
        this.wallBracketService = wallBracketService;
        this.televisionWallBracketService = televisionWallBracketService;
    }


    @GetMapping("")
    public ResponseEntity<Object> getWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable("id") Long id) {
        WallBracketDto returnDto = wallBracketService.getWallBracket(id);
        return ResponseEntity.ok(returnDto);
    }

    // Deze methode haalt alle televisies op die aan een bepaalde wallbracket gekoppeld zijn.
    // Deze methode maakt gebruikt van de televisionWallBracketService.
    @GetMapping("/televisions/{wallBracketId}")
    public Collection<TelevisionDto> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId){
        return televisionWallBracketService.getTelevisionsByWallBracketId(wallBracketId);
    }

    @PostMapping("")
    public ResponseEntity<Object> createWallBracket(@RequestBody WallBracketInputDto wallBracketInputDto) {
        Long createdId = wallBracketService.saveWallBracket(wallBracketInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/wallbrackets/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("WallBracket was added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable Long id, @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketDto returnWallBracketDto = wallBracketService.updateWallBracket(id, wallBracketInputDto);

        return ResponseEntity.ok(returnWallBracketDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable Long id) {
        //Verkorte weergave
        return wallBracketService.deleteWallBracket(id);
    }
}
