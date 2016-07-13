package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;

/**
 * Created by Giglema on 7/13/2016.
 */
public interface Repository {
    public void insertSwitch(String id, JsonWrapper w);
    public JsonWrapper retrieveSwitch(String id);
}
