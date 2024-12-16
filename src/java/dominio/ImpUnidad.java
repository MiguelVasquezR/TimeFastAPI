package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Unidad;
import mybatis.MyBatisUtil;

public class ImpUnidad {

    public static Mensaje agregarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int res = conexionBD.insert("unidades.agregar", unidad);
                conexionBD.commit();
                if (res > 0) {
                    msj.setError(false);
                    msj.setMensaje("Unidad agregada correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Por el momento no se puede almacenar la unidad, intentelo más tarde");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("Por el momento no se puede almacenar la unidad, intentelo más tarde");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no se puede almacenar la unidad, intentelo más tarde.");
        }
        return msj;
    }

    public static Mensaje actualizarFoto(byte[] foto, Integer id) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<String, Object>();
        parametros.put("id", id);
        parametros.put("foto", foto);
        if (conexionBD != null) {
            try {
                int res = conexionBD.update("unidades.actualizarFoto", parametros);
                conexionBD.commit();
                if (res > 0) {
                    msj.setError(false);
                    msj.setMensaje("Foto de la Unidad actualizado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Por el momento no se puede actualizar la foto de la unidad, intentelo más tarde");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Por el momento no se puede actualizar la foto de la unidad, intentelo más tarde");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no se puede actualizar la foto de la unidad, intentelo más tarde");
        }
        return msj;
    }

    public static Integer obtenerUltimoID() {
        SqlSession conexion = MyBatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                Integer res = conexion.selectOne("unidades.obtenerUltimoID");
                conexion.commit();
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

    public static List<Unidad> obtenerUnidades() {
        List<Unidad> unidades = new ArrayList<Unidad>();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                unidades = conexionBD.selectList("unidades.obtenerUnidades");
                conexionBD.commit();
                System.out.println(unidades.get(0).getFoto());
            } catch (Exception e) {
                e.printStackTrace();
                unidades = null;
            }
        } else {
            unidades = null;
        }
        return unidades;
    }

    public static Mensaje eliminarUnidad(Integer id) {
        Mensaje msj = new Mensaje();
        SqlSession conextionBD = MyBatisUtil.obtenerConexion();
        if (conextionBD != null) {
            try {
                int res = conextionBD.delete("unidades.eliminar", id);
                conextionBD.commit();
                if (res > 0) {
                    msj.setError(false);
                } else {
                    msj = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj = null;
            }
        } else {
            msj = null;
        }
        return msj;
    }

    public static Mensaje editarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        SqlSession conextionBD = MyBatisUtil.obtenerConexion();
        if (conextionBD != null) {
            try {
                int res = conextionBD.delete("unidades.editar", unidad);
                conextionBD.commit();
                if (res > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se ha actualizado correctamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Por el momento no es posible actualizar la unidad");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("Por el momento no es posible actualizar la unidad");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no es posible actualizar la unidad");
        }
        return msj;
    }

    public static Mensaje conductorAsociado(Integer idConductor) {
        Mensaje msj = new Mensaje();
        SqlSession conextionBD = MyBatisUtil.obtenerConexion();
        if (conextionBD != null) {
            try {
                int res = conextionBD.selectOne("unidades.conductorAsociado", idConductor);
                conextionBD.commit();
                if (res > 0) {
                    msj.setError(true);
                    msj.setMensaje("El conductor ya está asociado a una unidad");
                } else {
                    msj.setError(false);
                    msj.setMensaje("El conductor no está asociado");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(false);
                msj.setMensaje("El conductor no está asociado");
            }
        } else {
            msj.setError(false);
            msj.setMensaje("El conductor no está asociado");
        }
        return msj;
    }

    public static Mensaje asociarConductor(Integer idConductor, Integer id) {
        Mensaje msj = new Mensaje();
        SqlSession conextionBD = MyBatisUtil.obtenerConexion();
        Map<String, Integer> parametros = new HashMap<>();
        parametros.put("idConductor", idConductor);
        parametros.put("id", id);
        if (conextionBD != null) {
            try {
                int res = conextionBD.update("unidades.asociarConductor", parametros);
                conextionBD.commit();
                if (res > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se ha asociado el conductor a la unidad exitosamente");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Por el momento su petición no se puede cumplir, intentelo más tarde.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("Por el momento su petición no se puede cumplir, intentelo más tarde.");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no está disponible, intentelo más tarde.");
        }
        return msj;
    }

}
