package ca.nexapp.core.referables;

import java.util.Objects;

public class ReferenceNumber {

    private final String value;

    public ReferenceNumber(String value) {
        this.value = value;
    }

    public String describe() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ReferenceNumber)) {
            return false;
        }

        ReferenceNumber referenceNumber = (ReferenceNumber) obj;
        return Objects.equals(value, referenceNumber.value);
    }

    @Override
    public String toString() {
        return describe();
    }
}
