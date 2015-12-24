package ca.nexapp.core.application.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ca.nexapp.core.application.assemblers.DTOAssemblable;

public class DTOAssemblableTest {

    private static final Integer A_MODEL = 2;

    private DTOAssemblable<Integer, String> assembler;

    @Before
    public void setUp() {
        assembler = spy(new MinimalDTOAssembler());
    }

    @Test
    public void givenThreeModels_WhenAssemblingToDTOs_ShouldAssembleThreeTimes() {
        assembler.assembleToDTOs(Arrays.asList(A_MODEL, A_MODEL, A_MODEL));

        verify(assembler, times(3)).assembleToDTO(A_MODEL);
    }

    @Test
    public void givenAModel_WhenAssemblingToDTOs_ShouldReturnTheAssembledDTO() {
        Collection<String> models = assembler.assembleToDTOs(Arrays.asList(A_MODEL));

        assertThat(models).containsExactly("2");
    }

    private class MinimalDTOAssembler implements DTOAssemblable<Integer, String> {

        @Override
        public String assembleToDTO(Integer model) {
            return String.valueOf(model);
        }

    }
}
