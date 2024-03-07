package com.example.TrabajointegradorbackendI.dao.implementacion;

import com.example.TrabajointegradorbackendI.dao.BD;
import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.model.Odontologo;
import com.example.TrabajointegradorbackendI.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class PacienteDaoH2 implements IDao<Paciente> {


    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String DELETE = "DELETE * FROM PACIENTE WHERE ID=?";



    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(3, paciente.getFechaIngreso());
            preparedStatement.setString(3, String.valueOf(paciente.getDomicilio()));

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paciente;
    }


    @Override
    public Paciente buscarPorId(Integer id) {
        Connection connection = null;
        Paciente paciente = null;

        try {
            connection = BD.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, paciente.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setFechaIngreso(rs.getDate(5).toLocalDate());
                paciente.setDomicilio(rs.getString(6));


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paciente;
    }


    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
         Paciente paciente = null;

        try {
            connection = BD.getConnection();

            PreparedStatement pStmt = connection.prepareStatement(DELETE);
            pStmt.setInt(1, paciente.getId());
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }
}
