package com.example.HwLes11ANWM.controllers;

import com.example.HwLes11ANWM.dto.TelevisionDto;
import com.example.HwLes11ANWM.dto.TelevisionInputDto;
import com.example.HwLes11ANWM.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id") Long id) {
        TelevisionDto returnDto = televisionService.getTelevision(id);
        return ResponseEntity.ok(returnDto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTelevision(@RequestBody TelevisionInputDto televisionInputDto) {
        Long createdId = televisionService.saveTelevision(televisionInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/televisions/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Television was added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto returnTelevisionDto = televisionService.updateTelevision(id, televisionInputDto);

        return ResponseEntity.ok(returnTelevisionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        //Verkorte weergave
        return televisionService.deleteTelevision(id);
    }
}
