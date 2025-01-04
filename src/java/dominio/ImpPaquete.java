package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Direccion;
import pojo.Mensaje;
import pojo.Paquete;

public class ImpPaquete {

    public static Boolean agregarPaquete(Paquete paquete) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("paquetes.agregar", paquete);
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

    public static Boolean actualizarPaquete(Paquete paquete) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("paquetes.actualizar", paquete);
                conexion.commit();
                if (res > 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        } else {
            return true;
        }
    }

    public static Boolean eliminarPaquete(Integer id) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.delete("paquetes.eliminar", id);
                conexion.commit();
                if (res > 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        } else {
            return true;
        }
    }

    public static Paquete obtenerPaquetePorID(Integer id) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        Paquete paquete = null;
        if (conexion != null) {
            try {
                paquete = conexion.selectOne("paquetes.consultar-id", id);
                if (paquete == null) {
                    paquete = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paquete;
    }

    public static List<Paquete> obtenerPaqueteEnvio(Integer id) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                return conexion.selectList("paquetes.obtenerPaquetesEnvio", id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static List<Paquete> obtenerPaquetesPorEnvio(Integer idEnvio) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                // Llama a la consulta MyBatis con el parámetro idEnvio
                return conexion.selectList("paquetes.obtenerPaquetesPorEnvio", idEnvio);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexion.close();
            }
        }
        return null; // Devuelve null si no hay conexión o ocurre un error
    }


    public static List<Paquete> obtenerPaquetes() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                return conexion.selectList("paquetes.obtenerPaquetes");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
