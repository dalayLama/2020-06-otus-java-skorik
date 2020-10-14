package ru.otus.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    public static final String ACTION_ADD = "added";

    public static final String ACTION_REMOVE = "removed";

    private final WeakHashMap<K, V> storage = new WeakHashMap<>();

    private final List<HwListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        storage.put(key, value);
        listeners.forEach(l -> l.notify(key, value, ACTION_ADD));
    }

    @Override
    public void remove(K key) {
        if (storage.containsKey(key)) {
            V value = storage.get(key);
            storage.remove(key);
            listeners.forEach(l -> l.notify(key, value, ACTION_REMOVE));
        }
    }

    @Override
    public V get(K key) {
        return storage.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }
}
