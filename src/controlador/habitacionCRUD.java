/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import conexion.Conexion;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import modelo.habitacion;


/**
 *
 * @author Fox
 */
public class habitacionCRUD {
    
     Conexion c = new Conexion();
     public static  int id_sector;
     public static int numero;
    public static int id_estado;
    public static int valor;
    public static int idtipo_habitacon;
     public static InputStream foto;


    public void insertar(habitacion u) {
        boolean si = false;
        String sql = "INSERT INTO `habitacion`( `idsector`, `numero`, `id_estado`, `valor`, `idtipo_habitacion`, `foto`) values(?,?,?,?,?,?)";
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);

            st.setInt(1, u.getIdsector());
            st.setInt(2, u.getNumero());
            st.setInt(3, u.getId_estado());
            st.setInt(4, u.getValor());
            st.setInt(5, u.getId_tipo_habitacion());
            st.setBinaryStream(6, u.getFoto());

            st.executeUpdate();
        } catch (SQLException ex) {
            si = true;
           // JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, "la habitacion a sido ingresado anteriormente");
        } finally {
            c.desconectar();
            if (si) {

            } else {
                JOptionPane.showMessageDialog(null, "Habitacion Ingresada con exito");
            }
        }

    }

    public habitacion consultarPorrut(int num) {
        boolean si = false;
        habitacion u = null;
        String sql = "SELECT * FROM `habitacion` WHERE `numero`=?";
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            st.setInt(1, num);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                si = true;
                u = new habitacion();
                u.setIdsector(rs.getInt(2));
                u.setNumero(rs.getInt(3));
                u.setId_estado(rs.getInt(4));
                u.setValor(rs.getInt(5));
                u.setId_tipo_habitacion(rs.getInt(6));
                u.setFoto2(rs.getBlob(7));

            }

            if (si) {
                
                id_sector = u.getIdsector();
               numero = u.getNumero();
                id_estado = u.getId_estado();
                valor = u.getValor();
                idtipo_habitacon = u.getId_tipo_habitacion();
                foto = u.getFoto();
             

                System.out.println("encontre " + numero);
            } else {

                id_sector = 0;
                numero = 0;
                id_estado = 0;
                valor = 0;
                idtipo_habitacon = 0;
                foto = null;
              
                JOptionPane.showMessageDialog(null,"Habitacion no encontradoa");
            }

            //JOptionPane.showMessageDialog(null, numerox+""+""+nombrex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            c.desconectar();
        }
        return u;
    }
//
    public static CustomImageIcon getFoto(int num) {
        Conexion c = new Conexion();
        String sql = "SELECT foto FROM `habitacion` WHERE numero=? ";
        CustomImageIcon ii = null;
        InputStream is = null;
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            st.setInt(1, num);
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
    
    
    public void modificaruntipodeclase(habitacion u) {
        String sql = "UPDATE `habitacion` SET `idsector`=?,`numero`=?,`id_estado`=?,`valor`=?,`idtipo_habitacion`=? WHERE numero = ?";

        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);

            st.setInt(1, u.getIdsector());
            st.setInt(2, u.getNumero());
            st.setInt(3, u.getId_estado());
            st.setInt(4, u.getValor());
            st.setInt(5, u.getId_tipo_habitacion());
        
            st.setInt(6, u.getNumero());

            int res = st.executeUpdate();
            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Habitacion modificada");
            } else {
                System.out.println("Habitacion no modificado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El numero  corresponde a otra habitacion");
        } finally {
            c.desconectar();
        }

    }
    
    public ArrayList consultarTodos() {
        ArrayList lista = new ArrayList();
        String sql = "SELECT  sector.descripcion, `numero`, estado.descripcion, `valor`, tipo_habitacion.descripcion FROM `habitacion`,estado, `sector`, tipo_habitacion WHERE habitacion.`idsector`=sector.idsector and habitacion.`id_estado`=estado.`id_estado` and habitacion.`idtipo_habitacion`=tipo_habitacion.idtipo_habitacion";
        c.conectar();
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                habitacion b = new habitacion();
                b.setSector(rs.getString(1));
                b.setNumero(rs.getInt(2));
                b.setEstado(rs.getString(3));
                b.setValor(rs.getInt(4));
                b.setTipo_habitacion(rs.getString(5));
              
                

                lista.add(b);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            c.desconectar();
        }
        return lista;
    }
