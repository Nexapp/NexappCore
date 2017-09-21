package ca.nexapp.core.referables;

public interface Referable {

    default boolean hasReference(ReferenceNumber reference) {
        return getReference().equals(reference);
    }

    default boolean hasSameReference(Referable other) {
        return hasReference(other.getReference());
    }

    ReferenceNumber getReference();
}
