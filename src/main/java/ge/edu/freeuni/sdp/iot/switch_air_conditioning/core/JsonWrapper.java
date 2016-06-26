package ge.edu.freeuni.sdp.iot.switch_air_conditioning.core;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class JsonWrapper {

    private String status;

    public JsonWrapper(){}
    public JsonWrapper(String status){
        setStatus(status);
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
