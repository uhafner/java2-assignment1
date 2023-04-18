package edu.hm.hafner.java2.assignment1;

/**
 * Representation of an assignment.
 */
public class Assignment {

    private final int number;
    private final int numberTestCases;
    private boolean[] testCases;

    public Assignment(final int number, final int numberTestCases) {
        this.number = number;
        this.numberTestCases = numberTestCases;
        this.testCases = new boolean[numberTestCases];
    }

    public int getNumber() {
        return number;
    }

    public int getTests() {
        return numberTestCases;
    }

    /**
     * Get number of green tests.
     *
     * @return number of solved tests.
     */
    public int getGreen() {
        int count = 0;
        for (boolean test : testCases) {
            if (test) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get number of red tests.
     *
     * @return number of unsolved tests
     */
    public int getRed() {
        int count = 0;
        for (boolean test : testCases) {
            if (!test) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get percentage of solved tests.
     *
     * @return percantage of solved tests
     */
    public int getPercentage() {
        if (getRed() == 0) {
            return 100;
        }
        float percentage = (float) getGreen() / getTests() * 100;
        return (int) percentage;
    }

    /**
     * Solves a test with the given index.
     *
     * @param index index of test to solve
     */
    public void solve(final int index) {
        if (index >= 0 && index < testCases.length) {
            testCases[index] = true;
        }
    }

    /**
     * Returns if percentage of solved tests is greater than 50 percent.
     *
     * @return if percentage is sufficient
     */
    public boolean isSufficient() {
        return getPercentage() >= 50;
    }
}
