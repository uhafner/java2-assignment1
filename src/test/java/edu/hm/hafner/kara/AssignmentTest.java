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
class AssignmentTest {
    private static final String ROOT = "edu.hm.hafner.kara";
    private static final String ASSIGNMENT = "Assignment";

    @Test
    void shouldCreateEmptyAssignment() {
        var assignment = createAssignment(5);

        assertThat(assignment).extracting("number").isEqualTo(1);
        assertThat(assignment).extracting("tests").isEqualTo(5);
        assertThat(assignment).extracting("green").isEqualTo(0);
        assertThat(assignment).extracting("red").isEqualTo(5);
        assertThat(assignment).extracting("percentage").isEqualTo(0);
        assertThat(assignment).extracting("sufficient").isEqualTo(false);
    }

    @Test
    void shouldFixOneTest() {
        var assignmentClass = findAssignment();

        var assignment = create(assignmentClass, 5);

        var solve = assignmentClass.getMethod("solve", int.class);
        call(solve, assignment, 1);

        assertThat(assignment).extracting("number").isEqualTo(1);
        assertThat(assignment).extracting("tests").isEqualTo(5);
        assertThat(assignment).extracting("green").isEqualTo(1);
        assertThat(assignment).extracting("red").isEqualTo(4);
        assertThat(assignment).extracting("percentage").isEqualTo(20);
        assertThat(assignment).extracting("sufficient").isEqualTo(false);
    }

    @Test
    void shouldNotChangeWhenSameTestIsFixedAgain() {
        var assignmentClass = findAssignment();

        var assignment = create(assignmentClass, 5);

        var solve = assignmentClass.getMethod("solve", int.class);

        call(solve, assignment, 1);
        call(solve, assignment, 1);

        assertThat(assignment).extracting("number").isEqualTo(1);
        assertThat(assignment).extracting("tests").isEqualTo(5);
        assertThat(assignment).extracting("green").isEqualTo(1);
        assertThat(assignment).extracting("red").isEqualTo(4);
        assertThat(assignment).extracting("percentage").isEqualTo(20);
        assertThat(assignment).extracting("sufficient").isEqualTo(false);
    }

    @Test
    void shouldSolveAllAssignments() {
        var assignmentClass = findAssignment();

        var assignment = create(assignmentClass, 5);

        var solve = assignmentClass.getMethod("solve", int.class);

        call(solve, assignment, 4);
        call(solve, assignment, 3);
        call(solve, assignment, 2);
        call(solve, assignment, 1);
        call(solve, assignment, 0);

        assertThat(assignment).extracting("number").isEqualTo(1);
        assertThat(assignment).extracting("tests").isEqualTo(5);
        assertThat(assignment).extracting("green").isEqualTo(5);
        assertThat(assignment).extracting("red").isEqualTo(0);
        assertThat(assignment).extracting("percentage").isEqualTo(100);
        assertThat(assignment).extracting("sufficient").isEqualTo(true);
    }

    @Test
    void shouldSolveAllAssignmentsAgain() {
        var assignmentClass = findAssignment();

        var assignment = create(assignmentClass, 5);

        var solve = assignmentClass.getMethod("solve", int.class);

        call(solve, assignment, 4);
        call(solve, assignment, 3);
        call(solve, assignment, 2);
        call(solve, assignment, 1);
        call(solve, assignment, 0);
        call(solve, assignment, 4);
        call(solve, assignment, 3);
        call(solve, assignment, 2);
        call(solve, assignment, 1);
        call(solve, assignment, 0);

        assertThat(assignment).extracting("number").isEqualTo(1);
        assertThat(assignment).extracting("tests").isEqualTo(5);
        assertThat(assignment).extracting("green").isEqualTo(5);
        assertThat(assignment).extracting("red").isEqualTo(0);
        assertThat(assignment).extracting("percentage").isEqualTo(100);
        assertThat(assignment).extracting("sufficient").isEqualTo(true);
    }

    private void call(final JavaMethod method, final Object assignment, final int intArg) {
        try {
            method.reflect().invoke(assignment, intArg);
        }
        catch (InvocationTargetException | IllegalAccessException exception) {
            throw new AssertionError(exception);
        }
    }

    private static Object createAssignment(final int tests) {
        JavaClass javaClass = findAssignment();

        return create(javaClass, tests);
    }

    private static JavaClass findAssignment() {
        var classes = new ClassFileImporter().importPackages(ROOT);

        var javaClass = classes.get(ROOT + "." + ASSIGNMENT);
        assertThat(javaClass.getPackageName()).isEqualTo(ROOT);
        assertThat(javaClass.getName()).endsWith(ASSIGNMENT);
        return javaClass;
    }

    private static Object create(final JavaClass javaClass, final int tests) {
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
