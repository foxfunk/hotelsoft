
package modelo;

import java.sql.Date;


public class pagar {
    
    private int idreserva;
    private Date fecha;
    private int id_descuento;
    private int total;

    public pagar() {
    }

    public pagar(int idreserva, Date fecha, int id_descuento, int total) {
        this.idreserva = idreserva;
        this.fecha = fecha;
        this.id_descuento = id_descuento;
        this.total = total;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_descuento() {
        return id_descuento;
    }

    public void setId_descuento(int id_descuento) {
        this.id_descuento = id_descuento;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
