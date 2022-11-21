package com.example.HwLes11ANWM.Controllers;

import com.example.HwLes11ANWM.Exceptions.RecordNotFoundException;
import com.example.HwLes11ANWM.Models.Television;
import com.example.HwLes11ANWM.Repositories.TelevisionRepository;
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
        if (id > 0 && id < televisionDatabase.count()) {
            return ResponseEntity.ok(televisionDatabase.findById(id));
        } else {
            throw new IndexOutOfBoundsException();
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
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television television) {
        if (id > 0 && id < televisionDatabase.count()) {
            television.setId((long) id);
            televisionDatabase.save(television);
            return ResponseEntity.ok(televisionDatabase.findById((long) id));
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
