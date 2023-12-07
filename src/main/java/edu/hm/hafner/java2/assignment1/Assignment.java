package edu.hm.hafner.java2.assignment1;

/**
 * Representation of an assignment.
 */
public class Assignment {
    private final int number;
    private final boolean[] testCases;
    private final int tests; // redundant as implicitly given by array length

    /**
     * Creates a new instance of {@link Assignment}.
     *
     * @param number
     *         the number of this assignment
     * @param tests
     *         the number of test cases
     */
    public Assignment(final int number, final int tests) {
        super();

        this.number = number;
        this.testCases = new boolean[tests];
        this.tests = tests;

        int i = 0;
    }

    public int getNumber() {
        return number;
    }

    public int getTests() {
        return tests;
    }

    /**
     * Returns the number of green tests.
     *
     * @return the number of solved tests.
     */
    public int getGreen() {
        int solvedAmount = 0;
        for (boolean isTestGreen : testCases) {
            if (isTestGreen) {
                solvedAmount++;
            }
        }
        return solvedAmount;
    }

    /**
     * Get number of red tests.
     *
     * @return number of unsolved tests
     */
    public int getRed() {
        return tests - getGreen();
    }

    /**
     * Get percentage of solved tests.
     *
     * @return percentage of solved tests
     */
    public int getPercentage() {
        int solvedAmount = getGreen();

        return 100 * solvedAmount / getTests();
    }

    /**
     * Solves a test with the given index.
     *
     * @param index
     *         index of test to solve
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void solve(final int index)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= testCases.length) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds: " + index);
        }
        testCases[index] = true;
    }

    /**
     * Returns whether the percentage of solved tests is greater or equal than 50 percent.
     *
     * @return if percentage is sufficient
     */
    public boolean isSufficient() {
        return getPercentage() >= 50;
    }
}