//
//    

//      public ArrayList<usuario> seleccionarporrut(String parametro) {
//
//        usuario book = null;
//        ArrayList<usuario> listatodo = new ArrayList<usuario>();
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement("SELECT `rut_user`,  `nombre`, `apellidop`, `apellidom`, perfil.descripcion FROM `usuario`,`perfil` WHERE usuario.id_perfil=perfil.`id_perfil` and rut_user LIKE  '%" + parametro + "%'");
//
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                 book = new usuario();
//               book.setRut_user(rs.getString(1));
//                book.setNombre(rs.getString(2));
//                book.setApellidop(rs.getString(3));
//                book.setApellidom(rs.getString(4));
//               
//                book.setPerfil(rs.getString(5));
//                
//
//                listatodo.add(book);
//            }
//            System.out.println("total " + listatodo.size());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//        c.desconectar();
//        return listatodo;
//    }
//      
//        public ArrayList<usuario> seleccionarpornombre(String parametro) {
//
//        usuario book = null;
//        ArrayList<usuario> listatodo = new ArrayList<usuario>();
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement("SELECT `rut_user`, `nombre`, `apellidop`, `apellidom`, perfil.descripcion FROM `usuario`,`perfil` WHERE usuario.id_perfil=perfil.`id_perfil` and nombre LIKE  '%" + parametro + "%'");
//
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                 book = new usuario();
//               book.setRut_user(rs.getString(1));
//                book.setNombre(rs.getString(2));
//                book.setApellidop(rs.getString(3));
//                book.setApellidom(rs.getString(4));
//              
//                book.setPerfil(rs.getString(5));
//                
//
//                listatodo.add(book);
//            }
//            System.out.println("total " + listatodo.size());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//        c.desconectar();
//        return listatodo;
//    }
//
//  public ArrayList<usuario> seleccionarporapellidop(String parametro) {
//
//        usuario book = null;
//        ArrayList<usuario> listatodo = new ArrayList<usuario>();
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement("SELECT `rut_user`,  `nombre`, `apellidop`, `apellidom`, perfil.descripcion FROM `usuario`,`perfil` WHERE usuario.id_perfil=perfil.`id_perfil` and apellidop LIKE  '%" + parametro + "%'");
//
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                 book = new usuario();
//               book.setRut_user(rs.getString(1));
//                book.setNombre(rs.getString(2));
//                book.setApellidop(rs.getString(3));
//                book.setApellidom(rs.getString(4));
//                book.setContraseña(rs.getString(5));
//                book.setPerfil(rs.getString(6));
//                
//
//                listatodo.add(book);
//            }
//            System.out.println("total " + listatodo.size());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//        c.desconectar();
//        return listatodo;
//    }
//
//    public ArrayList<usuario> seleccionarporapellidom(String parametro) {
//
//        usuario book = null;
//        ArrayList<usuario> listatodo = new ArrayList<usuario>();
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement("SELECT `rut_user`, `nombre`, `apellidop`, `apellidom`, perfil.descripcion FROM `usuario`,`perfil` WHERE usuario.id_perfil=perfil.`id_perfil` and apellidom LIKE  '%" + parametro + "%'");
//
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                 book = new usuario();
//               book.setRut_user(rs.getString(1));
//                book.setNombre(rs.getString(2));
//                book.setApellidop(rs.getString(3));
//                book.setApellidom(rs.getString(4));
//                book.setContraseña(rs.getString(5));
//                book.setPerfil(rs.getString(6));
//                
//
//                listatodo.add(book);
//            }
//            System.out.println("total " + listatodo.size());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//        c.desconectar();
//        return listatodo;
//    }
//    
    public void eliminarJugador(habitacion j) {
        String sql = "delete from habitacion where numero = ?"; 
        c.conectar(); 
        try { 
            PreparedStatement st = c.getConector().prepareStatement(sql);
            st.setInt(1, j.getNumero()); st.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "la habitacion Eliminado "); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage()); 
        } finally { 
            c.desconectar(); 
        } 
    }
}
