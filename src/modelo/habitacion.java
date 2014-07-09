

package modelo;

public class habitacion {
    private int idsector;
    private int numero;
    private int id_estado;
    private int valor;
    private int id_tipo_habitacion;

    public habitacion() {
    }

    public habitacion(int idsector, int numero, int id_estado, int valor, int id_tipo_habitacion) {
        this.idsector = idsector;
        this.numero = numero;
        this.id_estado = id_estado;
        this.valor = valor;
        this.id_tipo_habitacion = id_tipo_habitacion;
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
