package util;

import java.util.Random;

public class IdGenerator {

    private static final Random RNG = new Random();
    private static int defaultNumber = 8;
    private static String defaultAllowedCharacters = "ABCDEFGHIJKLMNOPQRSTUWabcdefghijklmnopqrstuvwxyz1234567890";

    public static String generate(int numberOfChars, String allowedChars) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < numberOfChars) {
            builder.append(allowedChars.charAt((int)(RNG.nextDouble()*allowedChars.length())));
        }
        return builder.toString();
    }

    public static String generate(int numberOfChars) {
        return generate(numberOfChars, defaultAllowedCharacters);
    }

    public static String generate(String allowedCharacters) {
        return generate(defaultNumber, allowedCharacters);
    }
    public static String generate() {
        return generate(defaultNumber, defaultAllowedCharacters);
    }
}
