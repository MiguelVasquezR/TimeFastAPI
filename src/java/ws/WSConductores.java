/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpLogin;
import pojo.LoginColaborador;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mario
 */


@Path("conductores")
public class WSConductores {

    @Path("iniciarSesion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public LoginColaborador iniciarSesionConductor(@FormParam("noPersonal") String noPersonal, @FormParam("password") String password) {
        if ((noPersonal != null && !noPersonal.isEmpty()) && (password != null && !password.isEmpty())) {
            return ImpLogin.validarSesionConductor(noPersonal,password);
        }
        throw new BadRequestException("Los parámetros no pueden estar vacíos.");
    }
}