package hu.davidszabo.davefilesbackend.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.Map;

@Data
@AllArgsConstructor
public class SimpleMapEntry<Key, Value> implements Map.Entry<Key, Value> {
    @Setter(AccessLevel.NONE)
    Key key;
    @Setter(AccessLevel.NONE)
    Value value;

    @Override
    public Value setValue(Value value) {
        Value old = this.value;
        this.value = value;
        return old;
    }
}
