package ws;

import com.google.gson.Gson;
import dominio.ImpDireccion;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Direccion;
import pojo.Mensaje;

@Path("direccion")
public class WSDirecciones {

@GET
@Path("origen")
@Produces(MediaType.APPLICATION_JSON)
public Mensaje obtenerDireccionesOrigen() {
    Mensaje mensaje = new Mensaje();
    List<Direccion> direcciones = ImpDireccion.obtenerDireccionesOrigen();
    if (direcciones != null && !direcciones.isEmpty()) {
        for (Direccion direccion : direcciones) {
            System.out.println(direccion); 
        }
        mensaje.setError(false);
        mensaje.setMensaje("Direcciones de origen obtenidas exitosamente");
        Gson gson = new Gson();
        mensaje.setObjeto(gson.toJson(direcciones));
    } else {
        mensaje.setError(true);
        mensaje.setMensaje("No se encontraron direcciones de origen");
    }
    return mensaje;
}

}
