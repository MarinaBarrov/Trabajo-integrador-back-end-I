package com.example.TrabajointegradorbackendI.dao.implementacion;

import com.example.TrabajointegradorbackendI.dao.BD;
import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PacienteDaoH2 implements IDao<Paciente> {


    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHADEINGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String DELETE = "DELETE * FROM PACIENTE WHERE ID=?";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES";
    private static final String UPDATE = "UPDATE PACIENTES SET NOMBRE=? WHERE ID=?";





    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.guardar(paciente.getDomicilio());


            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());

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
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setFechaIngreso(rs.getDate(5).toLocalDate());
                paciente.setInt(5, paciente.getDomicilio().getId());


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


        try {
            connection = BD.getConnection();

            PreparedStatement pStmt = connection.prepareStatement(DELETE);
            pStmt.setInt(1, id);
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


    @Override
    public void actualizar(Paciente paciente) {

        Connection connection = null;
        try {

            connection = BD.getConnection();

            PreparedStatement pStmt1 = connection.prepareStatement(UPDATE);
            pStmt1.setString(1, paciente.getNombre());
            pStmt1.setInt(2, 1);

            pStmt1.executeUpdate();


            ResultSet rs1 = pStmt1.executeQuery(SELECT_ALL);


            while (rs1.next()) {
                System.out.println(" paciente actializado" + rs1.getString(2));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection conexion = null;
        List<Paciente> listaPacientes = new ArrayList<>();

        try {
            conexion = BD.getConnection();
            PreparedStatement psUpdateById = conexion.prepareStatement(SELECT_ALL);
            ResultSet rs = psUpdateById.executeQuery();

            while (rs.next()) {
                Paciente paciente = null;
                paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setFechaIngreso(5, Date.valueOf(paciente.getFechaIngreso()));
                paciente.setInt(5, paciente.getDomicilio().getId());

                //TODO : ASHUDA PLIS CON LINEA 172 Y 173

                listaPacientes.add(paciente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listaPacientes;
    }
    }

