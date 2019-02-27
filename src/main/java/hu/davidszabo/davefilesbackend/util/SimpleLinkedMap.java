package hu.davidszabo.davefilesbackend.util;

import java.util.*;

public class SimpleLinkedMap<Key, Value> implements Map<Key, Value> {

    private Collection<Map.Entry<Key, Value>> entries;

    public SimpleLinkedMap() {
        entries = new LinkedList<>();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (Entry<Key, Value> i : entries)
            if (i.getKey().equals(key))
                return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<Key, Value> i : entries)
            if (i.getValue().equals(value))
                return true;
        return false;
    }

    @Override
    public Value get(Object key) {
        for (Entry<Key, Value> entry : entries)
            if (entry.getKey().equals(key))
                return entry.getValue();
        return null;
    }

    @Override
    public Value put(Key key, Value value) {
        Value old = get(key);
        entries.add(new SimpleMapEntry<>(key, value));
        return old;
    }

    @Override
    public Value remove(Object key) {
        Value old = get(key);
        if (old == null)
            return null;
        entries.forEach(entry -> {
            if (entry.getKey().equals(key))
                entries.remove(key);
        });
        return old;
    }

    @Override
    public void putAll(Map map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next instanceof Map.Entry)
                put((Key) ((Entry) next).getKey(), (Value) ((Entry) next).getValue());
        }
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public Set keySet() {
        Set<Key> keys = new LinkedHashSet<>();
        entries.forEach(key -> keys.add(key.getKey()));
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<Key, Value>> entrySet() {
        Set<Entry<Key, Value>> entrySet = new LinkedHashSet<>();
        entries.forEach(entry -> entrySet.add(entry));
        return entrySet;
    }
}
