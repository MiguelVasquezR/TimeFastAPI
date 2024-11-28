package pojo;

public class Cliente {

    private Integer id;
    private Integer idPersona;
    private Integer idDireccion;
    private String telefono;
    private Persona persona;
    private Direccion direccion;

    public Cliente() {
    }

    public Cliente(Integer id, Integer idPersona, Integer idDireccion, Persona persona, String telefono, Direccion direccion) {
        this.id = id;
        this.idPersona = idPersona;
        this.idDireccion = idDireccion;
        this.persona = persona;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", idPersona=" + idPersona + ", idDireccion=" + idDireccion + ", telefono=" + telefono + ", persona=" + persona + ", direccion=" + direccion + '}';
    }
    
    

}
