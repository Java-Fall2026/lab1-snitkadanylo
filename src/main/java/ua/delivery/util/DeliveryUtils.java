package ua.delivery.util;

import ua.delivery.model.CargoType;
import ua.delivery.model.ShipmentStatus;
import ua.delivery.model.Waybill;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DeliveryUtils {

    private DeliveryUtils() {
    }

    public static String cleanText(String text, String fieldName) {
        ValidationHelper.requireNotBlank(text, fieldName);
        return FormatHelper.normalize(text);
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

    public static String describeCargoType(CargoType cargoType) {
        ValidationHelper.requireNotNull(cargoType, "cargoType");
        return switch (cargoType) {
            case STANDARD -> "Звичайний вантаж (базовий тариф)";
            case FRAGILE -> "Крихкий вантаж (потребує обережного транспортування)";
            case HAZARDOUS -> "Небезпечний вантаж (спеціальний дозвіл)";
            case PERISHABLE -> "Швидкопсувний вантаж (температурний режим)";
        };
    }

    public static String nextAvailableAction(ShipmentStatus status) {
        ValidationHelper.requireNotNull(status, "status");
        return switch (status) {
            case REGISTERED -> "Передати перевізнику та відправити в рейс";
            case IN_TRANSIT -> "Підтвердити прибуття у відділення або оформити повернення";
            case DELIVERED -> "Доставку завершено, дій не потрібно";
            case RETURNED -> "Повернути відправнику на склад";
        };
    }

    public static long transitDays(Waybill waybill) {
        ValidationHelper.requireNotNull(waybill, "Waybill");
        return ChronoUnit.DAYS.between(waybill.dispatchDate(), waybill.estimatedArrival());
    }

    public static double loadPercent(Waybill waybill) {
        ValidationHelper.requireNotNull(waybill, "Waybill");
        double weight = waybill.shipment().getCargoWeightKg();
        double capacity = waybill.vehicle().maxWeightKg();
        return (weight / capacity) * 100.0;
    }
}