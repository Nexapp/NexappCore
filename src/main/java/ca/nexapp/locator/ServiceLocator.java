package ca.nexapp.locator;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private static ServiceLocator instance;

    public static final void load(ServiceLocator serviceLocator) {
        instance = serviceLocator;
    }

    private final Map<Class<?>, Object> registeredServices = new HashMap<>();

    public final void register(Class<?> interfaceClass, Object implementation) {
        registeredServices.put(interfaceClass, implementation);
    }

    public static final Object find(Class<?> interfaceClass) {
        if ( !instance.registeredServices.containsKey(interfaceClass) ) {
            throw new IllegalArgumentException(interfaceClass + " is not registered.");
        }
        return instance.registeredServices.get(interfaceClass);
    }

}
