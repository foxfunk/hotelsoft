/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author Fox
 */
public class admin_cocina {
    private int id_ing_c;
    private String consumo;
    private int valor;
    private Date fecha;
    private int idproductos_bar;

    public admin_cocina() {
    }

    public admin_cocina(int id_ing_c, String consumo, int valor, Date fecha, int idproductos_bar) {
        this.id_ing_c = id_ing_c;
        this.consumo = consumo;
        this.valor = valor;
        this.fecha = fecha;
        this.idproductos_bar = idproductos_bar;
    }

    public int getId_ing_c() {
        return id_ing_c;
    }

    public void setId_ing_c(int id_ing_c) {
        this.id_ing_c = id_ing_c;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdproductos_bar() {
        return idproductos_bar;
    }

    public void setIdproductos_bar(int idproductos_bar) {
        this.idproductos_bar = idproductos_bar;
    }
    
    
    
    
}
