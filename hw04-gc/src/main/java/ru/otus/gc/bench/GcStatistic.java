package ru.otus.gc.bench;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GcStatistic {

    private int quantityCalls = 0;

    private long sumDuration = 0;

    private boolean stopCounting = false;

    public int getQuantityCalls() {
        return quantityCalls;
    }

    public long getSumDuration() {
        return sumDuration;
    }

    public boolean isStopCounting() {
        return stopCounting;
    }

    public void setStopCounting(boolean stopCounting) {
        this.stopCounting = stopCounting;
    }

    public void addDuration(long duration) {
        if (isStopCounting()) {
            return;
        }
        quantityCalls++;
        sumDuration += duration;
    }

    public double avgDuration() {
        return quantityCalls != 0 ?
                BigDecimal.valueOf(sumDuration)
                        .divide(BigDecimal.valueOf(quantityCalls), 4, RoundingMode.CEILING)
                        .setScale(4, RoundingMode.CEILING)
                        .doubleValue() :
                0;
    }
}
