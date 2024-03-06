package com.example.TrabajointegradorbackendI.service;

import com.example.TrabajointegradorbackendI.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);

}
