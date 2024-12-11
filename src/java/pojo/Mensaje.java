package pojo;

public class Mensaje {

    private String mensaje;
    private Boolean error;
    private Object objeto;

    public Mensaje() {
    }

    public Mensaje(String mensaje, Boolean error, Object objeto) {
        this.mensaje = mensaje;
        this.error = error;
        this.objeto = objeto;
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

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "mensaje=" + mensaje + ", error=" + error + ", objeto=" + objeto + '}';
    }
    
    

}
