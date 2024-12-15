package dominio;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.EstadoEnvio;

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

}
