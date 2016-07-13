package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

/**
 * Created by Giglema on 7/13/2016.
 */
public class RepositoryFactory{

    private static boolean isTest = false;
    public static Repository getRepository(){
        if (isTest) {
            return new FakeRepository();
        } else {
            return new CloudRepository();
        }
    }

    public static void setTestRepositoryStatus(boolean status) {
        isTest = status;
    }
}
