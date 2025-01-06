/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpLogin;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;

/**
 * REST Web Service
 *
 * @author vasquez
 */
@Path("login")
public class WSLogin {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSLogin
     */
    public WSLogin() {
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String holaMundo() {
        return "Hola mundo";
    }

    @POST
    @Path("login-colaborador-conductor")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje validarCredencialesColaboradorConductor(@FormParam("noPersonal") Integer noPersonal,
            @FormParam("password") String password) {
        if ((noPersonal != null && password != null) && noPersonal > 0 && !password.isEmpty()) {
            return ImpLogin.validarCredencialesLogin(noPersonal, password, true);
        }
        throw new BadRequestException();
    }
    
    @POST
    @Path("login-colaborador")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje validarCredencialesColaborador(@FormParam("noPersonal") Integer noPersonal,
            @FormParam("password") String password) {
        if ((noPersonal != null && password != null) && noPersonal > 0 && !password.isEmpty()) {
            return ImpLogin.validarCredencialesLogin(noPersonal, password, false);
        }
        throw new BadRequestException();
    }

    /**
     * Retrieves representation of an instance of ws.WSLogin
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of WSLogin
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
