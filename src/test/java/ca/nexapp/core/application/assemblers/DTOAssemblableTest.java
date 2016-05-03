package ca.nexapp.core.application.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.spy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class DTOAssemblableTest {

    private DTOAssemblable<Integer, String> assembler;

    @Before
    public void setUp() {
        assembler = spy(new MinimalDTOAssembler());
    }

    @Test
    public void givenAModel_WhenAssemblingToDTOs_ShouldReturnTheAssembledDTO() {
        Stream<String> dtos = assembler.assembleToDTOs(1, 4, 7, 30, 75);

        List<String> actual = dtos.collect(Collectors.toList());
        assertThat(actual).containsExactly("1", "4", "7", "30", "75");
    }

    private class MinimalDTOAssembler implements DTOAssemblable<Integer, String> {

        @Override
        public String assembleToDTO(Integer model) {
            return String.valueOf(model);
        }

    }
}
