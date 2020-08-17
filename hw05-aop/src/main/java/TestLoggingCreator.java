import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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

        private final Set<MethodInfo> loggingMethods = new HashSet<>();

        MyInvocationHandler(TestLoggingImpl implLogging) {
            this.implLogging = implLogging;
            scanMethods();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            MethodInfo methodInfo = new MethodInfo(method);
            if (loggingMethods.contains(methodInfo)) {
                String params = Stream.of(args).map(String::valueOf).collect(Collectors.joining(", "));
                String message = String.format("executed method: %s, params: %s", method.getName(), params);
                System.out.println(message);
            }
            return method.invoke(implLogging, args);
        }

        private void scanMethods() {
            Stream.of(implLogging.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .forEach(m -> loggingMethods.add(new MethodInfo(m)));
        }

        static class MethodInfo {

            private final String name;

            private final Class<?>[] params;

            public MethodInfo(Method method) {
                this.name = method.getName();
                this.params = method.getParameterTypes();
            }

            public String getName() {
                return name;
            }

            public Class<?>[] getParams() {
                return params;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof MethodInfo)) return false;
                MethodInfo that = (MethodInfo) o;
                return Objects.equals(getName(), that.getName()) &&
                        Arrays.equals(getParams(), that.getParams());
            }

            @Override
            public int hashCode() {
                int result = Objects.hash(getName());
                result = 31 * result + Arrays.hashCode(getParams());
                return result;
            }
        }

    }

}
