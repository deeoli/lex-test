package org.lexisnexis.core;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static final ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(HashMap::new);

    public static <T> void set(String key, T value) {
        context.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) context.get().get(key);
    }

    public static void clear() {
        context.get().clear();
    }

    public static void remove() {
        context.remove();
    }
}
