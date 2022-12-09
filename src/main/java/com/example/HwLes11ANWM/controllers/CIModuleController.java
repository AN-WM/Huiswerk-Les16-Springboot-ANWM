package com.example.HwLes11ANWM.controllers;

import com.example.HwLes11ANWM.dtos.CIModuleDto;
import com.example.HwLes11ANWM.dtos.CIModuleInputDto;
import com.example.HwLes11ANWM.services.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {
    private final CIModuleService ciModuleService;

    @Autowired
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getCIModules() {
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable("id") Long id) {
        CIModuleDto returnDto = ciModuleService.getCIModule(id);
        return ResponseEntity.ok(returnDto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createCIModule(@RequestBody CIModuleInputDto ciModuleInputDto) {
        Long createdId = ciModuleService.saveCIModule(ciModuleInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/cimodules/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("CI-Module was added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCIModule(@PathVariable Long id, @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto returnCIModuleDto = ciModuleService.updateCIModule(id, ciModuleInputDto);

        return ResponseEntity.ok(returnCIModuleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable Long id) {
        //Verkorte weergave
        return ciModuleService.deleteCIModule(id);
    }
}
