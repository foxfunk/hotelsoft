package conexion;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private String URL = "jdbc:mysql://localhost:3306/hotelsoft";
    private String driver = "com.mysql.jdbc.Driver";
    private String user = "fox";
    private String pass = "1234";
    private Connection conn = null;
    
    public void conectar(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(URL, user, pass);
           
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    public void desconectar(){
        try{
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public Connection getConector(){
        return conn;
    }

    public Object getConexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
