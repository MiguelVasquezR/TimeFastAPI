package ws;

import com.google.gson.Gson;
import dominio.ImpDireccion;
import dominio.ImpEnvio;
import dominio.ImpEstadoEnvio;
import dominio.ImpPaquete;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import pojo.Paquete;
import java.time.LocalDate;
import java.sql.Date;

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
        if (envio != null) {

            if (ImpDireccion.agregarDireccion(envio.getDestino()).equals("Guardado")) {
                int idDestino = ImpDireccion.obtenerUltimoID();

                if (idDestino > 0) {

                    envio.setIdOrigen(envio.getCliente().getDireccion().getIdDireccion());
                    envio.setIdDestino(idDestino);
                    envio.setIdCliente(envio.getCliente().getId());
                    String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    envio.setFecha(fechaActual);

                    if (!ImpEnvio.agregarEnvio(envio)) {
                        mensaje.setError(false);
                        mensaje.setMensaje("Se ha agregar el envio correctamente");
                    } else {
                        mensaje.setError(true);
                        mensaje.setMensaje("No es posible agregar el envio");
                    }

                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible agregar el envio");
                }

            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible agregar el envio");
            }

        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No es posible agregar el envio");
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
            envio.setIdOrigen(envio.getCliente().getDireccion().getIdDireccion());
            envio.setIdCliente(envio.getCliente().getId());
                if (!ImpDireccion.actualizarDireccion(envio.getDestino()).getError()) {                    
                    if (!ImpEnvio.actualizarEnvio(envio)) {
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
            Gson gson = new Gson();
            List<Paquete> listaPaquetes = ImpPaquete.obtenerPaqueteEnvio(envio.getIdEnvio());
            envio.setPaquetes(listaPaquetes);
            List<EstadoEnvio> listaEstados = ImpEstadoEnvio.obtenerEstadosEnvio(envio.getIdEnvio());
            envio.setEstadoEnvios(listaEstados);
            mensaje.setError(false);
            mensaje.setMensaje("Envio encontrado");
            mensaje.setObjeto(gson.toJson(envio));
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
        Gson gson = new Gson();

        EstadoEnvio estadoEnvio = ImpEstadoEnvio.obtenerEstadoEnvio(idEnvio);
        if (estadoEnvio != null) {
            mensaje.setError(false);
            mensaje.setMensaje("Estado del envio encontrado");
            mensaje.setObjeto(gson.toJson(estadoEnvio));
            return mensaje;
        }
        throw new BadRequestException();
    }

    @GET
    @Path("detalles-envio/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> detallesEnvio(@PathParam("idEnvio") int idEnvio) {
        if (idEnvio > 0) {
            List<Envio> lista = ImpEstadoEnvio.obtenerDetallesEnvio(idEnvio);
            for (Envio envio : lista) {
                List<EstadoEnvio> estados = ImpEstadoEnvio.obtenerEstadosEnvio(envio.getIdEnvio());
                envio.setEstadoEnvios(estados);
            }
            return lista;
        }
        throw new BadRequestException();
    }

    @POST
    @Path("nuevo-estado")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje nuevoEstadoEnvio(String jsonEstado) {
        Gson gson = new Gson();
        if (jsonEstado != null && !jsonEstado.isEmpty()) {
            EstadoEnvio ee = gson.fromJson(jsonEstado, EstadoEnvio.class);
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatoSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaEnSQL = ahora.format(formatoSQL);
            ee.setFecha(fechaEnSQL);
            return ImpEstadoEnvio.insertarNuevoEstado(ee);
        }
        throw new BadRequestException();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path("todos-num-guia")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerTodosLosNumGuia() {
        Mensaje mensaje = new Mensaje();
        List<String> numerosGuia = ImpEnvio.obtenerTodosLosNumGuia();
        if (numerosGuia != null && !numerosGuia.isEmpty()) {
            mensaje.setError(false);
            mensaje.setMensaje("Números de guía obtenidos exitosamente");
            mensaje.setObjeto(numerosGuia);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se encontraron números de guía");
        }
        return mensaje;
    }

    @GET
    @Path("todos-envios")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerTodosLosEnvios() {
        Mensaje mensaje = new Mensaje();
        List<Envio> envios = ImpEnvio.obtenerTodosLosEnvios();
        if (envios != null && !envios.isEmpty()) {
            mensaje.setError(false);
            mensaje.setMensaje("Envíos obtenidos exitosamente");
            Gson gson = new Gson();
            mensaje.setObjeto(gson.toJson(envios));
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se encontraron envíos");
        }
        return mensaje;
    }

    @GET
    @Path("todos-id-envio")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerTodosLosIdEnvio() {
        Mensaje mensaje = new Mensaje();
        List<Integer> idsEnvio = ImpEnvio.obtenerTodosLosIdEnvio();
        if (idsEnvio != null && !idsEnvio.isEmpty()) {
            mensaje.setError(false);
            mensaje.setMensaje("IDs de envío obtenidos exitosamente");
            mensaje.setObjeto(idsEnvio);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No se encontraron IDs de envío");
        }
        return mensaje;
    }

    @PUT
    @Path("asignar-conductor/{idEnvio}/{idConductor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje asignarConductor(@PathParam("idEnvio") int idEnvio, @PathParam("idConductor") int idConductor) {
        Mensaje mensaje = new Mensaje();

        if (idEnvio <= 0 || idConductor <= 0) {
            mensaje.setError(true);
            mensaje.setMensaje("Parámetros inválidos. Verifique los IDs proporcionados.");
            return mensaje;
        }

        boolean resultado = ImpEnvio.asignarConductor(idEnvio, idConductor);
        if (resultado) {
            mensaje.setError(false);
            mensaje.setMensaje("Conductor asignado correctamente al envío.");
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No fue posible asignar el conductor al envío. Verifique los datos.");
        }

        return mensaje;
    }

    @POST
    @Path("agregar-con-cliente/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarEnvioConCliente(@PathParam("idCliente") int idCliente, String jsonEnvio) {
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
                        if (!ImpEnvio.agregarEnvioConCliente(envio, idCliente)) {
                            mensaje.setError(false);
                            mensaje.setMensaje("Se ha agregado el envío correctamente.");
                        } else {
                            mensaje.setError(true);
                            mensaje.setMensaje("No es posible agregar el envío.");
                        }
                    } else {
                        mensaje.setError(true);
                        mensaje.setMensaje("No se obtuvo el ID de destino.");
                    }
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible agregar la dirección de destino.");
                }
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("No se obtuvo el ID de origen.");
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No es posible agregar la dirección de origen.");
        }

        return mensaje;
    }

}
