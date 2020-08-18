package ru.otus.gc.bench;

public class TestOutOfMemory {

    public static void main(String[] args) throws InterruptedException {
        int size = 5 * 1000 * 1000;
        int loopCounter = 1000;
        Benchmark mbean = new Benchmark(loopCounter);
        mbean.setSize(size);
        mbean.runWithOutOfMemory();
    }

}
