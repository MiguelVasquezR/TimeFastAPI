package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Direccion;
import pojo.Mensaje;

public class ImpDireccion {

    public static String agregarDireccion(Direccion direccion) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("direccion.agregar", direccion);
                conexion.commit();
                if (res > 0) {
                    return "Guardado";
                } else {
                    return "No guardado";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "Hubo un problema";
            }
        } else {
            return "No hay conexión";
        }
    }

    public static Integer obtenerUltimoID() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.selectOne("direccion.obtenerUltimoID");
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

    public static Mensaje actualizarDireccion(Direccion direccion) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("direccion.actualizar", direccion);
                conexion.commit();
                if (res > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Actualizado");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible actualizar la dirección");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible actualizar la dirección");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponibles, intentelo más tarde");
        }
        return mensaje;
    }
    
     public static Mensaje eliminarDireccion(Integer id) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.delete("direccion.eliminar", id);
                conexion.commit();
                if (res > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Eliminado");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible eliminar a la direccion");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible eliminar a la direccion");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponibles, intentelo más tarde");
        }
        return mensaje;
    }

}
