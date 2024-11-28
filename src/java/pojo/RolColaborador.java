package pojo;

public class RolColaborador {

    private Integer idRolColaborador;
    private String rol;
    private String numLicencia;
    private Integer idColaborador;

    public RolColaborador() {
    }

    public RolColaborador(Integer idRolColaborador, String rol, String numLicencia, Integer idColaborador) {
        this.idRolColaborador = idRolColaborador;
        this.rol = rol;
        this.numLicencia = numLicencia;
        this.idColaborador = idColaborador;
    }

    public Integer getIdRolColaborador() {
        return idRolColaborador;
    }

    public void setIdRolColaborador(Integer idRolColaborador) {
        this.idRolColaborador = idRolColaborador;
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
        return "RolColaborador{" + "id=" + idRolColaborador + ", rol=" + rol + ", numLicencia=" + numLicencia + '}';
    }

}
