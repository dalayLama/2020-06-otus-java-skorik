package ru.otus.cachehw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwListener;

import java.util.*;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);

    private final Map<String, V> storage = new WeakHashMap<>();

    private final List<HwListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        String serializedKey = serializeKey(key);
        storage.put(serializedKey, value);
        notify(key, value, Action.ADDED);
    }

    @Override
    public void remove(K key) {
        String serializedKey = serializeKey(key);
        V value = storage.remove(serializedKey);
        if (Objects.nonNull(value)) {
            notify(key, value, Action.REMOVED);
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

    protected void notify(K key, V value, Action action) {
        try {
            listeners.forEach(l -> l.notify(key, value, action));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
