import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {

    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);

    private static final int MAX_VALUE = 10;

    private static final int START_VALUE = 0;

    private static final List<String> THREADS_ORDER = new ArrayList<>(Arrays.asList(
            "thread1", "thread2"
    ));

    private static final Map<String, Integer> THREADS_VALUES = new HashMap<>();

    private static final Map<String, Direction> THREADS_DIRECTIONS = new HashMap<>();

    private String expectedThread = THREADS_ORDER.get(0);

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.go();
    }

    public void go() {
        List<Thread> threads = THREADS_ORDER.stream()
                .map(name -> {
                    THREADS_VALUES.put(name, START_VALUE);
                    THREADS_DIRECTIONS.put(name, Direction.FORWARD);
                    return new Thread(this::task, name);
                })
                .collect(Collectors.toList());
        threads.forEach(Thread::start);
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }

    private static int getCurrentValue() {
        return THREADS_VALUES.get(Thread.currentThread().getName());
    }

    private static void setCurrentValue(int newValue) {
        THREADS_VALUES.put(Thread.currentThread().getName(), newValue);
    }

    private static int getNextValue(int fromValue) {
        int nextValue = getCurrentDirection() == Direction.FORWARD ? fromValue + 1 : fromValue - 1;
        if (nextValue == MAX_VALUE) {
            setCurrentDirection(Direction.BACK);
        }
        return nextValue;
    }

    private static Direction getCurrentDirection() {
        return THREADS_DIRECTIONS.get(Thread.currentThread().getName());
    }

    private static void setCurrentDirection(Direction direction) {
        THREADS_DIRECTIONS.put(Thread.currentThread().getName(), direction);
    }

    private synchronized void task() {
        int newValue = getNextValue(getCurrentValue());
        while (newValue != START_VALUE) {
            try {
                while (!isExpectedThread()) {
                    wait();
                }
                setCurrentValue(newValue);
                LOG.info(String.valueOf(newValue));
                newValue = getNextValue(getCurrentValue());
                iterateThread();
                sleep();
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isExpectedThread() {
        return Thread.currentThread().getName().equals(expectedThread);
    }

    private void iterateThread() {
        int index = THREADS_ORDER.indexOf(expectedThread);
        int nextIndex = index == THREADS_ORDER.size() - 1 ? 0 : index + 1;
        expectedThread = THREADS_ORDER.get(nextIndex);
    }

    private enum Direction {
        FORWARD, BACK
    }

}
