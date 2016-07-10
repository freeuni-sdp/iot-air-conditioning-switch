package ge.edu.freeuni.sdp.iot.switch_air_conditioning.core;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.CloudStorage;
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
        SwitchEntity sw =  new SwitchEntity(id);
        sw.setStatus(w.getStatus());
        CloudStorage cloud = new CloudStorage();
        cloud.insertOrReplaceSwitch(sw);

        String url =  "https://iot-sim-house.herokuapp.com/webapi/conditioner/"+id;

        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        Client client = ClientBuilder.newClient(config);
        client.target(url)
                .request()
                .post(Entity.entity(w, MediaType.APPLICATION_JSON_TYPE));

        return Response.ok().build();
    }

    @GET
    @Path("/houses/{house_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonWrapper retrieveSwitch(@PathParam("house_id") String id){
        CloudStorage cloud = new CloudStorage();
        SwitchEntity sw = cloud.retrieveSwitch(id);
        JsonWrapper wr = new JsonWrapper(sw.getStatus());
        return wr;
    }

}