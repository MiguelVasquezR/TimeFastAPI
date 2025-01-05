package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

public class ImpColaborador {

    public static Boolean agregarColaborador(Colaborador colaborador) {
        Boolean respuesta = false;
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("colaboradores.agregar", colaborador);
                conexion.commit();
                if (res < 1) {
                    respuesta = false;
                }
            } catch (Exception ex) {
                respuesta = true;
            }finally{
                try{
                    conexion.close();
                }catch(Exception e){
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
    
    public static List<Colaborador> obtenerConductores() {
    List<Colaborador> conductores = new ArrayList<>();
    SqlSession conexion = MyBatisUtil.obtenerConexion();
    if (conexion != null) {
        try {
            conductores = conexion.selectList("colaboradores.obtenerConductores");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    return conductores;
}


}
