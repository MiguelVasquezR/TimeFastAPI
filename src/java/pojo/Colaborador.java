package pojo;

public class Colaborador {

    private Integer idColaborador;
    private Integer idPersona;
    private String noPersonal;
    private Persona persona;
    private String contrasena;
    private RolColaborador rol;

    public Colaborador() {
    }

    public Colaborador(Integer idColaborador, String noPersonal, Persona persona, String contrasena, RolColaborador rol, Integer idPersona) {
        this.idColaborador = idColaborador;
        this.noPersonal = noPersonal;
        this.persona = persona;
        this.contrasena = contrasena;
        this.rol = rol;
        this.idPersona = idPersona;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public RolColaborador getRol() {
        return rol;
    }

    public void setRol(RolColaborador rol) {
        this.rol = rol;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Colaborador{" + "idColaborador=" + idColaborador + ", noPersonal=" + noPersonal + ", persona=" + persona + ", contrasena=" + contrasena + ", rol=" + rol + '}';
    }

}
