package com.salt.spring.saltservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salt.spring.saltservice.dto.ResponseData;
import com.salt.spring.saltservice.model.Konsumen;
import com.salt.spring.saltservice.repository.KonsumenRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class KonsumenController {

    @Autowired
    KonsumenRepository konsumenRepository;

    @GetMapping("/konsumen")
    public ResponseEntity<List<Konsumen>> getAllKonsumen(@RequestParam(required = false) String param) {
        try {
            List<Konsumen> konsumen = new ArrayList<Konsumen>();

            if (param == null){
                konsumenRepository.findAll().forEach(konsumen::add);
            } else {
                konsumenRepository.findByParam(param).forEach(konsumen::add);
            }

            if (konsumen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(konsumen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/konsumen/{id}")
    private ResponseEntity<Konsumen> getEmployeeDetails(@PathVariable("id") int id) {
        Konsumen konsumen = konsumenRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(konsumen);
    }

    @PostMapping("/konsumen")
    public ResponseEntity<ResponseData<Konsumen>> addKonsumen(@RequestBody Konsumen konsumen) {
        try {
            ResponseData<Konsumen> responseData = new ResponseData<>();

            konsumenRepository.save(konsumen);

            responseData.setStatus(true);
            responseData.getMessages().add("Data createad successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData); // CREATED == 201
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/konsumen/{id}")
    public ResponseEntity<String> updateKonsumen(@PathVariable("id") int id, @RequestBody Konsumen konsumen) {
        try {
            Konsumen _konsumen = konsumenRepository.findById(id);

            if (_konsumen != null) {
                _konsumen.setId(id);
                _konsumen.setNama(konsumen.getNama());
                _konsumen.setAlamat(konsumen.getAlamat());
                _konsumen.setKota(konsumen.getKota());
                _konsumen.setProvinsi(konsumen.getProvinsi());
                _konsumen.setTglRegistrasi(konsumen.getTglRegistrasi());
                _konsumen.setStatus(konsumen.getStatus());

                konsumenRepository.update(_konsumen);
                return new ResponseEntity<>("Data updated successfully", HttpStatus.NO_CONTENT); // NO_CONTENT == 204
            } else {
                return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/konsumen/{id}")
    public ResponseEntity<String> deleteKonsumen(@PathVariable("id") int id) {
        try {
            int result = konsumenRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND); 
            }
            return new ResponseEntity<>("Data deleted successfully", HttpStatus.NO_CONTENT); // NO_CONTENT == 204
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
