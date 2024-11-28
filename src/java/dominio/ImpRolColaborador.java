package dominio;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.RolColaborador;

public class ImpRolColaborador {

    public static Boolean agregarColaborador(RolColaborador rolColaborador) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("rolColaborador.agregar", rolColaborador);
                conexion.commit();
                if (res < 1) {
                    respuesta = false;
                }
            } catch (Exception ex) {
                respuesta = true;
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
                int res = conexion.selectOne("rolColaborador.obtenerUltimoID");
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

    public static Boolean actualizarRolColaborador(RolColaborador rolColaborador) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("rolColaborador.actualizar", rolColaborador);
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

    public static Boolean eliminarRolColaborador(Integer id) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.delete("rolColaborador.eliminar", id);
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
    
}
