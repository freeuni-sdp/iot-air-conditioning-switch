import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.PingService;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class TestPingService extends JerseyTest{

    @Override
    protected Application configure()
    {
        return new ResourceConfig(PingService.class);
    }


    @Test
    public void TestPingServiceOK(){
        Response res = target("ping").request().get();
        assertEquals(res.getStatus(),200);
    }

}