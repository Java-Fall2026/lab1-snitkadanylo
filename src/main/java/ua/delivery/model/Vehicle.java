package ua.delivery.model;

import ua.common.BaseEntity;
import ua.delivery.util.DeliveryUtils;

import java.util.Objects;

public class Vehicle extends BaseEntity {

    private final String vinCode;
    private final String model;
    private final double maxWeightKg;

    private Vehicle(String vinCode, String model, double maxWeightKg) {
        super();
        this.vinCode = DeliveryUtils.cleanText(vinCode, "vinCode");
        this.model = DeliveryUtils.cleanText(model, "model");
        DeliveryUtils.validatePositive(maxWeightKg, "maxWeightKg");
        this.maxWeightKg = maxWeightKg;
    }

    public static Vehicle of(String vinCode, String model, double maxWeightKg) {
        return new Vehicle(vinCode, model, maxWeightKg);
    }

    public String getVinCode() {
        return vinCode;
    }

    public String getModel() {
        return model;
    }

    public double getMaxWeightKg() {
        return maxWeightKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vinCode, vehicle.vinCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vinCode);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vinCode='" + vinCode + '\'' +
                ", model='" + model + '\'' +
                ", maxWeightKg=" + maxWeightKg +
                ", createdAt=" + createdAt +
                '}';
    }
}