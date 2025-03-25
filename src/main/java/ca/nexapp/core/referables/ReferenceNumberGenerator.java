package ca.nexapp.core.referables;

import java.lang.reflect.InvocationTargetException;

public interface ReferenceNumberGenerator {

    ReferenceNumber generate() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    <T extends ReferenceNumber> T generate(Class<T> klass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
