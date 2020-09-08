package ru.otus;

import com.google.gson.Gson;

public class Demo {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String json = gson.toJson(1);
        System.out.println(json);
    }

}
