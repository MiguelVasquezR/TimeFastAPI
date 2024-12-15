package pojo;

public class RolColaborador {

    private Integer id;
    private String rol;
    private String numLicencia;
    private Integer idColaborador;

    public RolColaborador() {
    }

    public RolColaborador(Integer idRolColaborador, String rol, String numLicencia, Integer idColaborador) {
        this.id = idRolColaborador;
        this.rol = rol;
        this.numLicencia = numLicencia;
        this.idColaborador = idColaborador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    @Override
    public String toString() {
        return "RolColaborador{" + "id=" + id + ", rol=" + rol + ", numLicencia=" + numLicencia + '}';
    }

}
