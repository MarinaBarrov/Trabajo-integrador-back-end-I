package com.example.TrabajointegradorbackendI.dao.implementacion;

import com.example.TrabajointegradorbackendI.dao.BD;
import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.model.Domicilio;
import com.example.TrabajointegradorbackendI.model.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {


    private static final String INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SELECT_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    private static final String DELETE = "DELETE * FROM ODONTOLOGOS WHERE ID=?";
    private static final String UPDATE = "UPDATE ODONTOLOGOS SET NOMBRE=? WHERE ID=?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGOS, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setString(3, odontologo.getMatricula());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
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
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection conexion = null;
        Odontologo odontologo = null;
        try {
            conexion = BD.getConnection();
            PreparedStatement psSearchByID = conexion.prepareStatement(SELECT_BY_ID);
            psSearchByID.setInt(1, id);
            ResultSet rs = psSearchByID.executeQuery();

            while (rs.next()) {
                odontologo = new Odontologo();
                odontologo.setId(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));
                odontologo.setMatricula(rs.getString(4));
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
        return odontologo;
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
    public void actualizar(Odontologo odontologo) {
        Connection connection = null;
        try {

            connection = BD.getConnection();

            PreparedStatement pStmt1 = connection.prepareStatement(UPDATE);
            pStmt1.setString(1, odontologo.getNombre());
            pStmt1.setInt(2, 1);

            pStmt1.executeUpdate();


            ResultSet rs1 = pStmt1.executeQuery(SELECT_ALL);


            while (rs1.next()) {
                System.out.println(" Odontologo" + rs1.getString(2));
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
    public List<Odontologo> listarTodos() {

        Connection conexion = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();

        try {
            conexion = BD.getConnection();
            PreparedStatement psUpdateById = conexion.prepareStatement(SELECT_ALL);
            ResultSet rs = psUpdateById.executeQuery();

            while (rs.next()) {
                Odontologo odontologo = null;
                odontologo = new Odontologo();
                odontologo.setId(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));
                odontologo.setMatricula(rs.getString(4));

                listaOdontologos.add(odontologo);
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
        return listaOdontologos;
    }

}
