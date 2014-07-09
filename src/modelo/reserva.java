

package modelo;

import java.sql.Date;


public class reserva {
    
    private String rut_cliente;
    private String email;
    private String nombre_cliente;
    private String apellido_cliente;
    private int celular_cliente;
    private Date fecha_reserva;
    private Date fecha_ing;
    private Date fecha_sale;
    private int id_habitacion;
    private int pago_anticipado;
    private int total;
    private String rut_user;

    public reserva() {
    }

    public reserva(String rut_cliente, String email, String nombre_cliente, String apellido_cliente, int celular_cliente, Date fecha_reserva, Date fecha_ing, Date fecha_sale, int id_habitacion, int pago_anticipado, int total, String rut_user) {
        this.rut_cliente = rut_cliente;
        this.email = email;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.celular_cliente = celular_cliente;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ing = fecha_ing;
        this.fecha_sale = fecha_sale;
        this.id_habitacion = id_habitacion;
        this.pago_anticipado = pago_anticipado;
        this.total = total;
        this.rut_user = rut_user;
    }

    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public int getCelular_cliente() {
        return celular_cliente;
    }

    public void setCelular_cliente(int celular_cliente) {
        this.celular_cliente = celular_cliente;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_ing() {
        return fecha_ing;
    }

    public void setFecha_ing(Date fecha_ing) {
        this.fecha_ing = fecha_ing;
    }

    public Date getFecha_sale() {
        return fecha_sale;
    }

    public void setFecha_sale(Date fecha_sale) {
        this.fecha_sale = fecha_sale;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getPago_anticipado() {
        return pago_anticipado;
    }

    public void setPago_anticipado(int pago_anticipado) {
        this.pago_anticipado = pago_anticipado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRut_user() {
        return rut_user;
    }

    public void setRut_user(String rut_user) {
        this.rut_user = rut_user;
    }
    
    
    
}
