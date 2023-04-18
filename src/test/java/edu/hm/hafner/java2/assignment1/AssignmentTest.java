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
        assertThat(assignment.isSufficient()).isEqualTo(false);
    }

    @Test
    void shouldFixOneTest() {
        var assignment = new Assignment(1, 10);
        assignment.solve(1);

        assertThat(assignment.getTests()).isEqualTo(10);
        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(9);
        assertThat(assignment.getPercentage()).isEqualTo(10);
        assertThat(assignment.isSufficient()).isEqualTo(false);
    }

    @Test
    void shouldFixAllTests() {
        var assignment = new Assignment(1, 1);
        assignment.solve(0);

        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(0);
        assertThat(assignment.getPercentage()).isEqualTo(100);
        assertThat(assignment.isSufficient()).isEqualTo(true);
    }

    @Test
    void shouldNotSolveTests() {
        var assignment = new Assignment(1, 3);
        assignment.solve(-1);
        assignment.solve(3);

        assertThat(assignment.getGreen()).isEqualTo(0);
        assertThat(assignment.getRed()).isEqualTo(3);
        assertThat(assignment.getPercentage()).isEqualTo(0);
    }

    @Test
    void shouldNotSolveSameTestTwice() {
        var assignment = new Assignment(1, 3);
        assignment.solve(0);
        assignment.solve(0);

        assertThat(assignment.getGreen()).isEqualTo(1);
        assertThat(assignment.getRed()).isEqualTo(2);
        assertThat(assignment.getPercentage()).isEqualTo(33);
    }
}
