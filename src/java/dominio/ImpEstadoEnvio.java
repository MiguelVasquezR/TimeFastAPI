package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoEnvio;
import pojo.Mensaje;

public class ImpEstadoEnvio {

    public static boolean actualizarEstado(EstadoEnvio estadoEnvio) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("estadoEnvio.actualizarEstado", estadoEnvio);
                conexion.commit();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static EstadoEnvio obtenerEstadoEnvio(Integer idEnvio) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                return conexion.selectOne("estadoEnvio.obtenerEstadoEnvio", idEnvio);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static Mensaje insertarNuevoEstado(EstadoEnvio ee) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int res = conexionBD.insert("estadoEnvio.nuevoEstado", ee);
                conexionBD.commit();
                if (res > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se ha actualizado el estado del envío");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se ha podido actualizar el envío, intentelo más tarde.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("No se ha podido actualizar el envío, intentelo más tarde.");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("No se ha podido actualizar el envío, intentelo más tarde.");
        }

        return msj;
    }

    public static List<EstadoEnvio> obtenerEstadosEnvio(Integer id) {
        List<EstadoEnvio> lista = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                lista = conexionBD.selectList("estadoEnvio.obtenerEstadosEnvios", id);
            } catch (Exception e) {
                e.printStackTrace();
                lista = null;
            }
        }
        return lista;
    }

    public static List<Envio> obtenerDetallesEnvio(Integer id) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                return conexionBD.selectList("estadoEnvio.detallesEnvioConductor", id);
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("No se han encontrado envios para usted");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("No se han encontrado envios para usted");
        }

        return null;
    }
    
    public static List<Envio> obtenerTodosDetallesEnvio(Integer id) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                return conexionBD.selectList("estadoEnvio.obtenerTodosPaquetesEnvio", id);
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("No se han encontrado envios para usted");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("No se han encontrado envios para usted");
        }

        return null;
    }
    
    

}
