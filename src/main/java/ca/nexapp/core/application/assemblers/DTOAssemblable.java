package ca.nexapp.core.application.assemblers;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public interface DTOAssemblable<M, V> {

    V assembleToDTO(M model);

    @SuppressWarnings("unchecked")
    default Stream<V> assembleToDTOs(M... models) {
        return assembleToDTOs(Arrays.asList(models));
    }

    default Stream<V> assembleToDTOs(Collection<M> models) {
        return models.stream().map(this::assembleToDTO);
    }
}
