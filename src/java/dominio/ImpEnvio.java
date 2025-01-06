package dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Envio;

public class ImpEnvio {
    
    public static Boolean agregarEnvio(Envio envio) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        envio.setFechaEntrega(obtenerFechaEntrega());
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
    
    public static Boolean actualizarEnvio(Envio envio) {
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return envio;
    }
    
    private static String obtenerFechaEntrega() {
        LocalDateTime ahora = LocalDateTime.now();
        int hora = ahora.getHour();
        LocalDateTime nuevaFecha;
        if (hora >= 16) {
            nuevaFecha = ahora.plusDays(4);
        } else {
            nuevaFecha = ahora.plusDays(3);
        }
        java.sql.Date fechaSQL = java.sql.Date.valueOf(nuevaFecha.toLocalDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return nuevaFecha.format(formatter);
    }
    
    
    public static List<String> obtenerTodosLosNumGuia() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        List<String> numerosGuia = new ArrayList<>();
        if (conexion != null) {
            try {
                numerosGuia = conexion.selectList("envios.obtenerTodosNumGuia");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return numerosGuia;
    }
    
    public static List<Envio> obtenerTodosLosEnvios() {
       SqlSession conexion = MyBatisUtil.obtenerConexion();
       List<Envio> envios = new ArrayList<>();
       if (conexion != null) {
           try {
               envios = conexion.selectList("envios.obtenerTodos");
           } catch (Exception ex) {
               ex.printStackTrace();
           } finally {
               try {
                   conexion.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
       return envios;
   }

    public static List<Integer> obtenerTodosLosIdEnvio() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        List<Integer> idsEnvio = new ArrayList<>();
        if (conexion != null) {
            try {
                idsEnvio = conexion.selectList("envios.obtenerTodosIdEnvio");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return idsEnvio;
    }
    
    
public static boolean asignarConductor(int idEnvio, int idConductor) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                // Crear un mapa para pasar los parámetros a MyBatis
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idEnvio", idEnvio);
                parametros.put("idConductor", idConductor);

                int resultado = conexion.update("envios.asignarConductor", parametros);
                conexion.commit();
                return resultado > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                conexion.close();
            }
        } else {
            return false;
        }
    }

public static Boolean agregarEnvioConCliente(Envio envio, int idCliente) {
    SqlSession conexion = MyBatisUtil.obtenerConexion();
    envio.setFechaEntrega(obtenerFechaEntrega());
    envio.setCliente(new Cliente());
    envio.getCliente().setId(idCliente); // Asigna el idCliente al envío
    if (conexion != null) {
        try {
            int res = conexion.insert("envios.agregarConCliente", envio);
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

      
}
