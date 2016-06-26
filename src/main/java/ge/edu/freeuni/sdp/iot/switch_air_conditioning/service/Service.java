package ge.edu.freeuni.sdp.iot.switch_air_conditioning.service;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.CloudStorage;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.SwitchEntity;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

public class Service {

    @POST
    @Path("/houses/{house_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertSwitch(@PathParam("id") String id, JsonWrapper w){
        System.out.println("got to post");
        SwitchEntity sw =  new SwitchEntity(id);
        System.out.println("created switch entry");
        sw.setStatus(w.getStatus());
        CloudStorage cloud = new CloudStorage();
        cloud.insertOrReplaceSwitch(sw);
        System.out.println("inserted entry");
        return Response.ok().build();
    }

    @GET
    @Path("/houses/{house_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonWrapper retrieveSwitch(@PathParam("id") String id){
        CloudStorage cloud = new CloudStorage();
        SwitchEntity sw = cloud.retrieveSwitch(id);
        JsonWrapper wr = new JsonWrapper(sw.getStatus());
        return wr;
    }

}