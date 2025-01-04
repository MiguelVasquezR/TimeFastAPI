package ws;

import com.google.gson.Gson;
import dominio.ImpPaquete;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.Paquete;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author vasquez
 */
@Path("paquetes")
public class WSPaquete {

    @Context
    private UriInfo context;

    public WSPaquete() {
    }

    @POST
    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarPaquete(String jsonPaquete) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        if (jsonPaquete.isEmpty() || jsonPaquete != null) {
            Paquete paquete = gson.fromJson(jsonPaquete, Paquete.class);
            if (!ImpPaquete.agregarPaquete(paquete)) {
                mensaje.setError(false);
                mensaje.setMensaje("Se ha agregado el paquete correctamente");
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible agregar el paquete por el momento, intente más tarde");
            }
            return mensaje;
        }
        throw new BadRequestException();
    }

    @PUT
    @Path("actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarPaquete(String jsonPaquete) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        if (jsonPaquete.isEmpty() || jsonPaquete != null) {
            Paquete paquete = gson.fromJson(jsonPaquete, Paquete.class);
            if (!ImpPaquete.actualizarPaquete(paquete)) {
                mensaje.setError(false);
                mensaje.setMensaje("Se ha actualizado el paquete correctamente");
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible actualizar el paquete por el momento, intente más tarde");
            }
            return mensaje;
        }
        throw new BadRequestException();
    }

    @DELETE
    @Path("eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("id") Integer id) {
        Mensaje mensaje = new Mensaje();
        if (id > 0) {
            if (!ImpPaquete.eliminarPaquete(id)) {
                mensaje.setError(false);
                mensaje.setMensaje("Se ha eliminado el paquete");
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible eliminar el paquete, intentelo más tarde");
            }
            return mensaje;
        }
        throw new BadRequestException();
    }

    @GET
    @Path("consultar-paquete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerPaqueteID(@PathParam("id") Integer id) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        if (id > 0) {
            Paquete respuesta = ImpPaquete.obtenerPaquetePorID(id);
            if (respuesta != null) {
                mensaje.setObjeto(gson.toJson(respuesta));
                mensaje.setError(false);
                mensaje.setMensaje("Paquete obtenido");
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible obtener el paquete, intentelo más tarde");
            }
            return mensaje;
        }
        throw new BadRequestException();
    }
    
    @GET
    @Path("obtener-paquetes-por-envio/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerPaquetesPorEnvio(@PathParam("idEnvio") Integer idEnvio) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        if (idEnvio != null && idEnvio > 0) {
            List<Paquete> paquetes = ImpPaquete.obtenerPaquetesPorEnvio(idEnvio);
            if (paquetes != null && !paquetes.isEmpty()) {
                mensaje.setObjeto(gson.toJson(paquetes));
                mensaje.setError(false);
                mensaje.setMensaje("Paquetes obtenidos correctamente");
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No se encontraron paquetes para el envío con ID: " + idEnvio);
            }
            return mensaje;
        }
        throw new BadRequestException("El ID del envío debe ser mayor a 0");
    }


    @GET
    @Path("obtener-paquetes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes() {
        return ImpPaquete.obtenerPaquetes();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    

    
}
