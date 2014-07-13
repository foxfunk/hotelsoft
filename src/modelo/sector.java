/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


public class sector {
    
    private int id_sector;
    private String descripcion;

    public sector() {
    }

    public sector(int id_sector, String descripcion) {
        this.id_sector = id_sector;
        this.descripcion = descripcion;
    }

    public int getId_sector() {
        return id_sector;
    }

    public void setId_sector(int id_sector) {
        this.id_sector = id_sector;
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
