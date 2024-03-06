package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.dao.implementacion.DomicilioDaoH2;
import com.example.TrabajointegradorbackendI.model.Domicilio;
import com.example.TrabajointegradorbackendI.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {
    private IDao<Domicilio> iDao;

    public DomicilioService() {
        this.iDao = new DomicilioDaoH2();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return iDao.guardar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return iDao.listarTodos();
    }
}
