package ca.nexapp.core.functionalities.assemblers;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import ca.nexapp.core.application.assemblers.DTOAssemblable;
import ca.nexapp.core.application.assemblers.DomainAssemblable;

public class AssemblerITest {

    private DummyAssembler assembler;

    @Before
    public void a() {
        assembler = new DummyAssembler();
    }

    @Test
    public void canAssembleDomains() {
        List<Integer> domains = assembler.assembleToDomains("1", "2", "3").collect(Collectors.toList());

        assertThat(domains).containsExactly(1, 2, 3);
    }

    @Test
    public void canAssembleDTOs() {
        List<String> dtos = assembler.assembleToDTOs(1, 2, 3, 4, 5).collect(Collectors.toList());

        assertThat(dtos).containsExactly("1", "2", "3", "4", "5");
    }

    private class DummyAssembler implements DomainAssemblable<Integer, String>, DTOAssemblable<Integer, String> {

        @Override
        public String assembleToDTO(Integer model) {
            return String.valueOf(model);
        }

        @Override
        public Integer assembleToDomain(String dto) {
            return Integer.valueOf(dto);
        }

    }
}
