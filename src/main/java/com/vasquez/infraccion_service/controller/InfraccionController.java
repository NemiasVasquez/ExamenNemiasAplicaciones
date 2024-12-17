package com.vasquez.infraccion_service.controller;

import com.vasquez.infraccion_service.entity.Infraccion;
import com.vasquez.infraccion_service.service.InfraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infracciones")
public class InfraccionController {

    @Autowired
    private InfraccionService infraccionService;

    @PostMapping("/")
    public ResponseEntity<Infraccion> registrarInfraccion(@RequestBody Infraccion infraccion) {
        Infraccion nuevaInfraccion = infraccionService.save(infraccion);
        return ResponseEntity.ok(nuevaInfraccion);
    }

    @GetMapping("/")
    public ResponseEntity<List<Infraccion>> obtenerInfracciones(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<Infraccion> infracciones = infraccionService.findAll(offset, limit);
        return ResponseEntity.ok(infracciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Infraccion> obtenerInfraccion(@PathVariable int id) {
        Infraccion infraccion = infraccionService.findById(id);
        return ResponseEntity.ok(infraccion);
    }

    @GetMapping("/usuario/{dni}")
    public ResponseEntity<List<Infraccion>> obtenerInfraccionesPorDni(
            @PathVariable String dni,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<Infraccion> infracciones = infraccionService.findByNombre(dni, offset, limit);
        return ResponseEntity.ok(infracciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Infraccion> anularInfraccion(@PathVariable int id) {
        Infraccion infraccionAnulada = infraccionService.annulInfraccion(id);
        return ResponseEntity.ok(infraccionAnulada);
    }
}
