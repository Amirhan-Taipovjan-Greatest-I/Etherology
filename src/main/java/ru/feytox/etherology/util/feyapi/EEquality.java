package ru.feytox.etherology.util.feyapi;

public interface EEquality {
    default boolean equalsAll(Object... objects) {
        for (Object o : objects) {
            if (!this.equals(o)) return false;
        }
        return true;
    }

    default boolean equalsAny(Object... objects) {
        for (Object o : objects) {
            if (this.equals(o)) return true;
        }
        return false;
    }
}