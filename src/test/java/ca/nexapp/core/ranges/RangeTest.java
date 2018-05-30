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

    @Test
    public void givenAnElementOnLeftEnd_ItShouldNotContainsIt() {
        Range<Integer> range = Range.of(20, 50);

        assertThat(range.contains(4)).isFalse();
    }

    @Test
    public void givenAnElementOnLowerbound_ItShouldContainsIt() {
        Range<Integer> range = Range.of(20, 50);

        assertThat(range.contains(20)).isTrue();
    }

    @Test
    public void givenAnElementInsideRange_ItShouldContainsIt() {
        Range<Integer> range = Range.of(20, 50);

        assertThat(range.contains(45)).isTrue();
    }

    @Test
    public void givenAnElementOnUpperbound_ItShouldContainsIt() {
        Range<Integer> range = Range.of(20, 50);

        assertThat(range.contains(50)).isTrue();
    }

    @Test
    public void givenAnElementOnRightEnd_ItShouldNotContainsIt() {
        Range<Integer> range = Range.of(20, 50);

        assertThat(range.contains(99232)).isFalse();
    }

}
