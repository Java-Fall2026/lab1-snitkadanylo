package ua.delivery.model;

import ua.delivery.util.DeliveryUtils;

public record Vehicle(String vinCode, String model, double maxWeightKg) {

    public Vehicle {
        vinCode = DeliveryUtils.cleanText(vinCode, "vinCode");
        model = DeliveryUtils.cleanText(model, "model");
        DeliveryUtils.validatePositive(maxWeightKg, "maxWeightKg");
    }

    public static Vehicle of(String vinCode, String model, double maxWeightKg) {
        return new Vehicle(vinCode, model, maxWeightKg);
    }
}