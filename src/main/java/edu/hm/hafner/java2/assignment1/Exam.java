package edu.hm.hafner.java2.assignment1;

/**
 * An exam contains several assignments.
 */
public class Exam {
    private Assignment[] assignments = new Assignment[]{};

    /**
     * Get number of assignments.
     *
     * @return length of assignments array
     */
    public int getSize() {
        return assignments.length;
    }

    /**
     * Adds an assignment to the exam.
     *
     * @param assignment assignment to add
     */
    public void addAssignment(final Assignment assignment) {
        int currentLenght = assignments.length;
        Assignment[] newArray = new Assignment[currentLenght + 1];
        for (int i = 0; i < currentLenght; i++) {
            newArray[i] = assignments[i];
        }
        newArray[currentLenght] = assignment;
        assignments = newArray;
    }

    /**
     * Get assignment at the given index.
     *
     * @param index index of assignment
     * @return assignment at given index
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
        if (assignments.length < 1) {
            return 100;
        }
        int percentageSum = 0;
        for (Assignment assignment: assignments) {
            percentageSum += assignment.getPercentage();
        }
        return percentageSum / assignments.length;
    }

    /**
     * Returns if score of exam is greaten than 50 percent.
     *
     * @return if score is sufficient
     */
    public boolean isSufficient() {
        return getScore() >= 50;
    }

    /**
     * Get grade for exam between 1 and 6.
     *
     * @return grade for exam
     */
    public int getGrade() {
        if (getScore() >= 95) {
            return 1;
        } else if (getScore() >= 80) {
            return 2;
        } else if (getScore() >= 65) {
            return 3;
        } else if (getScore() >= 50) {
            return 4;
        } else if (getScore() > 0) {
            return 5;
        } else {
            return 6;
        }
    }

}
