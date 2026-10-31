package ua.delivery.util;

class FormatHelper {

    private FormatHelper() {
    }

    static String normalize(String value) {
        return value == null ? null : value.trim();
    }

    static String normalizeUpper(String value) {
        return value == null ? null : value.trim().toUpperCase();
    }
}