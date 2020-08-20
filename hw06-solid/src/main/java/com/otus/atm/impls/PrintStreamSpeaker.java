package com.otus.atm.impls;

import com.otus.atm.Speaker;

import java.io.PrintStream;

public class PrintStreamSpeaker implements Speaker {

    private final PrintStream os;

    public PrintStreamSpeaker(PrintStream os) {
        this.os = os;
    }

    @Override
    public void sayMessage(String message) {
        os.println(message);
    }
}
