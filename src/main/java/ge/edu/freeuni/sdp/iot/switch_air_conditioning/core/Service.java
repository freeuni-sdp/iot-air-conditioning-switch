package ge.edu.freeuni.sdp.iot.switch_air_conditioning.core;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.CloudStorage;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.SwitchEntity;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

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

        Client client = ClientBuilder.newClient();
        String url =  "https://iot-sim-house.herokuapp.com/webapi/bath/vent-switch/" + w.getStatus();

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