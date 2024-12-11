package ws;

import com.google.gson.Gson;
import dominio.ImpDireccion;
import dominio.ImpEnvio;
import dominio.ImpEstadoEnvio;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.Envio;
import pojo.EstadoEnvio;
import pojo.Mensaje;

@Path("envios")
public class WSEnvios {
    
    @Context
    private UriInfo context;
    
    public WSEnvios() {
    }
    
    @POST
    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarEnvio(String jsonEnvio) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        Envio envio = gson.fromJson(jsonEnvio, Envio.class);
        if (ImpDireccion.agregarDireccion(envio.getOrigen()).equals("Guardado")) {
            int idOrigen = ImpDireccion.obtenerUltimoID();
            if (idOrigen > 0) {
                envio.setIdOrigen(idOrigen);
                if (ImpDireccion.agregarDireccion(envio.getDestino()).equals("Guardado")) {
                    int idDestino = ImpDireccion.obtenerUltimoID();
                    envio.setIdDestino(idDestino);
                    if (idDestino > 0) {
                        if (!ImpEnvio.agregarEnvio(envio)) {
                            mensaje.setError(false);
                            mensaje.setMensaje("Se ha agregar el envio correctamente");
                        } else {
                            mensaje.setError(true);
                            mensaje.setMensaje("No es posible agregar el envio");
                        }
                    } else {
                        mensaje.setError(true);
                        mensaje.setMensaje("No se obtuvo el ID");
                    }
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible agregar la dirección de origen");
                }
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No se obtuvo el ID");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No es posible agregar la dirección de origen");
        }
        
        return mensaje;
    }
    
    @PUT
    @Path("actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEnvio(String json) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        if (!json.isEmpty() || json != null) {
            Envio envio = gson.fromJson(json, Envio.class);
            if (ImpDireccion.actualizarDireccion(envio.getOrigen()).getError()) {
                if (ImpDireccion.actualizarDireccion(envio.getDestino()).getError()) {
                    if (ImpEnvio.actualizarenvio(envio)) {
                        mensaje.setError(false);
                        mensaje.setMensaje("Se ha actualizado en envio");
                    } else {
                        mensaje.setError(true);
                        mensaje.setMensaje("No es posible actualizar el envio");
                    }
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible actualizar la dirección");
                }
                
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible actualizar la dirección");
            }
            
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Debe ingresar información valida");
        }
        return mensaje;
    }
    
    @GET
    @Path("consultar/{numGuia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje consultarEnvioNumGuia(@PathParam("numGuia") String numGuia) {
        Mensaje mensaje = new Mensaje();
        Envio envio = ImpEnvio.consultarEnvioNumGuia(numGuia);
        
        if (envio != null) {
            mensaje.setError(false);
            mensaje.setMensaje("Envio encontrado");
            mensaje.setObjeto(envio);
            return mensaje;
        }
        
        throw new BadRequestException();
    }
    
    @PUT
    @Path("actualizar-estado-envio")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEstadoEnvio(String jsonEstado) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        if (!jsonEstado.equals("") || jsonEstado != null) {
            EstadoEnvio estadoEnvio = gson.fromJson(jsonEstado, EstadoEnvio.class);
            if (ImpEstadoEnvio.actualizarEstado(estadoEnvio)) {
                mensaje.setError(false);
                mensaje.setMensaje("Se ha actualizado el estado");
                return mensaje;
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("Por el momento no es posible actualizar el estado, intentelo más tarde");
                return mensaje;
            }
        }
        throw new BadRequestException();
    }
    
    @GET
    @Path("consultar-estado/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerEstadoEnvio(@PathParam("idEnvio") int idEnvio) {
        Mensaje mensaje = new Mensaje();
        
        if(idEnvio <= 0){
            throw new BadRequestException();
        }
        
        EstadoEnvio estadoEnvio = ImpEstadoEnvio.obtenerEstadoEnvio(idEnvio);
        if (estadoEnvio != null) {
            mensaje.setError(false);
            mensaje.setMensaje("Estado del envio encontrado");
            mensaje.setObjeto(estadoEnvio);
            return mensaje;
        }
        throw new BadRequestException();
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
