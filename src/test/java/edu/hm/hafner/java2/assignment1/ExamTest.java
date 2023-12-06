package edu.hm.hafner.java2.assignment1;

import org.junit.jupiter.api.DisplayName;
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

        assertThat(exam.getSize()).isEqualTo(0);
        assertThat(exam.getScore()).isEqualTo(100);
        assertThat(exam.isSufficient()).isTrue();
        assertThat(exam.getGrade()).isEqualTo(1);
    }

    @Test @DisplayName("Ein Examen mit genau einer Aufgabe sollte erstellt werden können.")
    void shouldCreateExamWithOneAssignment() {
        var exam = new Exam();

        var assignment = new Assignment(1, 5);
        exam.addAssignment(assignment);

        assertThat(exam.getSize()).isEqualTo(1);
        assertThat(exam.getAssignment(0)).isSameAs(assignment);

        assertThat(exam.getScore()).isEqualTo(0);
        assertThat(exam.isSufficient()).isFalse();
        assertThat(exam.getGrade()).isEqualTo(6);
    }

    @Test
    void shouldCreateExamWithTwoAssignments() {
        var exam = new Exam();

        var firstAssignment = new Assignment(1, 5);
        exam.addAssignment(firstAssignment);

        var secondAssignment = new Assignment(2, 3);
        exam.addAssignment(secondAssignment);

        assertThat(exam.getAssignment(0))
                .isSameAs(firstAssignment);
        assertThat(exam.getAssignment(1))
                .isSameAs(secondAssignment);

        assertThat(exam.getSize()).isEqualTo(2);
        assertThat(exam.getScore()).isEqualTo(0);
        assertThat(exam.isSufficient()).isFalse();
        assertThat(exam.getGrade()).isEqualTo(6);
    }

    @Test
    void shouldCreateExamWithOneAssignmentAndSolveOneTest() {
        var exam = new Exam();

        var assignment = new Assignment(1, 5);
        exam.addAssignment(assignment);

        assignment.solve(0);

        assertThat(exam.getSize()).isEqualTo(1);
        assertThat(exam.getScore()).isEqualTo(20);
        assertThat(exam.isSufficient()).isFalse();
        assertThat(exam.getGrade()).isEqualTo(5);
    }

    @Test
    void shouldSolveOneOfTwoAssignments() {
        var exam = new Exam();

        var assignment = new Assignment(1, 2);

        assignment.solve(0);
        assignment.solve(1);

        var secondAssignment = new Assignment(2, 2);

        exam.addAssignment(assignment);
        exam.addAssignment(secondAssignment);

        assertThat(exam.getSize()).isEqualTo(2);
        assertThat(exam.getScore()).isEqualTo(50);
        assertThat(exam.isSufficient()).isTrue();
        assertThat(exam.getGrade()).isEqualTo(4);
    }

    @Test
    void shouldCreateExamWithTwoAssignmentsAndSolveAllTests() {
        var exam = new Exam();

        var assignment = new Assignment(1, 2);
        assignment.solve(0);
        assignment.solve(1);

        var secondAssignment = new Assignment(2, 2);
        secondAssignment.solve(0);
        secondAssignment.solve(1);

        exam.addAssignment(assignment);
        exam.addAssignment(secondAssignment);

        assertThat(exam.getSize()).isEqualTo(2);
        assertThat(exam.getScore()).isEqualTo(100);
        assertThat(exam.isSufficient()).isTrue();
        assertThat(exam.getGrade()).isEqualTo(1);
    }

    @Test
    void shouldCreateExamWithThreeAssignmentsAndSolveTwo() {
        var exam = new Exam();

        var assignment = new Assignment(1, 1);
        assignment.solve(0);

        var secondAssignment = new Assignment(2, 1);
        secondAssignment.solve(0);

        exam.addAssignment(assignment);
        exam.addAssignment(secondAssignment);
        exam.addAssignment(new Assignment(3, 9));

        assertThat(exam.getSize()).isEqualTo(3);
        assertThat(exam.getScore()).isEqualTo(66);
        assertThat(exam.isSufficient()).isTrue();
        assertThat(exam.getGrade()).isEqualTo(3);
    }

    @Test
    void shouldTestGetAssignmentMethod() {
        var exam = new Exam();
        exam.addAssignment(new Assignment(1, 10));

        for (int i = 0; i < 8; i++) {
            exam.getAssignment(0).solve(i);
        }

        assertThat(exam.getSize()).isEqualTo(1);
        assertThat(exam.getScore()).isEqualTo(80);
        assertThat(exam.isSufficient()).isTrue();
        assertThat(exam.getGrade()).isEqualTo(2);
    }
}
