package br.unitins.tp1.model;

public final class IsbnUtils {

    private IsbnUtils() {
    }

    public static String normalize(String isbn) {
        return isbn == null ? null : isbn.replaceAll("[\\s-]", "").toUpperCase();
    }

    public static boolean isValid(String isbn) {
        String value = normalize(isbn);
        if (value == null) {
            return false;
        }

        if (value.length() == 13 && isAsciiDigits(value)) {
            int sum = 0;
            for (int index = 0; index < value.length(); index++) {
                sum += (value.charAt(index) - '0') * (index % 2 == 0 ? 1 : 3);
            }
            return sum % 10 == 0;
        }

        if (value.length() == 10
                && isAsciiDigits(value.substring(0, 9))
                && (isAsciiDigit(value.charAt(9)) || value.charAt(9) == 'X')) {
            int sum = 0;
            for (int index = 0; index < 9; index++) {
                sum += (value.charAt(index) - '0') * (10 - index);
            }
            sum += value.charAt(9) == 'X' ? 10 : value.charAt(9) - '0';
            return sum % 11 == 0;
        }

        return false;
    }

    private static boolean isAsciiDigits(String value) {
        return value.chars().allMatch(IsbnUtils::isAsciiDigit);
    }

    private static boolean isAsciiDigit(int character) {
        return character >= '0' && character <= '9';
    }
}
