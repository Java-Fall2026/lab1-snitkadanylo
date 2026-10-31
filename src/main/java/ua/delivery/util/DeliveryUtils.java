package ua.delivery.util;

import ua.delivery.model.Waybill;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public final class DeliveryUtils {

    public static final Set<String> ALLOWED_CARGO_TYPES = Set.of(
            "STANDARD", "FRAGILE", "HAZARDOUS", "PERISHABLE"
    );

    public static final Set<String> ALLOWED_SHIPMENT_STATUSES = Set.of(
            "REGISTERED", "IN_TRANSIT", "DELIVERED", "RETURNED"
    );

    private DeliveryUtils() {
    }

    public static String cleanText(String text, String fieldName) {
        ValidationHelper.requireNotBlank(text, fieldName);
        return FormatHelper.normalize(text);
    }

    public static String cleanUpper(String text, String fieldName) {
        ValidationHelper.requireNotBlank(text, fieldName);
        return FormatHelper.normalizeUpper(text);
    }

    public static void validatePositive(double value, String fieldName) {
        ValidationHelper.requirePositive(value, fieldName);
    }

    public static void validateNotNull(Object value, String fieldName) {
        ValidationHelper.requireNotNull(value, fieldName);
    }

    public static void validateNotFuture(LocalDate date, String fieldName) {
        ValidationHelper.requireNotFuture(date, fieldName);
    }

    public static void validateCargoType(String cargoType) {
        ValidationHelper.requireAllowedValue(cargoType, ALLOWED_CARGO_TYPES, "Cargo type");
    }

    public static void validateShipmentStatus(String status) {
        ValidationHelper.requireAllowedValue(status, ALLOWED_SHIPMENT_STATUSES, "Shipment status");
    }

    // --- Обчислювані методи за варіантом ---

    /**
     * Скільки днів триває перевезення (між датою відправлення та очікуваного прибуття).
     */
    public static long transitDays(Waybill waybill) {
        ValidationHelper.requireNotNull(waybill, "Waybill");
        return ChronoUnit.DAYS.between(waybill.getDispatchDate(), waybill.getEstimatedArrival());
    }

    /**
     * На скільки відсотків завантажена машина (вага вантажу відносно вантажопідйомності).
     */
    public static double loadPercent(Waybill waybill) {
        ValidationHelper.requireNotNull(waybill, "Waybill");
        double weight = waybill.getShipment().getCargoWeightKg();
        double capacity = waybill.getVehicle().getMaxWeightKg();
        return (weight / capacity) * 100.0;
    }
}