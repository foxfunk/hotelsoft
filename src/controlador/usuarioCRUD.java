/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import modelo.usuario;
import vista.login;

/**
 *
 * @author Fox
 */
public class usuarioCRUD {

    public static int id;
    public static String strut;
    public static String nombres;
    public static String apellidop;
    public static String apellidom;
    public static String contraseña;
    public static InputStream foto;

    public static boolean l = false;
    Conexion c = new Conexion();

    public usuario Login(String rut, String pass) {
        boolean si = false;
        usuario u = null;
        String sql = "SELECT `nombre`, `apellidop` FROM `usuario` WHERE rut_user=? and contraseña=?";
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            st.setString(1, rut);
            st.setString(2, pass);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                //JOptionPane.showMessageDialog(null, "aki");
                si = true;
                u = new usuario();
                u.setNombre(rs.getString(1));
                u.setApellidop(rs.getString(2));

            }

            if (si) {
                l = true;
                nombres = u.getNombre();
                apellidop = u.getApellidop();

            }

            //JOptionPane.showMessageDialog(null, numerox+""+""+nombrex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            c.desconectar();
        }
        return u;
    }

    public void insertar(usuario u) {
        boolean si = false;
        String sql = "insert into usuario (`rut_user`, `contraseña`, `nombre`, `apellidop`, `apellidom`, `id_perfil`, `foto`)values(?,?,?,?,?,?,?)";
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);

            st.setString(1, u.getRut_user());
            st.setString(2, u.getContraseña());
            st.setString(3, u.getNombre());
            st.setString(4, u.getApellidop());
            st.setString(5, u.getApellidom());
            st.setInt(6, u.getId_perfil());
            st.setBinaryStream(7, u.getFoto());

            st.executeUpdate();
        } catch (SQLException ex) {
            si = true;
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "el rut a sido ingresado anteriormente");
        } finally {
            c.desconectar();
            if (si) {

            } else {
                JOptionPane.showMessageDialog(null, "Usuario Ingresado con exito");
            }
        }

    }

    public usuario consultarPorrut(String rut) {
        boolean si = false;
        usuario u = null;
        String sql = "SELECT * FROM `usuario` WHERE rut_user = ?";
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            st.setString(1, rut);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                si = true;
                u = new usuario();
                u.setRut_user(rs.getString(1));
                u.setContraseña(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellidop(rs.getString(4));
                u.setApellidom(rs.getString(5));
                u.setId_perfil(rs.getInt(6));
                u.setFoto2(rs.getBlob(7));

            }

            if (si) {
            
                strut = u.getRut_user();
                contraseña = u.getContraseña();
                nombres = u.getNombre();
                apellidop = u.getApellidop();
                apellidom = u.getApellidom();
                foto = u.getFoto();


                System.out.println("encontre " + strut);
            } else {

                strut = "";
                nombres = "";
                apellidop = "";
                apellidom = "";
                contraseña = "";
                foto = null;
            }

            //JOptionPane.showMessageDialog(null, numerox+""+""+nombrex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            c.desconectar();
        }
        return u;
    }

    public static CustomImageIcon getFoto(String rut) {
        Conexion c = new Conexion();
        String sql = "SELECT foto FROM `usuario` WHERE rut_user=? ";
        CustomImageIcon ii = null;
        InputStream is = null;
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            st.setString(1, rut);
             ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                is = rs.getBinaryStream(1);
                if (is != null) {

                    BufferedImage bi = ImageIO.read(is);
                    ii = new CustomImageIcon(bi);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return ii;
    }
}
