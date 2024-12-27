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
