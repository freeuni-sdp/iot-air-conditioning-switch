package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;

public interface Repository {
    public void insertSwitch(String id, JsonWrapper w);
    public JsonWrapper retrieveSwitch(String id);
}
