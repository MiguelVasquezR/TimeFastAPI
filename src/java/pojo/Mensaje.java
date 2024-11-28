package pojo;

public class Mensaje {

    private String mensaje;
    private Boolean error;
    private Cliente cliente;

    public Mensaje() {
    }

    public Mensaje(String mensaje, Boolean error, Cliente cliente) {
        this.mensaje = mensaje;
        this.error = error;
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "mensaje=" + mensaje + ", error=" + error + ", cliente=" + cliente + '}';
    }
    
    

}
