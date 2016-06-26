package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

import com.microsoft.azure.storage.table.TableServiceEntity;
/**
 * Created by elene on 6/25/16.
 */
public class SwitchEntity extends TableServiceEntity{

    private String id;
    private String status;

    public final static String PARTITION = "GIGLEMA";

    public SwitchEntity(){}

    /* default status: off */
    public SwitchEntity(String id){
        this.rowKey = id;
        this.id = id;
        this.partitionKey = PARTITION;
        this.status = "0";
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status){
        if(check(status)) {
            this.status = status;
        }
    }

    public String getStatus(){
        return this.status;
    }

    private boolean check(String status){
        return (status.equals("0") || status.equals("#") || status.equals("*") ||  status.equals("**"));
    }




}
