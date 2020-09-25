package main.webapp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {
    public static ConexionSingleton conectionDB;
    public Connection connection;

    private ConexionSingleton(){

    }
    public static ConexionSingleton getInstance(){
        if(conectionDB == null){
            conectionDB = new ConexionSingleton();
        }
        return conectionDB;
    }

    public void openConection() {

        try {
            String urlConexion = "jdbc:mysql://192.185.4.65:3306/jbarilla_as2_equipo5?serverTimezone=UTC";
            String usuario = "jbarilla_as2";
            String contra = "agEO5ZTI.VC8";
            connection = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("Conexion eexitosa");
        } catch ( SQLException e) {
            //System.out.println(e);
            closeConection();
        }

    }

    public void closeConection(){
        try{
            connection.close();
            System.out.println("Cerrando conexion");
        } catch(SQLException ex){
            System.out.println("error");
        }
    }
}
