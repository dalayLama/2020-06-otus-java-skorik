package ru.otus.gc.bench;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class Benchmark implements BenchmarkMBean {

    private static List<Double> om = new ArrayList<>();

    private final int loopCounter;
    private volatile int size = 0;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            System.out.println("Loop: " + idx);
            int local = size;
            Double[] array = new Double[local];
            for (int i = 0; i < local; i++) {
                array[i] = Double.valueOf(veryDifficultCalculate(i));
            }
        }
    }

    void runWithOutOfMemory() throws InterruptedException {

        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            for (int i = 0; i < local; i++) {
                om.add(Math.random());
            }
            Thread.sleep(250);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }

    private Double veryDifficultCalculate(int index) {
        return BigDecimal
                .valueOf(index).multiply(BigDecimal.valueOf(869542.356))
                .add(BigDecimal.valueOf(983527.98324))
                .multiply(BigDecimal.valueOf(23427.654).add(BigDecimal.valueOf(index)))
                .divide(BigDecimal.valueOf(12347718.98342), 10, RoundingMode.CEILING)
                .setScale(10, RoundingMode.CEILING)
                .doubleValue();
    }

}
