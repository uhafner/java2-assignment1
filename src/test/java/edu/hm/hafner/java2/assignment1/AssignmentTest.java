package edu.hm.hafner.java2.assignment1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link Assignment}.
 *
 * @author Ullrich Hafner
 */
class AssignmentTest {
    @Test
    void shouldCreateEmptyAssignment() {
        var assignment = new Assignment(18, 5);

        assertThat(assignment.getNumber()).isEqualTo(18);
        assertThat(assignment.getTests()).isEqualTo(5);
        assertThat(assignment.getGreen()).isEqualTo(0);
        assertThat(assignment.getRed()).isEqualTo(5);
        assertThat(assignment.getPercentage()).isEqualTo(0);
        assertThat(assignment.isSufficient()).isFalse();
    }

    @Test
    void shouldFixOneTest() {
        var assignment = new Assignment(1, 10);

        assignment.solve(1);

        assertThat(assignment.getTests()).isEqualTo(10);
        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(9);
        assertThat(assignment.getPercentage()).isEqualTo(10);
        assertThat(assignment.isSufficient()).isFalse();
    }

    @Test
    void shouldFixAllTests() {
        var assignment = new Assignment(1, 2);

        assignment.solve(0);

        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(1);
        assertThat(assignment.getPercentage()).isEqualTo(50);
        assertThat(assignment.isSufficient()).isTrue();

        assignment.solve(1);

        assertThat(assignment.getGreen()).isEqualTo(2);
        assertThat(assignment.getRed()).isEqualTo(0);
        assertThat(assignment.getPercentage()).isEqualTo(100);
        assertThat(assignment.isSufficient()).isTrue();
    }

    @Test
    void shouldNotSolveTests() {
        var assignment = new Assignment(1, 3);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> assignment.solve(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> assignment.solve(3));
    }

    @Test
    void shouldNotSolveSameTestTwice() {
        var assignment = new Assignment(1, 3);

        assignment.solve(0);

        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(2);
        assertThat(assignment.getPercentage()).isEqualTo(33);

        assignment.solve(0);

        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(2);
        assertThat(assignment.getPercentage()).isEqualTo(33);
    }
}
