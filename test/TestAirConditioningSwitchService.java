import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.Service;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.Repository;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.RepositoryFactory;
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
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class TestAirConditioningSwitchService extends JerseyTest{


    @Override
    protected Application configure()
    {
        return new ResourceConfig(Service.class);
    }

    @Test
    public void TestInsertValidStatus(){
        try{
            RepositoryFactory.clearTestRepository();
            RepositoryFactory.setTestRepositoryStatus(true);
            String id = "mamluqi";
            String status = "0";
            JsonWrapper w = new JsonWrapper(status);
            String url =  "/houses/"+id;
            Response response = target(url)
                    .request()
                    .post(Entity.entity(w, MediaType.APPLICATION_JSON_TYPE));
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            Repository testRep = RepositoryFactory.getRepository();
            assertEquals(status,testRep.retrieveSwitch(id).getStatus());
            }
        finally{
            RepositoryFactory.setTestRepositoryStatus(false);
        }
    }

    @Test
    public void TestInsertInvalidURL(){
        try{
            RepositoryFactory.clearTestRepository();
            RepositoryFactory.setTestRepositoryStatus(true);
            String id = "zurikie";
            String status = "0";
            JsonWrapper w = new JsonWrapper(status);
            String url =  "/housesInvalidURL/"+id;
            Response response = target(url)
                    .request()
                    .post(Entity.entity(w, MediaType.APPLICATION_JSON_TYPE));
            assertNotEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }
        finally{
            RepositoryFactory.setTestRepositoryStatus(false);
        }
    }

}
