package edu.hm.hafner.kara;

/**
 * Representation of an assignment.
 *
 * @author Ullrich Hafner
 */
public class Assignment {
    private final int number;
    private final int tests; // redundant as implicitly given by array length
    private final boolean[] solved;

    public Assignment(final int number, final int tests) {
        this.number = number;
        this.tests = tests;
        this.solved = new boolean[tests];
    }

    public int getNumber() {
        return number;
    }

    public int getTests() {
        return tests;
    }

    public void solve(final int test) {
        solved[test] = true;
    }

    public int getPercentage() {
        int solvedAmount = getGreen();

        return 100 * solvedAmount / tests;
    }

    public int getGreen() {
        int solvedAmount = 0;
        for (boolean isTestGreen : solved) {
            if (isTestGreen) {
                solvedAmount++;
            }
        }
        return solvedAmount;
    }

    public int getRed() {
        return tests - getGreen();
    }

    public boolean isSufficient() {
        return getPercentage() >= 50;
    }
}
