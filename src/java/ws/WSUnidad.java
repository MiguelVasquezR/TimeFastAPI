/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpUnidad;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.Unidad;

/**
 * REST Web Service
 *
 * @author vasquez
 */
@Path("unidades")
public class WSUnidad {

    @Context
    private UriInfo context;

    public WSUnidad() {
    }

    @POST
    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarUnidad(String jsonUnidad) {
        Gson gson = new Gson();
        if (!jsonUnidad.isEmpty()) {
            Unidad unidad = gson.fromJson(jsonUnidad, Unidad.class);
            return ImpUnidad.agregarUnidad(unidad);
        }
        throw new BadRequestException();
    }

    @PUT
    @Path("actualizar-foto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarFoto(byte[] fotoBase64, @PathParam("id") Integer id) {
        if (fotoBase64 != null && id > 0) {
            return ImpUnidad.actualizarFoto(fotoBase64, id);
        }
        throw new BadRequestException();
    }

    @PUT
    @Path("actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarFoto(String jsonUnidad) {
        Gson gson = new Gson();
        if (jsonUnidad != null && !jsonUnidad.isEmpty()) {
            Unidad unidad = gson.fromJson(jsonUnidad, Unidad.class);
            return ImpUnidad.editarUnidad(unidad);
        }
        throw new BadRequestException();
    }

    @GET
    @Path("obtener-ultimo-id")
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizarFoto() {
        Gson gson = new Gson();
        return gson.toJson(ImpUnidad.obtenerUltimoID());
    }

    @GET
    @Path("obtener-unidades")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades() {
        return ImpUnidad.obtenerUnidades();
    }

    @DELETE
    @Path("eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerUnidades(@PathParam("id") Integer id) {
        System.out.println(id);
        return ImpUnidad.eliminarUnidad(id);
    }

    @PUT
    @Path("asociar-conductor/{idConductor}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje asociarConductor(@PathParam("idConductor") Integer idConductor, @PathParam("id") Integer id) {
        if ((idConductor != null && id != null) && idConductor > 0 && id > 0) {
            Mensaje res = ImpUnidad.conductorAsociado(idConductor);
            if (res.getError() == true) {
                return res;
            } else {
                return ImpUnidad.asociarConductor(idConductor, id);
            }
        }
        throw new BadRequestException();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of WSUnidad
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
