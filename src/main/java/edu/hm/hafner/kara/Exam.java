package edu.hm.hafner.kara;

import java.util.Arrays;

/**
 * An exam contains several assignments.
 *
 * @author Ullrich Hafner
 */
public class Exam {
    private Assignment[] assignments = new Assignment[0];

    /**
     * Adds a new assignment.
     * @param assignment
     */
    public void addAssignment(final Assignment assignment) {
        assignments = Arrays.copyOf(assignments, assignments.length + 1);
        assignments[assignments.length - 1] = assignment;
    }

    public Assignment getAssignment(final int position) {
        return assignments[position];
    }

    public int getSize() {
        return assignments.length;
    }

    public int getScore() {
        if (getSize() == 0) {
            return 100;
        }

        int percentage = 0;
        for (Assignment assignment : assignments) {
            percentage += assignment.getPercentage();
        }
        return percentage / getSize();
    }

    public boolean isSufficient() {
        return getScore() > 50;
    }

    public int getGrade() {
        var score = getScore();
        if (score >= 95) {
            return 1;
        }
        if (score >= 80) {
            return 2;
        }
        if (score >= 65) {
            return 3;
        }
        if (score >= 50) {
            return 4;
        }
        if (score > 0) {
            return 5;
        }

        return 6;
    }
}
