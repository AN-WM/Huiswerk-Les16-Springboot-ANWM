package com.example.HwLes11ANWM.controllers;

import com.example.HwLes11ANWM.exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.models.Television;
import com.example.HwLes11ANWM.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    @Autowired
    private TelevisionRepository televisionDatabase;

    @GetMapping("")
    public ResponseEntity<Object> getTelevisions() {
        return ResponseEntity.ok(televisionDatabase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable Long id) {
        if (televisionDatabase.existsById(id)) {
            return ResponseEntity.ok(televisionDatabase.findById(id));
        } else {
            throw new IndexOutOfBoundsException("ID " + id + "was not found");
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createTelevision(@RequestBody Television television) {
        String type = television.getType();
        if (televisionDatabase.findByType(type).equals(television)) {
            return new ResponseEntity<>("Television already exists!", HttpStatus.CONFLICT);
        } else {
            televisionDatabase.save(television);
            return ResponseEntity.created(null).body("Television was added");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        if (televisionDatabase.existsById(id)) {
            television.setId(id);
            televisionDatabase.save(television);
            return ResponseEntity.ok(televisionDatabase.findById(id));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    //Deze functie krijg ik niet meer werkend met de repository, dus heb ik vervangen door een mapping gebaseerd op ID.
//    @DeleteMapping("")
//    public ResponseEntity<Object> deleteTelevision(@RequestBody String type) {
//        if (televisionDatabase.findByType(type) != null) {
//            televisionDatabase.delete((Television) televisionDatabase.findByType(type));
//            return ResponseEntity.ok("Television removed");
//        } else {
//            throw new RecordNotFoundException("Television not found");
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        if (!televisionDatabase.findById(id).isEmpty()) {
            televisionDatabase.deleteById(id);
            return ResponseEntity.ok("Television removed");
        } else {
            throw new RecordNotFoundException("Television not found");
        }
    }
}
