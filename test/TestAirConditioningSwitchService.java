import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.Service;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.Repository;
import ge.edu.freeuni.sdp.iot.switch_air_conditioning.model.RepositoryFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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



    @Test
    public void TestGetValidStatus(){
        try{
            RepositoryFactory.clearTestRepository();
            RepositoryFactory.setTestRepositoryStatus(true);
            Repository repository = RepositoryFactory.getRepository();
            String id = "validID";
            String status = "*";
            repository.insertSwitch(id,new JsonWrapper(status));
            String url =  "/houses/"+id;
            JsonWrapper result = target(url)
                    .request()
                    .get(JsonWrapper.class);
            //assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            assertEquals(repository.retrieveSwitch(id).getStatus(),result.getStatus());
        }
        finally{
            RepositoryFactory.setTestRepositoryStatus(false);
        }
    }






}
