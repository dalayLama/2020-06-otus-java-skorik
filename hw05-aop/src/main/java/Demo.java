public class Demo {

    public static void main(String[] args) {
        TestLogging testLogging = TestLoggingCreator.createTestLogging();
        testLogging.calculation(1);
        testLogging.calculation(2, 5);
        testLogging.calculation(5, 1, "hi");
    }

}
