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
        var assignment = new Assignment();

        assertThat(assignment).isSameAs(assignment);
    }
}
