package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

import ge.edu.freeuni.sdp.iot.switch_air_conditioning.core.JsonWrapper;

import java.util.HashMap;

/**
 * Created by Giglema on 7/13/2016.
 */
public class FakeRepository implements Repository{
    private HashMap<String, String> houseConditions;

    public FakeRepository(){
        houseConditions = new HashMap<>();
    }

    @Override
    public void insertSwitch(String id, JsonWrapper w) {
        houseConditions.put(id,w.getStatus());
    }

    @Override
    public JsonWrapper retrieveSwitch(String id) {
        return new JsonWrapper(houseConditions.get(id));
    }
}
