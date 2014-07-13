/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Fox
 */
public class tipo_habitacion {
    
    private int idtipo_habitacion;
    private String descripcion;

    public tipo_habitacion() {
    }

    public tipo_habitacion(int idtipo_habitacion, String descripcion) {
        this.idtipo_habitacion = idtipo_habitacion;
        this.descripcion = descripcion;
    }

    public int getIdtipo_habitacion() {
        return idtipo_habitacion;
    }

    public void setIdtipo_habitacion(int idtipo_habitacion) {
        this.idtipo_habitacion = idtipo_habitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
       public String toString(){
        return descripcion;
    }
    
}
