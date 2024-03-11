package com.example.TrabajointegradorbackendI.dao.implementacion;

import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDaoList implements IDao<Turno> {

    private List<Turno> turnoList = new ArrayList<>();

    public Turno guardar(Turno turno) {
        turnoList.add(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoADevolver = null;

        for (Turno turno: turnoList) {
            if (turno.getId().equals(id)) {
                turnoADevolver = turno;
                return turnoADevolver;
            }
        }
        return turnoADevolver;
    }

    @Override
    public void eliminar(Integer id) {
        //podríamos llamar al método que busca por id
    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }
}

