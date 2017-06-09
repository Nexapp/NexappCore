package ca.nexapp.core.ranges;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class RangeTest {

    @Test
    public void givenOnePointTouchRanges_ShouldBeOverlapping() {
        Range<Integer> left = Range.of(10, 25);
        Range<Integer> right = Range.of(25, 40);

        assertThat(left.isOverlapping(right)).isTrue();
    }

    @Test
    public void givenARangeLowerThanLeftEnd_ShouldNotBeOverlapping() {
        Range<Integer> range = Range.of(20, 50);
        Range<Integer> lower = Range.of(0, 10);

        assertThat(range.isOverlapping(lower)).isFalse();
    }

    @Test
    public void givenAnOverlapOnLeftEnd_ShouldBeOverlapping() {
        Range<Integer> range = Range.of(20, 50);
        Range<Integer> overlap = Range.of(10, 30);

        assertThat(range.isOverlapping(overlap)).isTrue();
    }

    @Test
    public void givenAnOverlapOnRightEnd_ShouldBeOverlapping() {
        Range<Integer> range = Range.of(20, 50);
        Range<Integer> overlap = Range.of(40, 60);

        assertThat(range.isOverlapping(overlap)).isTrue();
    }

    @Test
    public void givenARangeHigherThanRightEnd_ShouldNotBeOverlapping() {
        Range<Integer> range = Range.of(20, 50);
        Range<Integer> higher = Range.of(60, 90);

        assertThat(range.isOverlapping(higher)).isFalse();
    }

    @Test
    public void givenTheSameRange_ShouldBeOverlapping() {
        Range<Integer> range = Range.of(20, 50);
        Range<Integer> same = Range.of(20, 50);

        assertThat(range.isOverlapping(same)).isTrue();
    }
}
