import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.Service;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Created by Giglema on 7/13/2016.
 */
public class TestInsertSwitch extends JerseyTest{


    @Override
    protected Application configure()
    {
        return new ResourceConfig(Service.class);
    }

    @Test
    public void TestInsert(){
        JsonWrapper jw = new JsonWrapper("*");
        String id = "99341";
        String url = "https://iot-air-conditioning-switch.herokuapp.com/webapi/houses/"+id;
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        Client client = ClientBuilder.newClient(config);
        client.target(url)
                .request()
                .post(Entity.entity(jw, MediaType.APPLICATION_JSON_TYPE));



    }

}
