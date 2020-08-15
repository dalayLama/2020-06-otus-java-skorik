import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestLoggingCreator {

    public static TestLogging createTestLogging() {
        MyInvocationHandler handler = new MyInvocationHandler(new TestLoggingImpl());
        Object proxy = Proxy.newProxyInstance(TestLoggingCreator.class.getClassLoader(), new Class<?>[]{TestLogging.class}, handler);
        return (TestLogging) proxy;
    }

    private TestLoggingCreator() {}

    static class MyInvocationHandler implements InvocationHandler {

        private final TestLoggingImpl implLogging;

        MyInvocationHandler(TestLoggingImpl implLogging) {
            this.implLogging = implLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method implMethod = implLogging.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (implMethod.isAnnotationPresent(Log.class)) {
                String params = Stream.of(args).map(String::valueOf).collect(Collectors.joining(", "));
                String message = String.format("executed method: %s, params: %s", method.getName(), params);
                System.out.println(message);
            }
            return method.invoke(implLogging, args);
        }

    }

}
