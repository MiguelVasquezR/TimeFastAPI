package dominio;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Direccion;
import pojo.Mensaje;

public class ImpCliente {

    public static String agregarCliente(Cliente cliente) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.insert("clientes.agregar", cliente);
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

    public static List<Cliente> obtenerClientes() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        List<Cliente> clientes = new ArrayList<>();
        if (conexion != null) {
            try {
                clientes = conexion.selectList("clientes.obtenerClientes");
                return clientes;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static Integer obtenerUltimoID() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.selectOne("clientes.obtenerUltimoID");
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

    public static Mensaje actualizarCliente(Cliente cliente) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.update("clientes.actualizar", cliente);
                conexion.commit();
                if (res > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Actualizado");
                } else {
                    mensaje.setError(Boolean.TRUE);
                    mensaje.setMensaje("No es posible actualizar el cliente");
                }
            } catch (Exception e) {
                mensaje.setError(Boolean.TRUE);
                mensaje.setMensaje("No es posible actualizar la cliente");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponibles, intentelo más tarde");
        }
        return mensaje;
    }

    public static Mensaje eliminarCliente(Integer id) {
        Mensaje mensaje = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                int res = conexion.delete("clientes.eliminar", id);
                conexion.commit();
                if (res > 0) {
                    mensaje.setError(false);
                    mensaje.setMensaje("Eliminado");
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible eliminar al cliente");
                }
            } catch (Exception e) {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible eliminar al cliente");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Por el momento el servicio no está disponibles, intentelo más tarde");
        }
        return mensaje;
    }

    public static Boolean validarRepetido(Cliente cliente) {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        Boolean existeCliente = false;
        if (conexion != null) {
            try {
                int res = conexion.selectOne("clientes.validarRepetido", cliente.getTelefono());
                conexion.commit();
                if (res > 0) {
                    existeCliente = true;
                }
            } catch (Exception e) {
                existeCliente = false;
            }
        } else {
            existeCliente = false;
        }
        return existeCliente;
    }

    public static Mensaje obtenerClienteNombre(String nombreCompleto) {
        Mensaje msj = new Mensaje();
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        Boolean existeCliente = false;
        Gson gson = new Gson();
        if (conexion != null) {
            try {
                List<Integer> listaIdentificadores = conexion.selectList("clientes.buscarClienteNombre", nombreCompleto);
                conexion.commit();
                if (listaIdentificadores.size() == 0) {
                    msj.setError(true);
                    msj.setMensaje("No se ha encontrado cliente con ese nombre");
                } else if (listaIdentificadores.size() == 1) {
                    msj.setError(false);
                    msj.setMensaje("Se ha encontrado al cliente");
                    msj.setObjeto(gson.toJson(listaIdentificadores.get(0)));
                }else if(listaIdentificadores.size() >= 2){
                    msj.setError(true);
                    msj.setMensaje("Al parecer hay más clientes con ese nombre, intenta con el nombre completo.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("No se ha podido identificar al cliente");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("No se ha podido identificar al cliente");
        }
        return msj;
    }

}
