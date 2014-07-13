

package modelo;

import java.io.FileInputStream;
import java.sql.Blob;

public class habitacion {
    private int idsector;
    private String sector;
    private int numero;
    private int id_estado;
    private String estado;
    private int valor;
    private int id_tipo_habitacion;
    private String tipo_habitacion;
 private FileInputStream foto;
    private Blob foto2;

    public habitacion() {
    }

    public habitacion(int idsector, String sector, int numero, int id_estado, String estado, int valor, int id_tipo_habitacion, String tipo_habitacion, FileInputStream foto, Blob foto2) {
        this.idsector = idsector;
        this.sector = sector;
        this.numero = numero;
        this.id_estado = id_estado;
        this.estado = estado;
        this.valor = valor;
        this.id_tipo_habitacion = id_tipo_habitacion;
        this.tipo_habitacion = tipo_habitacion;
        this.foto = foto;
        this.foto2 = foto2;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
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

    

    public int getIdsector() {
        return idsector;
    }

    public void setIdsector(int idsector) {
        this.idsector = idsector;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getId_tipo_habitacion() {
        return id_tipo_habitacion;
    }

    public void setId_tipo_habitacion(int id_tipo_habitacion) {
        this.id_tipo_habitacion = id_tipo_habitacion;
    }
    
    
}
