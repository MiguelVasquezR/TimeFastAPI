package pojo;

public class Paquete {

    private Integer id;
    private Integer idPaquete;
    private Integer idEnvio;
    private Envio envio;
    private String descripcion;
    private String dimensiones;
    private Double peso;

    public Paquete() {
    }

    public Paquete(Integer id, Integer idPaquete, Integer idEnvio, Envio envio, String descripcion, String dimensiones, Double peso) {
        this.id = id;
        this.idPaquete = idPaquete;
        this.idEnvio = idEnvio;
        this.envio = envio;
        this.descripcion = descripcion;
        this.dimensiones = dimensiones;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Paquete{" + "id=" + id + ", idPaquete=" + idPaquete + ", idEnvio=" + idEnvio + ", envio=" + envio + ", descripcion=" + descripcion + ", dimensiones=" + dimensiones + ", peso=" + peso + '}';
    }
    
    

}
