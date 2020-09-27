package ru.otus;

import com.google.gson.Gson;
import ru.otus.factories.FactoryMyGson;

public class DemoGson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        MyGson myGson = new FactoryMyGson().createMyGson();
        String json = myGson.toJson(new AnyObject(22, "test", 33));
        System.out.println(json);
        AnyObject anyObject = gson.fromJson(json, AnyObject.class);
    }

}
