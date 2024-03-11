package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.dao.implementacion.TurnoDaoList;
import com.example.TrabajointegradorbackendI.model.Turno;
import com.example.TrabajointegradorbackendI.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private TurnoDaoList turnoDaoList;
    public TurnoService() {
        turnoDaoList = new TurnoDaoList();
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoDaoList.guardar(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Turno turno) {

    }
}
