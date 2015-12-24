package ca.nexapp.core.context;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private static ServiceLocator instance;

    private final Map<Class<?>, Object> services = new HashMap<>();

    public static ServiceLocator locate() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public <T> void register(Class<T> serviceKey, T service) {
        if (services.containsKey(serviceKey)) {
            throw new IllegalArgumentException(serviceKey + " is already registered");
        }
        services.put(serviceKey, service);
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> serviceKey) {
        if (!services.containsKey(serviceKey)) {
            throw new IllegalStateException(serviceKey + " is not registered");
        }
        return (T) services.get(serviceKey);
    }

    private ServiceLocator() {
    }
}
