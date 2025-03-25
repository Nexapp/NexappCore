package ca.nexapp.core.referables;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class UUIDReferenceNumberGenerator implements ReferenceNumberGenerator {

    @Override
    public ReferenceNumber generate() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return generate(ReferenceNumber.class);
    }

    @Override
    public <T extends ReferenceNumber> T generate(Class<T> klass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = klass.getConstructor(String.class);
        return constructor.newInstance(UUID.randomUUID().toString());
    }
}
