package ca.nexapp.application.assemblers;

import static com.google.common.truth.Truth.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class AssemblerTest {

    private static final Collection<Integer> MODELS = Arrays.asList(1, 2, 3, 4, 5);
    private static final Collection<String> VIEW_MODELS = Arrays.asList("1", "2", "3", "4", "5");

    private static final Integer[] MODELS_AS_ARRAY = { 1, 2, 3, 4, 5 };
    private static final String[] VIEW_MODELS_AS_ARRAY = { "1", "2", "3", "4", "5" };

    private Assembler<Integer, String> assembler;

    @Before
    public void setUp() {
        assembler = buildAnAssembler();
    }

    @Test
    public void givenACollectionOfModelsWhenConvertingToViewModelsShouldReturnTheCorrespondingCollectionOfViewModels() {
        Collection<String> actualViewModels = assembler.toViewModels(MODELS);

        assertThat(actualViewModels).containsAllIn(VIEW_MODELS);
    }

    @Test
    public void givenACollectionOfViewModelsWhenConvertingToModelsShouldReturnTheCorrespondingCollectionOfModels() {
        Collection<Integer> actualModels = assembler.toModels(VIEW_MODELS);

        assertThat(actualModels).containsAllIn(MODELS);
    }

    @Test
    public void givenACollectionOfModelsWhenConvertingToAnArrayOfViewModelsShouldReturnTheCorrespondingArrayOfViewModels() {
        String[] actualViewModels = new String[MODELS.size()];
        actualViewModels = assembler.toArrayOfViewModels(actualViewModels, MODELS);
        assertThat(actualViewModels).isEqualTo(VIEW_MODELS_AS_ARRAY);
    }

    @Test
    public void givenACollectionOfViewModelsWhenConvertingToAnArrayOfModelsShouldReturnTheCorrespondingArrayOfModels() {
        Integer[] actualModels = new Integer[VIEW_MODELS.size()];
        actualModels = assembler.toArrayOfModels(actualModels, VIEW_MODELS);
        assertThat(actualModels).isEqualTo(MODELS_AS_ARRAY);
    }

    private Assembler<Integer, String> buildAnAssembler() {
        return new Assembler<Integer, String>() {

            @Override
            public String toViewModel(Integer model) {
                return String.valueOf(model);
            }

            @Override
            public Integer toModel(String viewModel) {
                return Integer.parseInt(viewModel);
            }
        };
    }
}

