package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

import utilidades.Utilidades;

public class ImpColaborador {

    public static Boolean agregarColaborador(Colaborador colaborador) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("colaboradores.agregar", colaborador);
                conexion.commit();
                if (res > 1) {
                    respuesta = false;
                }
            } catch (Exception ex) {
                respuesta = true;
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    return true;
                }
            }
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    public static Integer obtenerUltimoID() {

        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.selectOne("colaboradores.obtenerUltimoID");
                if (res > 0) {
                    return res;
                } else {
                    return 0;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }

    }

    public static Boolean actualizarColaborador(Colaborador colaborador) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("colaboradores.actualizarContrasena", colaborador);
                conexion.commit();
                if (res < 1) {
                    respuesta = true;
                }
            } catch (Exception e) {
                respuesta = true;
            }
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    public static Boolean eliminarColaborador(Integer id) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.delete("colaboradores.eliminar", id);
                conexion.commit();
                if (res < 1) {
                    respuesta = true;
                }
            } catch (Exception e) {
                respuesta = true;
            }
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    public static List<Colaborador> obtenerColaboradores() {
        List<Colaborador> colaboradores = new ArrayList<>();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                return colaboradores = conexion.selectList("colaboradores.obtenerColaboradores");
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Integer obtenerIdPersona(Integer idColaborador) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                return conexion.selectOne("obtenerIdPersona", idColaborador);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Colaborador obtenerDatosColaborador(String correo) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                return conexion.selectOne("recuperarContrasena", correo);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Mensaje recuperarContrasena(String nombre, Integer idColaborador, String correo) {
        String token = Utilidades.generarToken(idColaborador);
        Mensaje msj = new Mensaje();
        if (token != null || !token.isEmpty()) {
            if (Utilidades.enviarCorreo(correo, token, nombre)) {
                msj.setError(false);
                msj.setMensaje("Correo enviado");
            } else {
                msj.setError(true);
            }
        } else {
            msj.setError(true);
        }
        return msj;

    }

    public static Mensaje actualizarContrasena(String password, String token) {
        String id = Utilidades.obtenerIdColaborador(token);
        Mensaje msj = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("contrasena", password);
        parametros.put("idColaborador", Integer.parseInt(id));
        if (conexion != null) {
            try {
                int res = conexion.update("colaboradores.actualizarContrasena", parametros);
                conexion.commit();
                if (res > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se ha actualizado la contraseña");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Por el momento no se puede obtener la contraseña, intentelo más tarde.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("Por el momento no se puede obtener la contraseña, intentelo más tarde.");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no se puede obtener la contraseña, intentelo más tarde.");
        }

        return msj;

    }

}
