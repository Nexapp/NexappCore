package ca.nexapp.application.assemblers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class Assembler<M, V> {

    public abstract M toModel(V viewModel);

    public abstract V toViewModel(M model);

    public final Collection<M> toModels(Collection<V> viewModels) {
        Collection<M> models = new ArrayList<>(viewModels.size());
        for (V viewModel : viewModels) {
            models.add(toModel(viewModel));
        }
        return models;
    }

    public final Collection<V> toViewModels(Collection<M> models) {
        Collection<V> viewModels = new ArrayList<>(models.size());
        for (M model : models) {
            viewModels.add(toViewModel(model));
        }
        return viewModels;
    }

    public final M[] toArrayOfModels(M[] emptyModels, Collection<V> viewModels) {
        Iterator<V> viewModelsIterator = viewModels.iterator();

        for (int i = 0; viewModelsIterator.hasNext(); ++i) {
            V viewModel = viewModelsIterator.next();
            M model = toModel(viewModel);

            emptyModels[i] = model;
        }

        return emptyModels;
    }

    public final V[] toArrayOfViewModels(V[] emptyViewModels, Collection<M> models) {
        Iterator<M> modelsIterator = models.iterator();

        for (int i = 0; modelsIterator.hasNext(); ++i) {
            M model = modelsIterator.next();
            V viewModel = toViewModel(model);

            emptyViewModels[i] = viewModel;
        }

        return emptyViewModels;
    }
}
