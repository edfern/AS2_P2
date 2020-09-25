package main.webapp.model.dao;

import main.webapp.model.entity.UserEntity;
import org.apache.catalina.User;

import java.sql.ResultSet;

public class UserDao implements IUserDao {
    ConexionSingleton conexion = ConexionSingleton.getInstance();
    ResultSet rs;
    @Override
    public UserEntity getUser(String gmail,String pass,String key) {
        UserEntity entity = new UserEntity();
        try {
            conexion.openConection();
            String query = "";
            System.out.println(key);
            if(key == null){
                query = "select * from cashiers where gmail = '"+gmail+"'"+" and password = "+"'"+pass+"'";
            }else {
                query = "select * from cashiers where jbarilla_as2_equipo5.cashiers.key = "+"'"+key+"'";
            }

            rs = conexion.connection.createStatement().executeQuery(query);

            while (rs.next()){
                entity.setIdUser(rs.getInt("idUser"));
                entity.setFullName(rs.getString("fullName"));
                entity.setSurName(rs.getString("surName"));
                entity.setBirthday(rs.getString("birthday"));
                entity.setTaxId(rs.getString("taxId"));
                entity.setCivilStatus(rs.getString("civilStatus"));
                entity.setGender(rs.getString("gender"));
                entity.setAddress(rs.getString("address"));
                entity.setPhone(rs.getString("phone"));
                entity.setGmail(rs.getString("gmail"));
                entity.setUserName(rs.getString("userName"));
                entity.setPassword(rs.getString("password"));
                entity.setKey(rs.getString("key"));
            }
            conexion.closeConection();
        }catch (Exception e){
            System.out.println(e);
            entity.setIdUser(0);
            entity.setFullName("User no encontrado");
            entity.setSurName("User no encontrado");
            entity.setBirthday("User no encontrado");
            entity.setTaxId("User no encontrado");
            entity.setCivilStatus("User no encontrado");
            entity.setGender("User no encontrado");
            entity.setAddress("User no encontrado");
            entity.setPhone("User no encontrado");
            entity.setGmail("User no encontrado");
            entity.setUserName("User no encontrado");
            entity.setPassword("User no encontrado");
        }
        finally {
            conexion.closeConection();
        }
        return entity;
    }

}
