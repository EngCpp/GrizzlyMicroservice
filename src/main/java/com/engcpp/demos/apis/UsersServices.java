/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.apis;

import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author engcpp
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersServices {    
    @GET    
    public Collection<User> findAll();
        
    @GET
    @Path("/{id}")
    public User findById(@PathParam("id")long id);
    
    @GET
    @Path("/findByEmailPassword")
    public User findByEmailPassword(@QueryParam("email")String email, 
                                    @QueryParam("password")String password);
        
    @DELETE
    @Path(value="/{id}")
    public boolean deleteJson(@PathParam("id")long id);
    
    @POST
    public boolean postJson(User content);
    
    @PUT
    @Path(value="/{id}")
    public boolean putJson(User content);
}
