/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.FileInputStream;
import java.sql.Blob;

/**
 *
 * @author Fox
 */
public class usuario {
    
    private String rut_user;
    private String contraseña;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private FileInputStream foto;
    private Blob foto2;
    private int id_perfil;
    private String perfil;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    

    public usuario() {
        
        
        
    }

    public usuario(String rut_user, String contraseña, String nombre, String apellidop, String apellidom, int id_perfil, FileInputStream foto, Blob foto2) {
        this.rut_user = rut_user;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.id_perfil = id_perfil;
        this.foto = foto;
          this.foto2 = foto2;
    }

    public String getRut_user() {
        return rut_user;
    }

    public void setRut_user(String rut_user) {
        this.rut_user = rut_user;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public FileInputStream getFoto() {
        return foto;
    }

    public void setFoto(FileInputStream foto) {
        this.foto = foto;
    }

    public Blob getFoto2() {
        return foto2;
    }

    public void setFoto2(Blob foto2) {
        this.foto2 = foto2;
    }

    
    
    
    
    
}
