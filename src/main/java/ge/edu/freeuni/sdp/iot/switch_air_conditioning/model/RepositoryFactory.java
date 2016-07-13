package ge.edu.freeuni.sdp.iot.switch_air_conditioning.model;

public class RepositoryFactory{

    private static boolean isTest = false;
    private static FakeRepository TestRepository;

    public static void clearTestRepository(){
        if (TestRepository!=null)
            TestRepository.clear();
    }

    public static Repository getRepository(){
        if (isTest) {
            if (TestRepository == null)
                TestRepository = new FakeRepository();
            return TestRepository;
        } else {
            return new CloudRepository();
        }
    }

    public static void setTestRepositoryStatus(boolean status) {
        isTest = status;
    }
}
