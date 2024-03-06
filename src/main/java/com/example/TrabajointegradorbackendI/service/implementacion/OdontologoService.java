package com.example.TrabajointegradorbackendI.service.implementacion;


import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.dao.implementacion.OdontologoDaoH2;
import com.example.TrabajointegradorbackendI.model.Odontologo;
import com.example.TrabajointegradorbackendI.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDao;

    public OdontologoService() {
        iDao = new OdontologoDaoH2();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }
}
