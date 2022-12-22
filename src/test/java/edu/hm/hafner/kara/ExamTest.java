package edu.hm.hafner.kara;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link Assignment}.
 *
 * @author Ullrich Hafner
 */
class ExamTest {
    private static final String ROOT = "edu.hm.hafner.kara";
    private static final String ASSIGNMENT = "Assignment";
    private static final String EXAM = "Exam";

    @Test
    void shouldCreateEmptyExam() {
        var exam = createExam();

        assertThat(exam).extracting("size").isEqualTo(0);
        assertThat(exam).extracting("score").isEqualTo(100);
        assertThat(exam).extracting("sufficient").isEqualTo(true);
        assertThat(exam).extracting("grade").isEqualTo(1);
    }

    @Test
    void shouldCreateExamWithOneAssignment() {
        JavaClass examClass = findClass(EXAM);
        var exam = newExam(examClass);

        addAssignment(examClass, exam, createAssignment(5));

        assertThat(exam).extracting("size").isEqualTo(1);
        assertThat(exam).extracting("score").isEqualTo(0);
        assertThat(exam).extracting("sufficient").isEqualTo(false);
        assertThat(exam).extracting("grade").isEqualTo(6);
    }

    @Test
    void shouldCreateExamWithTwoAssignment() {
        JavaClass examClass = findClass(EXAM);
        var exam = newExam(examClass);

        addAssignment(examClass, exam, createAssignment(5));
        addAssignment(examClass, exam, createAssignment(10));

        assertThat(exam).extracting("size").isEqualTo(2);
        assertThat(exam).extracting("score").isEqualTo(0);
        assertThat(exam).extracting("sufficient").isEqualTo(false);
        assertThat(exam).extracting("grade").isEqualTo(6);
    }

    @Test
    void shouldCreateExamWithOneAssignmentAndSolveOneTest() {
        JavaClass examClass = findClass(EXAM);
        var exam = newExam(examClass);

        addAssignment(examClass, exam, createAssignment(5));

        Object assignment = getAssignment(examClass, exam, 0);
        solveAssignmentTest(assignment, 1);

        assertThat(exam).extracting("size").isEqualTo(1);
        assertThat(exam).extracting("score").isEqualTo(20);
        assertThat(exam).extracting("sufficient").isEqualTo(false);
        assertThat(exam).extracting("grade").isEqualTo(5);
    }

    @Test
    void shouldCreateExamWithOneAssignmentAndSolveAllTests() {
        JavaClass examClass = findClass(EXAM);
        var exam = newExam(examClass);

        addAssignment(examClass, exam, createAssignment(5));

        Object assignment = getAssignment(examClass, exam, 0);
        solveAssignmentTest(assignment, 0);
        solveAssignmentTest(assignment, 1);
        solveAssignmentTest(assignment, 2);
        solveAssignmentTest(assignment, 3);
        solveAssignmentTest(assignment, 4);

        assertThat(exam).extracting("size").isEqualTo(1);
        assertThat(exam).extracting("score").isEqualTo(100);
        assertThat(exam).extracting("sufficient").isEqualTo(true);
        assertThat(exam).extracting("grade").isEqualTo(1);
    }

    @Test
    void shouldCreateExamWithTwoAssignmentAndSolve() {
        JavaClass examClass = findClass(EXAM);
        var exam = newExam(examClass);

        addAssignment(examClass, exam, createAssignment(5));
        addAssignment(examClass, exam, createAssignment(10));

        Object assignment0 = getAssignment(examClass, exam, 0);
        solveAssignmentTest(assignment0, 1);

        assertThat(exam).extracting("size").isEqualTo(2);
        assertThat(exam).extracting("score").isEqualTo(10);
        assertThat(exam).extracting("sufficient").isEqualTo(false);
        assertThat(exam).extracting("grade").isEqualTo(5);

        Object assignment1 = getAssignment(examClass, exam, 1);
        solveAssignmentTest(assignment1, 3);
        solveAssignmentTest(assignment1, 8);

        assertThat(exam).extracting("size").isEqualTo(2);
        assertThat(exam).extracting("score").isEqualTo(20);
        assertThat(exam).extracting("sufficient").isEqualTo(false);
        assertThat(exam).extracting("grade").isEqualTo(5);
    }

    private void solveAssignmentTest(final Object assignment, final int test) {
        var solve = findClass(ASSIGNMENT).getMethod("solve", int.class);

        call(solve, assignment, test);
    }

    private Object getAssignment(final JavaClass examClass, final Object exam, final int number) {
        var getAssignment = examClass.getMethod("getAssignment", int.class);

        return call(getAssignment, exam, number);
    }

    private static JavaMethod getAddAssignment(final JavaClass examClass) {
        return examClass.getMethod("addAssignment", ROOT + "." + ASSIGNMENT);
    }

    private void addAssignment(final JavaClass examClass, final Object exam, final Object assignment) {
        try {
            getAddAssignment(examClass).reflect().invoke(exam, assignment);
        }
        catch (InvocationTargetException | IllegalAccessException exception) {
            throw new AssertionError(exception);
        }
    }

    private Object call(final JavaMethod method, final Object assignment, final int intArg) {
        try {
            return method.reflect().invoke(assignment, intArg);
        }
        catch (InvocationTargetException | IllegalAccessException exception) {
            throw new AssertionError(exception);
        }
    }

    private static Object createAssignment(final int tests) {
        JavaClass javaClass = findClass(ASSIGNMENT);

        return createAssignment(javaClass, tests);
    }

    private static Object createExam() {
        JavaClass javaClass = findClass(EXAM);

        return newExam(javaClass);
    }

    private static Object newExam(final JavaClass javaClass) {
        var constructor = javaClass.getConstructor();
        assertThat(constructor).isNotNull();

        Constructor<?> javaConstructor = constructor.reflect();
        try {
            return javaConstructor.newInstance();
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new AssertionError(exception);
        }
    }

    private static JavaClass findClass(final String name) {
        var classes = new ClassFileImporter().importPackages(ROOT);

        var javaClass = classes.get(ROOT + "." + name);
        assertThat(javaClass.getPackageName()).isEqualTo(ROOT);
        assertThat(javaClass.getName()).endsWith(name);
        return javaClass;
    }

    private static Object createAssignment(final JavaClass javaClass, final int tests) {
        var constructor = javaClass.getConstructor(int.class, int.class);
        assertThat(constructor).isNotNull();

        Constructor<?> javaConstructor = constructor.reflect();
        try {
            return javaConstructor.newInstance(1, tests);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new AssertionError(exception);
        }
    }

}
