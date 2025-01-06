/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import pojo.LoginColaborador;
import pojo.Colaborador;


/**
 *
 * @author mario
 */

public class ImpLogin {

    public static LoginColaborador validarSesionConductor(String noPersonal, String password) {
    LoginColaborador respuesta = new LoginColaborador();
    SqlSession conexionBD = MyBatisUtil.obtenerConexion();
    if (conexionBD != null) {
        try {
            HashMap<String, String> parametros = new LinkedHashMap<>();
            parametros.put("noPersonal", noPersonal);
            parametros.put("password", password);

            Colaborador colaborador = conexionBD.selectOne("conductores.loginConductor", parametros);
            if (colaborador != null) {
                if (colaborador.getRol() != null && "Conductor".equals(colaborador.getRol().getRol())) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Inicio de sesión exitoso para: " + colaborador.getPersona().getNombre() + " " + colaborador.getPersona().getApellidoPaterno());
                    respuesta.setColaborador(colaborador);
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("El usuario no tiene el rol de Conductor.");
                }
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Número de personal y/o contraseña incorrectos.");
            }
        } catch (Exception e) {
            respuesta.setError(true);
            respuesta.setMensaje("Error al procesar la solicitud: " + e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        respuesta.setError(true);
        respuesta.setMensaje("No se pudo conectar con la base de datos.");
    }
    return respuesta;
}

}
package dominio;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

public class ImpLogin {
    
    public static Mensaje validarCredencialesLogin(Integer noPersonal, String password, Boolean esConductor) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        Map<String, Object> map = new HashMap<>();
        map.put("noPersonal", noPersonal);
        map.put("password", password);
        Gson gson = new Gson();
        if (conexionBD != null) {
            try {
                Colaborador colaborador = null;
                if(esConductor){
                    colaborador = conexionBD.selectOne("login.loginColaboradorConductor", map);
                }else{
                    colaborador = conexionBD.selectOne("login.loginColaborador", map);
                }
                if (colaborador != null) {
                    msj.setError(false);
                    msj.setMensaje("Colaborador Autenticado. Hola " + colaborador.getPersona().getNombre());
                    msj.setObjeto(gson.toJson(colaborador));
                } else {
                    msj.setError(true);
                    msj.setMensaje("Sus credenciales son incorrectas, intente de nuevo.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setError(true);
                msj.setMensaje("Por el momento no podemos validar tus credenciales, intentelo más tarde");
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no podemos validar tus credenciales, intentelo más tarde");
        }
        return msj;
    }
    
}
