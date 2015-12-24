package ca.nexapp.core.application.assemblers;

import java.util.Collection;
import java.util.stream.Collectors;

public interface DomainAssemblable<M, V> {

    M assembleToDomain(V dto);

    default Collection<M> assembleToDomains(Collection<V> dtos) {
        return dtos.stream().map(this::assembleToDomain).collect(Collectors.toList());
    }
}
