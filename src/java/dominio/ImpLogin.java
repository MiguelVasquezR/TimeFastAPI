package dominio;

import java.util.HashMap;
import java.util.Map;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

public class ImpLogin {
    
    public static Mensaje validarCredencialesLogin(Integer noPersonal, String password) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        Map<String, Object> map = new HashMap<>();
        map.put("noPersonal", noPersonal);
        map.put("password", password);
        if (conexionBD != null) {
            try {
                Colaborador colaborador = conexionBD.selectOne("login.loginColaborador", map);
                if (colaborador != null) {
                    msj.setError(false);
                    msj.setMensaje("Colaborador Autenticado. Hola " + colaborador.getPersona().getNombre());
                    msj.setObjeto(colaborador);
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
