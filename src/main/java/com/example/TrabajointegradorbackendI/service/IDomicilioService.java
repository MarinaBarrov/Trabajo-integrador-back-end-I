package com.example.TrabajointegradorbackendI.service;


import com.example.TrabajointegradorbackendI.model.Domicilio;

import java.util.List;

public interface IDomicilioService {
    public Domicilio guardar (Domicilio domicilio);

    public List<Domicilio> listarTodos();
}
