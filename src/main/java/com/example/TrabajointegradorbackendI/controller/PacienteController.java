package com.example.TrabajointegradorbackendI.controller;

import com.example.TrabajointegradorbackendI.model.Paciente;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }
}
