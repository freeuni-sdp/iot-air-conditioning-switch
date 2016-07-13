package ge.edu.freeuni.sdp.iot.switch_air_conditioning.core;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.CloudStorage;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.Repository;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.SwitchEntity;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Service {

    @POST
    @Path("/houses/{house_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertSwitch(@PathParam("house_id") String id, JsonWrapper w){
        Repository rep = RepositoryFactory.getRepository();
        rep.insertSwitch(id,w);
        return Response.ok().build();
    }

    @GET
    @Path("/houses/{house_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonWrapper retrieveSwitch(@PathParam("house_id") String id){
        Repository rep = RepositoryFactory.getRepository();
        return rep.retrieveSwitch(id);
    }

}