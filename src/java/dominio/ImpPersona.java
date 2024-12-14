package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Direccion;
import pojo.Mensaje;
import pojo.Persona;

public class ImpPersona {

    public static String agregarPersona(Persona persona) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("personas.agregar", persona);
                conexion.commit();
                if (res > 0) {
                    return "Guardado";
                } else {
                    return "No guardado";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "Hubo un problema";
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    return "Error";
                }
            }
        } else {
            return "No hay conexión";
        }
    }

    public static Integer obtenerUltimoID() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.selectOne("personas.obtenerUltimoID");
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

    public static Mensaje actualizarPersona(Persona persona) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("personas.actualizar", persona);
                conexion.commit();
                if (res > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Actualizado");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible actualizar la persona");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible actualizar la persona");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponibles, intentelo más tarde");
        }
        return mensaje;
    }

    public static Mensaje eliminarPersona(Integer id) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.delete("personas.eliminar", id);
                conexion.commit();
                if (res > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Eliminado");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible eliminar a la persona");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setError(true);
                mensaje.setMensaje("No es posible eliminar a la persona");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponibles, intentelo más tarde");
        }
        return mensaje;
    }

    public static Mensaje registrarFoto(int id, byte[] foto) {
        Mensaje mensaje = new Mensaje();
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<String, Object>();
        parametros.put("id", id);
        parametros.put("foto", foto);
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int filasAfectadas = conexion.update("personas.guardarFoto", parametros);
                conexion.commit();
                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Foto del cliente actualizada con exito");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("Foto del cliente no se pudo actualizar");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje(e.getMessage());
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se pudo procesar su petición, intentelo más tarde");
        }
        return mensaje;
    }

    public static Boolean validarRepetido(Persona persona) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        Map<String, String> map = new HashMap<>();
        String nombreCompleto = persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
        map.put("nombreCompleto", nombreCompleto);
        map.put("correo", persona.getCorreo());
        Boolean estaRepetido = false;
        if (conexion != null) {
            try {
                int res = conexion.selectOne("personas.validarRepetido", map);
                conexion.commit();
                if (res > 0) {
                    estaRepetido = true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                estaRepetido = false;
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    estaRepetido = false;
                }
            }
        } else {
            estaRepetido = false;
        }
        return estaRepetido;
    }

}
