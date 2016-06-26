package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;
/**
 * Created by elene on 6/26/16.
 */

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;

public class CloudStorage {

    private CloudTable cloud;
    private static final String tableName;
    private static final String storageConnectionString;

    static {
        tableName = "witches";
        storageConnectionString =
                "DefaultEndpointsProtocol=http;" +
                        "AccountName=uto;" +
                        "AccountKey=CpYMHzjbYSqJZli6EA75W3anJBCQBN2V4IZFKGn38MsCX8SDX+v/hV2vfk9mk5FeF6X1KInEaT5SRUMgeeuqBw==";
    }


    public CloudStorage(){
        this.cloud = getConnection();
    }


    public void insertOrReplaceSwitch(SwitchEntity sw){
        try
        {
            // Create an operation to add the new customer to the people table.
            TableOperation insertSwitch = TableOperation.insertOrReplace(sw);

            // Submit the operation to the table service.
            this.cloud.execute(insertSwitch);
        }
        catch (Exception e)        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }


    public void deleteSwitch(String houseId){
        try {
            TableOperation retrieveSwitch =
                    TableOperation.retrieve(SwitchEntity.PARTITION, houseId, SwitchEntity.class);
            SwitchEntity sw =
                    this.cloud.execute(retrieveSwitch).getResultAsType();
            TableOperation deleteSwitch = TableOperation.delete(sw);

            // Submit the delete operation to the table service.
            this.cloud.execute(deleteSwitch);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public SwitchEntity retrieveSwitch(String houseId){
        SwitchEntity res = null;
        try {
            TableOperation retrieveSwitch =
                    TableOperation.retrieve(SwitchEntity.PARTITION, houseId, SwitchEntity.class);
            res =
                    this.cloud.execute(retrieveSwitch).getResultAsType();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }


    /*returns cloud table */
    private CloudTable getConnection(){
        try
        {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);

            // Create the table client.
            CloudTableClient tableClient = storageAccount.createCloudTableClient();

            // Create the table if it doesn't exist.

            CloudTable cloudTable = tableClient.getTableReference(tableName);
            cloudTable.createIfNotExists();
            return cloudTable;
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
            return null;
        }
    }

}
