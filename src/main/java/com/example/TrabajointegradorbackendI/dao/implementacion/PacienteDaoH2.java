package com.example.TrabajointegradorbackendI.dao.implementacion;

import com.example.TrabajointegradorbackendI.dao.BD;
import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


public class PacienteDaoH2 implements IDao<Paciente> {



    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {

        }
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
    public void actualizar(Paciente paciente) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }
}
