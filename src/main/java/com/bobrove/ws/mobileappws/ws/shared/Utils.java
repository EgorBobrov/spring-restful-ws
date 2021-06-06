package com.bobrove.ws.mobileappws.ws.shared;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Stream;

public class Utils {
    private static final Random RANDOM = new SecureRandom();
    private static final String SYMBOLS = generateSymbols();

    private static final int SYMBOLS_LENGTH = 62; // 10 numbers, 26 capital letters, 26 regular letters
    private static final int USER_ID_LENGTH = 30;

    private static String generateSymbols() {
        StringBuilder sb = new StringBuilder(SYMBOLS_LENGTH);
        for (int i = 0; i <= 9; i++) {
            sb.append(i);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String generateUserId() {
        return generateRandomString(USER_ID_LENGTH);
    }

    public static String generateRandomString(int length) {
        StringBuilder result = new StringBuilder(length);
        Stream.generate(() -> SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS_LENGTH)))
                .limit(length)
                .forEach(result::append);
        return result.toString();
    }
}
