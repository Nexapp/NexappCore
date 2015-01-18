package ca.nexapp.application.assemblers;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
        Collection<String> viewModelsConverted = assembler.toViewModels(MODELS);
        assertEquals(VIEW_MODELS, viewModelsConverted);
    }

    @Test
    public void givenACollectionOfViewModelsWhenConvertingToModelsShouldReturnTheCorrespondingCollectionOfModels() {
        Collection<Integer> modelsConverted = assembler.toModels(VIEW_MODELS);
        assertEquals(MODELS, modelsConverted);
    }

    @Test
    public void givenACollectionOfModelsWhenConvertingToAnArrayOfViewModelsShouldReturnTheCorrespondingArrayOfViewModels() {
        String[] viewModelsConverted = new String[MODELS.size()];
        viewModelsConverted = assembler.toArrayOfViewModels(viewModelsConverted, MODELS);
        assertArrayEquals(VIEW_MODELS_AS_ARRAY, viewModelsConverted);
    }

    @Test
    public void givenACollectionOfViewModelsWhenConvertingToAnArrayOfModelsShouldReturnTheCorrespondingArrayOfModels() {
        Integer[] modelsConverted = new Integer[VIEW_MODELS.size()];
        modelsConverted = assembler.toArrayOfModels(modelsConverted, VIEW_MODELS);
        assertArrayEquals(MODELS_AS_ARRAY, modelsConverted);
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

