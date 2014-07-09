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
public class boleta {
    
    private int id_pago;
    private int iva;
    private int neto;
    private int total;

    public boleta() {
    }

    public boleta(int id_pago, int iva, int neto, int total) {
        this.id_pago = id_pago;
        this.iva = iva;
        this.neto = neto;
        this.total = total;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getNeto() {
        return neto;
    }

    public void setNeto(int neto) {
        this.neto = neto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
