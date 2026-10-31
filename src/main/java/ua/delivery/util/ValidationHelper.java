package ua.delivery.util;

import java.time.LocalDate;
import java.util.Collection;

class ValidationHelper {

    private ValidationHelper() {
    }

    static void requireNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
    }

    static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be strictly positive (> 0), got: " + value);
        }
    }

    static void requireNotFuture(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(fieldName + " cannot be in the future, got: " + date);
        }
    }

    static void requireAllowedValue(String value, Collection<String> allowed, String fieldName) {
        requireNotBlank(value, fieldName);
        if (!allowed.contains(value)) {
            throw new IllegalArgumentException(fieldName + " must be one of " + allowed + ", got: " + value);
        }
    }
}