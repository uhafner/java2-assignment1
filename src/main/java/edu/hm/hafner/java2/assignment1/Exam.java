package edu.hm.hafner.java2.assignment1;

import java.util.Arrays;

/**
 * An exam contains several assignments.
 */
public class Exam {
    private static final int LIMIT_ONE = 95;
    private static final int LIMIT_TWO = 80;
    private static final int LIMIT_THREE = 65;
    private static final int LIMIT_FOUR = 50;
    private static final int LIMIT_FIVE = 0;

    private Assignment[] assignments = new Assignment[0];

    /**
     * Returns the number of assignments.
     *
     * @return the number of assignments
     */
    public int getSize() {
        return assignments.length;
    }

    /**
     * Adds an assignment to this exam.
     *
     * @param assignment
     *         the assignment to add
     */
    public void addAssignment(final Assignment assignment) {
        assignments = Arrays.copyOf(assignments, assignments.length + 1);
        assignments[assignments.length - 1] = assignment;
    }

    /**
     * Returns the assignment at the given index.
     *
     * @param index
     *         index of the assignment
     *
     * @return assignment at the given index
     */
    public Assignment getAssignment(final int index) {
        return assignments[index];
    }

    /**
     * Get total score of all assignments in the exam.
     *
     * @return score between 0 and 100
     */
    public int getScore() {
        var size = getSize();
        if (size == 0) {
            return 100; // Oder 0 oder Exception
        }

        int percentageSum = 0;
        for (Assignment assignment : assignments) {
            percentageSum += assignment.getPercentage();
        }
        return percentageSum / size;
    }

    /**
     * Returns whether the score of this exam is greater or equal than 50 percent.
     *
     * @return if score is sufficient
     */
    public boolean isSufficient() {
        return getScore() >= LIMIT_FOUR;
    }

    /**
     * Returns the grade for this exam between 1 and 6.
     *
     * @return grade for exam
     */
    public int getGrade() {
        if (getScore() >= LIMIT_ONE) {
            return 1;
        }
        if (getScore() >= LIMIT_TWO) {
            return 2;
        }
        if (getScore() >= LIMIT_THREE) {
            return 3;
        }
        if (getScore() >= LIMIT_FOUR) {
            return 4;
        }
        if (getScore() > LIMIT_FIVE) {
            return 5;
        }
        return 6;
    }
}
