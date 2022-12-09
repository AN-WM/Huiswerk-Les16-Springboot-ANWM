package com.example.HwLes11ANWM.controllers;

import com.example.HwLes11ANWM.dtos.RemoteControllerDto;
import com.example.HwLes11ANWM.dtos.RemoteControllerInputDto;
import com.example.HwLes11ANWM.services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/remotecontrollers")
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    @Autowired
    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getRemoteControllers() {
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController(@PathVariable("id") Long id) {
        RemoteControllerDto returnDto = remoteControllerService.getRemoteController(id);
        return ResponseEntity.ok(returnDto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createRemoteController(@RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        Long createdId = remoteControllerService.saveRemoteController(remoteControllerInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/remotecontrollers/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("RemoteController was added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRemoteController(@PathVariable Long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto returnRemoteControllerDto = remoteControllerService.updateRemoteController(id, remoteControllerInputDto);

        return ResponseEntity.ok(returnRemoteControllerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable Long id) {
        //Verkorte weergave
        return remoteControllerService.deleteRemoteController(id);
    }
}

