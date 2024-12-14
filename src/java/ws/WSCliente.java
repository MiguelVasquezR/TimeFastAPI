package ws;

import com.google.gson.Gson;
import dominio.ImpCliente;
import dominio.ImpDireccion;
import dominio.ImpPersona;
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
import pojo.Cliente;
import pojo.Mensaje;

@Path("cliente")
public class WSCliente {

    @Context
    private UriInfo context;

    public WSCliente() {
    }

    @POST
    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarCliente(String jsonCliente) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
        Mensaje mensaje = new Mensaje();
        
        if(ImpPersona.validarRepetido(cliente.getPersona()) || ImpCliente.validarRepetido(cliente)){
            mensaje.setError(true);
            mensaje.setMensaje("El cliente que deseas agregar a nuestro sistema, ya está registrado.");
            return mensaje;
        }

        if (ImpDireccion.agregarDireccion(cliente.getDireccion()).equals("Guardado")) {
            int idDireccion = ImpDireccion.obtenerUltimoID();
            if (idDireccion > 0) {
                cliente.setIdDireccion(idDireccion);
                if (ImpPersona.agregarPersona(cliente.getPersona()).equals("Guardado")) {
                    int idPersona = ImpPersona.obtenerUltimoID();
                    if (idPersona > 0) {
                        cliente.setIdPersona(idPersona);
                        if (ImpCliente.agregarCliente(cliente).equals("Guardado")) {
                            mensaje.setMensaje("Cliente almacenado correctamente");
                            mensaje.setError(Boolean.FALSE);
                            mensaje.setObjeto(cliente);
                        } else {
                            mensaje.setMensaje("Por el momento, no es posible almacenar el cliente");
                            mensaje.setError(true);
                        }
                    } else {
                        mensaje.setMensaje("Por el momento, no es posible almacenar el cliente");
                        mensaje.setError(true);
                    }
                }
            } else {
                mensaje.setMensaje("Por el momento, no es posible almacenar el cliente");
                mensaje.setError(true);
            }
        } else {
            mensaje.setMensaje("Por el momento, no es posible almacenar el cliente");
            mensaje.setError(true);
        }

        return mensaje;

    }

    @GET
    @Path("obtener-clientes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerClientes() {
        return ImpCliente.obtenerClientes();
    }

    @PUT
    @Path("actualizar-cliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarCliente(String jsonCliente) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
        Mensaje respuesta = new Mensaje();
        Mensaje msjDireccion = ImpDireccion.actualizarDireccion(cliente.getDireccion());
        if (!msjDireccion.getError()) {
            Mensaje msjPersona = ImpPersona.actualizarPersona(cliente.getPersona());
            if (!msjPersona.getError()) {
                Mensaje msjCliente = ImpCliente.actualizarCliente(cliente);
                if (!msjCliente.getError()) {
                    respuesta = msjCliente;
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No es posible actualizar el cliente");
                }
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("No es posible actualizar la persona");
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No es posible actualizar la direccion");
        }

        return respuesta;
    }

    @DELETE
    @Path("eliminar-cliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(String jsonCliente) {
        Mensaje mensaje = new Mensaje();
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);

        boolean clienteEliminado = ImpCliente.eliminarCliente(cliente.getId()).getError();
        if (clienteEliminado) {
            return crearMensajeError("El cliente no ha sido eliminado, intentelo más tarde");
        }

        boolean personaEliminada = ImpPersona.eliminarPersona(cliente.getIdPersona()).getError();
        if (personaEliminada) {
            return crearMensajeError("El cliente no ha sido eliminado, intentelo más tarde (Persona)");
        }

        boolean direccionEliminada = ImpDireccion.eliminarDireccion(cliente.getIdDireccion()).getError();
        if (direccionEliminada) {
            return crearMensajeError("El cliente no ha sido eliminado, intentelo más tarde (Dirección)");
        }

        mensaje.setError(false);
        mensaje.setMensaje("Cliente eliminado");
        return mensaje;
    }

    @PUT
    @Path("actualizar-foto/{idPersona}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idPersona") Integer idPersona,
            byte[] foto) {
        if (idPersona != null && idPersona > 0 && foto != null) {
            return ImpPersona.registrarFoto(idPersona, foto);
        }
        throw new BadRequestException();
    }

    private Mensaje crearMensajeError(String mensajeError) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        mensaje.setMensaje(mensajeError);
        return mensaje;
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

}
