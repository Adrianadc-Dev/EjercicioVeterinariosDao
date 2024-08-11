package dao.impl;

import dao.Idao;
import db.H2Connection;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoH2Veterinario implements Idao<Veterinario> {
    private static final Logger logger = Logger.getLogger(DaoH2Veterinario.class);
    private static final String INSERT = "INSERT INTO VETERINARIOS VALUES (DEFAULT,?,?,?,?)";

    private static  final String SELECT_ALL = "SELECT *FROM VETERINARIOS";

    @Override
    public Veterinario guardar(Veterinario veterinario) {
        Connection connection= null;
        Veterinario veterinarioARetornar = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,veterinario.getNumeroLicencia());
            preparedStatement.setString(2,veterinario.getNombre());
            preparedStatement.setString(3,veterinario.getApellido());
            preparedStatement.setString(4,veterinario.getEspecialidad());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }
            veterinarioARetornar = new Veterinario(id,veterinario.getNumeroLicencia(),veterinario.getNombre(),veterinario.getApellido(),veterinario.getEspecialidad());
            logger.info("veterinario guardado: "+veterinarioARetornar);

        }catch (Exception e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(e.getMessage());
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                }
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return veterinarioARetornar;
    }

    @Override
    public List<Veterinario> listarTodos() {
        Connection connection= null;
        List<Veterinario> veterinarios = new ArrayList<>();
        Veterinario veterinario = null;

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                Integer licencia = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido= resultSet.getString(4);
                String especialidad = resultSet.getString(5);
                veterinario = new Veterinario(id, licencia,nombre,apellido,especialidad);
                logger.info(veterinario);
                veterinarios.add(veterinario);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return veterinarios;
    }
}
