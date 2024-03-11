package com.example.TrabajointegradorbackendI.controller;

import com.example.TrabajointegradorbackendI.model.Odontologo;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/odontologo")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/id") // TODO: revisar esto, la url te va a quedar localhost:8080/odontologos/id?id=1
    public Odontologo buscarOdontologoPorId(@RequestParam("id") Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        return odontologo;
    }
}
