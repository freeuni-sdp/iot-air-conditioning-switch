package ge.edu.freeuni.sdp.iot.switch_air_conditioning.core;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.CloudStorage;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.SwitchEntity;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class Service {

    @POST
    @Path("/houses/{house_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertSwitch(@PathParam("house_id") String id, JsonWrapper w){
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
    public JsonWrapper retrieveSwitch(@PathParam("house_id") String id){
        System.out.println("GEEET PLZZZ");
        CloudStorage cloud = new CloudStorage();
        System.out.println("STOcre");
        SwitchEntity sw = cloud.retrieveSwitch(id);
        JsonWrapper wr = new JsonWrapper(sw.getStatus());
        System.out.println("DAMPALIMATXOVARi");
        return wr;
    }

}