package edu.hm.hafner.java2.assignment1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link Exam}.
 *
 * @author Ullrich Hafner
 */
class ExamTest {
    @Test
    void shouldCreateEmptyExam() {
        var exam = new Exam();

        assertThat(exam).isSameAs(exam);
    }
}
