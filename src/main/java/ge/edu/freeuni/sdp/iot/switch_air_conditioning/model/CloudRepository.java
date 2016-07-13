package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.Repository;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class CloudRepository implements Repository {

    @Override
    public void insertSwitch(String id, JsonWrapper w) {
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
    }

    @Override
    public JsonWrapper retrieveSwitch(String id) {
        CloudStorage cloud = new CloudStorage();
        SwitchEntity sw = cloud.retrieveSwitch(id);
        JsonWrapper wr = new JsonWrapper(sw.getStatus());
        return wr;
    }
}
