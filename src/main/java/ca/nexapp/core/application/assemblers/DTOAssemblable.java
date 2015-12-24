package ca.nexapp.core.application.assemblers;

import java.util.Collection;
import java.util.stream.Collectors;

public interface DTOAssemblable<M, V> {

    V assembleToDTO(M model);

    default Collection<V> assembleToDTOs(Collection<M> models) {
        return models.stream().map(this::assembleToDTO).collect(Collectors.toList());
    }
}
