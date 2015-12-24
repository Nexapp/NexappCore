package ca.nexapp.core.application.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ca.nexapp.core.application.assemblers.DomainAssemblable;

public class DomainAssemblableTest {

    private static final String A_DTO = "2";

    private DomainAssemblable<Integer, String> assembler;

    @Before
    public void setUp() {
        assembler = spy(new MinimalDomainAssembler());
    }

    @Test
    public void givenThreeDTOs_WhenAssemblingToModels_ShouldAssembleThreeTimes() {
        assembler.assembleToDomains(Arrays.asList(A_DTO, A_DTO, A_DTO));

        verify(assembler, times(3)).assembleToDomain(A_DTO);
    }

    @Test
    public void givenADTO_WhenAssemblingToModels_ShouldReturnTheAssembledModel() {
        Collection<Integer> models = assembler.assembleToDomains(Arrays.asList(A_DTO));

        assertThat(models).containsExactly(2);
    }

    private class MinimalDomainAssembler implements DomainAssemblable<Integer, String> {

        @Override
        public Integer assembleToDomain(String dto) {
            return Integer.valueOf(dto);
        }

    }
}
