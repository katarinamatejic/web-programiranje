package com.domaci.vebdomaci5.resources;

import com.domaci.vebdomaci5.entities.Clanak;
import com.domaci.vebdomaci5.service.ClanakService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class ClanakResource {
    @Inject
    private ClanakService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){ return Response.ok(this.service.all()).build(); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Clanak add(Clanak clanak){ return this.service.add(clanak); }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Clanak find(@PathParam("id") Integer id){ return this.service.find(id); }

    @POST
    @Path("/comment/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Clanak add(String komentar, @PathParam("postId") Integer postId){ return this.service.addComm(komentar, postId); }
}
