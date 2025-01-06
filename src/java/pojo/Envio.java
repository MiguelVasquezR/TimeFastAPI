package pojo;

import java.util.ArrayList;

public class Envio {

    private Integer idEnvio;
    private Integer idOrigen;
    private Integer idDestino;
    private Integer idCliente;
    private Direccion origen;
    private Direccion destino;
    private Cliente cliente;
    private Colaborador conductor;
    private Double costo;
    private String fecha;
    private String numGuia;
    private List<Paquete> paquetes;
    private List<EstadoEnvio> estadoEnvios;
    private String fechaEntrega;
    private ArrayList<Paquete> paquetes;

    public Envio() {
    }

    public Envio(Integer idEnvio, Integer idOrigen, Integer idDestino, Direccion origen, Direccion destino, Cliente cliente, Double costo, String fecha, String numGuia, List<Paquete> paquetes, Colaborador conductor, List<EstadoEnvio> estadoEnvios, String fechaEntrega, Integer idCliente) {
        this.idEnvio = idEnvio;
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.origen = origen;
        this.destino = destino;
        this.cliente = cliente;
        this.costo = costo;
        this.fecha = fecha;
        this.numGuia = numGuia;
        this.paquetes = paquetes;
        this.conductor = conductor;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Colaborador getConductor() {
        return conductor;
    }

    public void setConductor(Colaborador conductor) {
        this.conductor = conductor;
    }

    public List<EstadoEnvio> getEstadoEnvios() {
        return estadoEnvios;
    }

    public void setEstadoEnvios(List<EstadoEnvio> estadoEnvios) {
        this.estadoEnvios = estadoEnvios;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
     public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Envio{" + "idEnvio=" + idEnvio + ", idOrigen=" + idOrigen + ", idDestino=" + idDestino + ", origen=" + origen + ", destino=" + destino + ", cliente=" + cliente + ", conductor=" + conductor + ", costo=" + costo + ", fecha=" + fecha + ", numGuia=" + numGuia + ", paquetes=" + paquetes + ", estadoEnvios=" + estadoEnvios + ", fechaEntrega=" + fechaEntrega + ", idCliente=" + idCliente + '}';
    }
    
    

}
