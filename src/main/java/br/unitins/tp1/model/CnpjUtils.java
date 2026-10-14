package br.unitins.tp1.model;

import java.util.Locale;

public final class CnpjUtils {

    private static final int[] FIRST_DIGIT_WEIGHTS = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] SECOND_DIGIT_WEIGHTS = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    private CnpjUtils() {
    }

    public static String normalize(String cnpj) {
        return cnpj == null
                ? null
                : cnpj.replaceAll("[./\\-\\s]", "").toUpperCase(Locale.ROOT);
    }

    public static boolean isValid(String cnpj) {
        if (cnpj == null || !cnpj.matches("[A-Za-z0-9./\\-\\s]+")) {
            return false;
        }
        String digits = normalize(cnpj);
        if (digits.length() != 14
                || !digits.substring(0, 12).matches("[A-Z0-9]{12}")
                || !digits.substring(12).matches("[0-9]{2}")
                || digits.chars().distinct().count() == 1) {
            return false;
        }

        int firstDigit = calculateDigit(digits, FIRST_DIGIT_WEIGHTS);
        int secondDigit = calculateDigit(digits.substring(0, 12) + firstDigit, SECOND_DIGIT_WEIGHTS);
        return digits.charAt(12) - '0' == firstDigit && digits.charAt(13) - '0' == secondDigit;
    }

    private static int calculateDigit(String digits, int[] weights) {
        int sum = 0;
        for (int index = 0; index < weights.length; index++) {
            sum += (digits.charAt(index) - 48) * weights[index];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
