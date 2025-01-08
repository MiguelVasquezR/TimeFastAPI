package pojo;

public class EstadoEnvio {

    private Integer idEstadoEnvio;
    private Integer idEnvio;
    private Envio envio;
    private String fecha;
    private String descripcion;
    private String estado;

    public EstadoEnvio() {
    }

    public EstadoEnvio(Integer idEstadoEnvio, Integer idEnvio, Envio envio, String fecha, String descripcion, String estado) {
        this.idEstadoEnvio = idEstadoEnvio;
        this.idEnvio = idEnvio;
        this.envio = envio;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdEstadoEnvio() {
        return idEstadoEnvio;
    }

    public void setIdEstadoEnvio(Integer idEstadoEnvio) {
        this.idEstadoEnvio = idEstadoEnvio;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstadoEnvio{" + "idEstadoEnvio=" + idEstadoEnvio + ", idEnvio=" + idEnvio + ", envio=" + envio + ", fecha=" + fecha + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
    
    

}
