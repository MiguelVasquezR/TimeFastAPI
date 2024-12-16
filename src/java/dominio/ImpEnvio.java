package dominio;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;

public class ImpEnvio {

    public static Boolean agregarEnvio(Envio envio) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("envios.agregar", envio);
                conexion.commit();
                if (res > 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return true;
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    public static Boolean actualizarenvio(Envio envio) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("envios.actualizar", envio);
                conexion.commit();
                if (res > 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return true;
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    public static Envio consultarEnvioNumGuia(String numGuia) {
        Envio envio = null;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                envio = conexion.selectOne("envios.consultarPorNumGuia", numGuia);
                System.out.println(envio.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return envio;
    }

}
