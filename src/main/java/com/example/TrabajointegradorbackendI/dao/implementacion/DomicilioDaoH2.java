package com.example.TrabajointegradorbackendI.dao.implementacion;

import com.example.TrabajointegradorbackendI.dao.BD;
import com.example.TrabajointegradorbackendI.dao.IDao;
import com.example.TrabajointegradorbackendI.model.Domicilio;
import com.example.TrabajointegradorbackendI.model.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {


    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";
    private static final String UPDATE = "UPDATE DOMICILIOS SET CALLE=? WHERE ID=?";
    private static final String SELECT_BY_ID = "SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String DELETE = "DELETE * FROM DOMICILIOS WHERE ID=?";


    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;

        try {

            connection = BD.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                domicilio.setId(resultSet.getInt(1));
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
        return domicilio;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {

        Connection conexion = null;
        Domicilio domicilio = null;
        try {
            conexion = BD.getConnection();

            PreparedStatement psSearchByID = conexion.prepareStatement(SELECT_BY_ID);
            psSearchByID.setInt(1, id);

            ResultSet rs = psSearchByID.executeQuery();

            while (rs.next()) {
                domicilio = new Domicilio();
                domicilio.setId(rs.getInt(1));
                domicilio.setCalle(rs.getString(2));
                domicilio.setNumero(rs.getInt(3));
                domicilio.setLocalidad(rs.getString(4));
                domicilio.setProvincia(rs.getString(5));
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
        return domicilio;
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
    public void actualizar(Domicilio domicilio) {

        Connection connection = null;
        try {

            connection = BD.getConnection();

            PreparedStatement pStmt1 = connection.prepareStatement(UPDATE);
            pStmt1.setString(1, domicilio.getCalle());
            pStmt1.setInt(2, domicilio.getId());

            pStmt1.executeUpdate();


            ResultSet rs1 = pStmt1.executeQuery(SELECT_ALL);


            while (rs1.next()) {
                System.out.println(" Domicilio actualizado" + rs1.getString(2));
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
    public List<Domicilio> listarTodos() {
        Connection connection = null;
        List<Domicilio> domicilioList = new ArrayList<>();

        try {
            connection = BD.getConnection();

            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                Domicilio domicilio = null;
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));

                domicilioList.add(domicilio);
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
        return domicilioList;
    }
}
