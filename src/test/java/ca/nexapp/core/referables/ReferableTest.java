package ca.nexapp.core.referables;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class ReferableTest {

    private static final ReferenceNumber A_REFERENCE = new ReferenceNumber("ref-A");
    private static final ReferenceNumber ANOTHER_REFERENCE = new ReferenceNumber("ref-B");

    @Test
    public void givenTheSameReference_ShouldHaveTheReference() {
        Referable referable = withReference(A_REFERENCE);

        assertThat(referable.hasReference(A_REFERENCE)).isTrue();
    }

    @Test
    public void givenADifferentReference_ShouldNotHaveTheReference() {
        Referable referable = withReference(A_REFERENCE);

        assertThat(referable.hasReference(ANOTHER_REFERENCE)).isFalse();
    }

    @Test
    public void givenAnotherReferableWithTheSameReference_ShouldBeTheSame() {
        Referable referable = withReference(A_REFERENCE);
        Referable another = withReference(A_REFERENCE);

        assertThat(referable.hasSameReference(another)).isTrue();
    }

    @Test
    public void givenAnotherReferableWithADifferentReference_ShouldNotBeTheSame() {
        Referable referable = withReference(A_REFERENCE);
        Referable another = withReference(ANOTHER_REFERENCE);

        assertThat(referable.hasSameReference(another)).isFalse();
    }

    private Referable withReference(ReferenceNumber reference) {
        return new Referable() {

            @Override
            public ReferenceNumber getReference() {
                return reference;
            }
        };
    }
}
