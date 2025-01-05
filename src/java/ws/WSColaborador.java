package ws;

import com.google.gson.Gson;
import dominio.ImpColaborador;
import dominio.ImpPersona;
import dominio.ImpRolColaborador;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.Colaborador;
import pojo.Mensaje;
import pojo.Persona;

@Path("colaborador")
public class WSColaborador {

    @Context
    private UriInfo context;

    public WSColaborador() {
    }

    @POST
    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarColaborador(String jsonColaborador) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);

        boolean respuestaPersona = ImpPersona.agregarPersona(colaborador.getPersona()).equals("Guardado");

        if (!respuestaPersona) {
            mensaje.setError(true);
            mensaje.setMensaje("No es posible agregar a la persona");
            return mensaje;
        }

        int idPersona = ImpPersona.obtenerUltimoID();
        if (idPersona > 0) {
            colaborador.setIdPersona(idPersona);
            boolean respuestaColaborador = ImpColaborador.agregarColaborador(colaborador);
            if (respuestaColaborador) {
                mensaje.setError(true);
                mensaje.setMensaje("No es posible agregar al colaborador");
            } else {
                int idColaborador = ImpColaborador.obtenerUltimoID();
                colaborador.getRol().setIdColaborador(idColaborador);
                boolean respuestaRolColaborador = ImpRolColaborador.agregarColaborador(colaborador.getRol());

                if (respuestaColaborador) {
                    mensaje.setError(true);
                    mensaje.setMensaje("No es posible agregar el rol de colaborador");
                } else {
                    mensaje.setError(false);
                    mensaje.setMensaje("Se ha agregado el colaborador");
                }
            }
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("No es posible encontrar a la persona");
        }

        return mensaje;
    }

    @GET
    @Path("obtener-colaboradores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores() {
        return ImpColaborador.obtenerColaboradores();
    }

    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String jsonColaborador) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
        if (colaborador.getIdColaborador() > 0 && colaborador.getPersona().getIdPersona() > 0 && colaborador.getRol().getIdRolColaborador() > 0) {
            Boolean respuestaPersona = ImpPersona.actualizarPersona(colaborador.getPersona()).getError();
            if (!respuestaPersona) {
                Boolean respuestaColaborador = ImpColaborador.actualizarColaborador(colaborador);
                if (!respuestaColaborador) {
                    Boolean respuestaRolColab = ImpRolColaborador.actualizarRolColaborador(colaborador.getRol());
                    if (!respuestaRolColab) {
                        mensaje.setError(false);
                        mensaje.setMensaje("El colaborador ha sido actualizado");
                    } else {
                        mensaje.setError(true);
                        mensaje.setMensaje("No se ha completado su petición no se ha podido actualizar");
                    }
                } else {
                    mensaje.setError(true);
                    mensaje.setMensaje("El colaborador no se ha podido actualizar");
                }
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("La personal no se ha podido actualizar");
            }
            return mensaje;
        }
        throw new BadRequestException();
    }

    @DELETE
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(String idsColaborador) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(idsColaborador, Colaborador.class);
        if (colaborador.getIdColaborador() > 0 && colaborador.getIdPersona() > 0 && colaborador.getRol().getIdRolColaborador() > 0) {
            Boolean respuestaRolColab = ImpRolColaborador.eliminarRolColaborador(colaborador.getRol().getIdRolColaborador());
            if (!respuestaRolColab) {
                Boolean respuestaColab = ImpColaborador.eliminarColaborador(colaborador.getIdColaborador());
                if (!respuestaColab) {
                    Boolean respuestaPersona = ImpPersona.eliminarPersona(colaborador.getIdPersona()).getError();
                    if (!respuestaPersona) {
                        mensaje.setError(false);
                        mensaje.setMensaje("El colaborador ha sido eliminado");
                    } else {
                        mensaje.setError(true);
                        mensaje.setMensaje("El colaborador no ha sido eliminado");
                    }
                    return mensaje;
                }
            }
        }
        throw new BadRequestException();
    }

    @PUT
    @Path("actualizar-foto/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idColaborador") Integer idColaborador,
            byte[] foto) {        
        if (idColaborador != null && idColaborador > 0 && foto != null) {
            return ImpPersona.registrarFoto(idColaborador, foto);
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
    @Path("obtener-conductores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerConductores() {
        return ImpColaborador.obtenerConductores();
    }


}
