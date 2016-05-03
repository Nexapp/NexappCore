package ca.nexapp.core.application.assemblers;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public interface DomainAssemblable<M, V> {

    M assembleToDomain(V dto);

    @SuppressWarnings("unchecked")
    default Stream<M> assembleToDomains(V... dtos) {
        return assembleToDomains(Arrays.asList(dtos));
    }

    default Stream<M> assembleToDomains(Collection<V> dtos) {
        return dtos.stream().map(this::assembleToDomain);
    }
}
