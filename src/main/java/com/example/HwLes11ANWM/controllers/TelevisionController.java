package com.example.HwLes11ANWM.controllers;

import com.example.HwLes11ANWM.dtos.IdInputDto;
import com.example.HwLes11ANWM.dtos.TelevisionDto;
import com.example.HwLes11ANWM.dtos.TelevisionInputDto;
import com.example.HwLes11ANWM.dtos.WallBracketDto;
import com.example.HwLes11ANWM.services.TelevisionService;
import com.example.HwLes11ANWM.services.TelevisionWallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    private final TelevisionWallBracketService televisionWallBracketService;

    public TelevisionController(TelevisionService televisionService,
                                TelevisionWallBracketService televisionWallBracketService){
        this.televisionService = televisionService;
        this.televisionWallBracketService = televisionWallBracketService;
        float f = 1f;
        double d = 1d;
        long l = 1l;
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

    // Deze methode is om alle wall brackets op te halen die aan een bepaalde television gekoppeld zijn.
    // Deze methode maakt gebruik van de televisionWallBracketService.
    @GetMapping("/televisions/wallBrackets/{televisionId}")
    public Collection<WallBracketDto> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId){
        return televisionWallBracketService.getWallBracketsByTelevisionId(televisionId);
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

    //Optie met path-variable en request-body
    @PutMapping("/{id}/remotecontroller")
    public void assignRemoteControllerToTelevision(@PathVariable("id") Long televisionId,@Valid @RequestBody IdInputDto remoteControllerId) {
        televisionService.assignRemoteControllerToTelevision(televisionId, remoteControllerId.id);
    }

    //optie met twee path-variables
    @PutMapping("/{id}/{ciModuleId}")
    public void assignCIModuleToTelevision(@PathVariable("id") Long televisionId, @PathVariable("ciModuleId") Long ciModuleId) {
        televisionService.assignCIModuleToTelevision(televisionId, ciModuleId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        //Verkorte weergave
        return televisionService.deleteTelevision(id);
    }
}
