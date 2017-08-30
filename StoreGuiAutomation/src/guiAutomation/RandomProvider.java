package guiAutomation;

import java.util.Random;

public final class RandomProvider {
    // Create a static instance of Random to allow it to improve randomness
    static Random _random = new Random();

    // Prevent instantiation to enforce that this class is static
    private RandomProvider() {
    }

    // Generate random numbers with inclusive min & max
    public static int next(int min, int max) {
        return _random.nextInt(max - min + 1) + min;
    }
}