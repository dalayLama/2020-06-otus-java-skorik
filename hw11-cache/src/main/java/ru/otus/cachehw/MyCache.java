package ru.otus.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    public static final String ACTION_ADD = "added";

    public static final String ACTION_REMOVE = "removed";

    private final Map<String, V> storage = new WeakHashMap<>();

    private final List<HwListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        String serializedKey = serializeKey(key);
        storage.put(serializedKey, value);
        listeners.forEach(l -> l.notify(key, value, ACTION_ADD));
    }

    @Override
    public void remove(K key) {
        String serializedKey = serializeKey(key);
        if (storage.containsKey(serializedKey)) {
            V value = storage.get(serializedKey);
            storage.remove(serializedKey);
            listeners.forEach(l -> l.notify(key, value, ACTION_REMOVE));
        }
    }

    @Override
    public V get(K key) {
        String serializedKey = serializeKey(key);
        return storage.get(serializedKey);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    public int size() {
        return storage.size();
    }

    protected String serializeKey(K key) {
        return String.valueOf(key);
    }

}
