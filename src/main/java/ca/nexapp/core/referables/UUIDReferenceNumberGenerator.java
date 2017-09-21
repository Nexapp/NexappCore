package ca.nexapp.core.referables;

import java.util.UUID;

public class UUIDReferenceNumberGenerator implements ReferenceNumberGenerator {

    @Override
    public ReferenceNumber generate() {
        return new ReferenceNumber(UUID.randomUUID().toString());
    }
}
