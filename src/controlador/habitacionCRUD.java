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
            JOptionPane.showMessageDialog(null, ex.getMessage());
            //JOptionPane.showMessageDialog(null, "el rut a sido ingresado anteriormente");
        } finally {
            c.desconectar();
            if (si) {

            } else {
                JOptionPane.showMessageDialog(null, "Habitacion Ingresada con exito");
            }
        }

    }

//    public habitacion consultarPorrut(String rut) {
//        boolean si = false;
//        habitacion u = null;
//        String sql = "SELECT * FROM `usuario` WHERE rut_user = ?";
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement(sql);
//            st.setString(1, rut);
//
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                si = true;
//                u = new usuario();
//                u.setRut_user(rs.getString(1));
//                u.setContraseña(rs.getString(2));
//                u.setNombre(rs.getString(3));
//                u.setApellidop(rs.getString(4));
//                u.setApellidom(rs.getString(5));
//                u.setId_perfil(rs.getInt(6));
//                u.setFoto2(rs.getBlob(7));
//
//            }
//
//            if (si) {
//                
//                strut = u.getRut_user();
//                contraseña = u.getContraseña();
//                nombres = u.getNombre();
//                apellidop = u.getApellidop();
//                apellidom = u.getApellidom();
//                foto = u.getFoto();
//                id = u.getId_perfil();
//
//                System.out.println("encontre " + strut);
//            } else {
//
//                strut = "";
//                nombres = "";
//                apellidop = "";
//                apellidom = "";
//                contraseña = "";
//                foto = null;
//                id = 0;
//                JOptionPane.showMessageDialog(null,"Usuario no encontrado");
//            }
//
//            //JOptionPane.showMessageDialog(null, numerox+""+""+nombrex);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        } finally {
//            c.desconectar();
//        }
//        return u;
//    }
//
//    public static CustomImageIcon getFoto(String rut) {
//        Conexion c = new Conexion();
//        String sql = "SELECT foto FROM `usuario` WHERE rut_user=? ";
//        CustomImageIcon ii = null;
//        InputStream is = null;
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement(sql);
//            st.setString(1, rut);
//             ResultSet rs = st.executeQuery();
//            
//            if (rs.next()) {
//                is = rs.getBinaryStream(1);
//                if (is != null) {
//
//                    BufferedImage bi = ImageIO.read(is);
//                    ii = new CustomImageIcon(bi);
//                }
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        return ii;
//    }
//    
//    
//    public void modificaruntipodeclase(usuario u) {
//        String sql = "UPDATE `usuario` SET `contraseña`=?,`nombre`=?,`apellidop`=?,`apellidom`=?,`id_perfil`=? WHERE rut_user = ?";
//
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement(sql);
//
//            st.setString(1, u.getContraseña());
//            st.setString(2, u.getNombre());
//            st.setString(3, u.getApellidop());
//            st.setString(4, u.getApellidom());
//            st.setInt(5, u.getId_perfil());
//        
//            st.setString(6, u.getRut_user());
//
//            int res = st.executeUpdate();
//            if (res == 1) {
//                JOptionPane.showMessageDialog(null, "usuario modificado");
//            } else {
//                System.out.println("usuario no modificado");
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Rut no valido corresponde a otra persona");
//        } finally {
//            c.desconectar();
//        }
//
//    }
//    
//    public ArrayList consultarTodos() {
//        ArrayList lista = new ArrayList();
//        String sql = "SELECT `rut_user`, `contraseña`, `nombre`, `apellidop`, `apellidom`, perfil.descripcion FROM `usuario`,`perfil` WHERE usuario.id_perfil=perfil.`id_perfil`";
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                usuario b = new usuario();
//                b.setRut_user(rs.getString(1));
//                b.setNombre(rs.getString(2));
//                b.setApellidop(rs.getString(3));
//                b.setApellidom(rs.getString(4));
//                b.setContraseña(rs.getString(5));
//                b.setPerfil(rs.getString(6));
//                
//
//                lista.add(b);
//
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        } finally {
//            c.desconectar();
//        }
//        return lista;
//    }
//
//    
//     public ArrayList consultarTodasesiones() {
//        ArrayList lista = new ArrayList();
//        String sql = "SELECT usuario.rut_user, usuario.nombre, usuario.apellidop, usuario.apellidom, perfil.descripcion, sesion.hora, sesion.fecha FROM `usuario`,`perfil`,sesion WHERE sesion.rut_user=usuario.rut_user and usuario.id_perfil=perfil.`id_perfil` ";
//        c.conectar();
//        try {
//            PreparedStatement st = c.getConector().prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                usuario b = new usuario();
//                sesion s = new sesion();
//                b.setRut_user(rs.getString(1));
//                b.setNombre(rs.getString(2));
//                b.setApellidop(rs.getString(3));
//                b.setApellidom(rs.getString(4));
//               
//                b.setPerfil(rs.getString(5));
//                b.setHora(rs.getTime(6));
//                b.setFecha(rs.getString(7));
//                
//
//                lista.add(b);
//                
//
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        } finally {
//            c.desconectar();
//        }
//        return lista;
//    }
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
//    public void eliminarJugador(usuario j) {
//        String sql = "delete from usuario where rut_user = ?"; 
//        c.conectar(); 
//        try { 
//            PreparedStatement st = c.getConector().prepareStatement(sql);
//            st.setString(1, j.getRut_user()); st.executeUpdate(); 
//            JOptionPane.showMessageDialog(null, "usuario Eliminado "); 
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage()); 
//        } finally { 
//            c.desconectar(); 
//        } 
//    }
}
