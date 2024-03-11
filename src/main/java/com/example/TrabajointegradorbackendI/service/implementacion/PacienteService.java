package com.example.TrabajointegradorbackendI.service.implementacion;

import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.dao.implementacion.PacienteDaoH2;
import com.example.TrabajointegradorbackendI.model.Paciente;
import com.example.TrabajointegradorbackendI.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService() {
    this.pacienteIDao = new PacienteDaoH2();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return null;
    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualiza(Paciente paciente) {

    }
}
