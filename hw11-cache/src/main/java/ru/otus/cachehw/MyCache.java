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

    private final WeakHashMap<K, V> storage = new WeakHashMap<>();

    private final List<HwListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        storage.put(key, value);
    }

    @Override
    public void remove(K key) {
        storage.remove(key);
    }

    @Override
    public V get(K key) {
        return storage.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        this.listeners.remove(listener);
    }
}
