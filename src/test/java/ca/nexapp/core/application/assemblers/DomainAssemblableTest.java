package ca.nexapp.core.application.assemblers;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class DomainAssemblableTest {

    private DomainAssemblable<Integer, String> assembler;

    @Before
    public void setUp() {
        assembler = new MinimalDomainAssembler();
    }

    @Test
    public void givenADTO_WhenAssemblingToDomains_ShouldReturnTheAssembledModel() {
        Stream<Integer> models = assembler.assembleToDomains("45", "48", "59", "64");

        List<Integer> actual = models.collect(Collectors.toList());
        assertThat(actual).containsExactly(45, 48, 59, 64);
    }

    private class MinimalDomainAssembler implements DomainAssemblable<Integer, String> {

        @Override
        public Integer assembleToDomain(String dto) {
            return Integer.valueOf(dto);
        }

    }
}
