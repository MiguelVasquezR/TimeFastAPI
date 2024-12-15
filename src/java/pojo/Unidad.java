package pojo;

public class Unidad {

    private Integer id;
    private String marca;
    private String modelo;
    private String anio;
    private String VIN;
    private String numIdentificacion;
    private String tipo;
    private String foto;
    private Integer idConductor;
    private Colaborador conductos;

    public Unidad() {
    }

    public Unidad(Integer id, String marca, String modelo, String anio, String VIN, String numIdentificacion, String tipo, String foto, Integer idConductor, Colaborador conductos) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.VIN = VIN;
        this.numIdentificacion = numIdentificacion;
        this.tipo = tipo;
        this.foto = foto;
        this.idConductor = idConductor;
        this.conductos = conductos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public Colaborador getConductos() {
        return conductos;
    }

    public void setConductos(Colaborador conductos) {
        this.conductos = conductos;
    }

    @Override
    public String toString() {
        return "Unidad{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + ", VIN=" + VIN + ", numIdentificacion=" + numIdentificacion + ", tipo=" + tipo + ", foto=" + foto + ", idConductor=" + idConductor + ", conductos=" + conductos + '}';
    }

}
