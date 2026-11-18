package ua.delivery.model;

import ua.delivery.util.DeliveryUtils;

import java.time.LocalDate;

public record Waybill(Shipment shipment, Vehicle vehicle, LocalDate dispatchDate, LocalDate estimatedArrival) {

    public Waybill {
        DeliveryUtils.validateNotNull(shipment, "shipment");
        DeliveryUtils.validateNotNull(vehicle, "vehicle");
        DeliveryUtils.validateNotNull(dispatchDate, "dispatchDate");
        DeliveryUtils.validateNotNull(estimatedArrival, "estimatedArrival");

        if (shipment.getCargoWeightKg() > vehicle.maxWeightKg()) {
            throw new IllegalArgumentException("Cargo weight (" + shipment.getCargoWeightKg()
                    + " kg) exceeds vehicle max capacity (" + vehicle.maxWeightKg() + " kg)");
        }

        if (dispatchDate.isBefore(shipment.getCreationDate())) {
            throw new IllegalArgumentException("Dispatch date (" + dispatchDate
                    + ") cannot be earlier than shipment creation date (" + shipment.getCreationDate() + ")");
        }

        if (estimatedArrival.isBefore(dispatchDate)) {
            throw new IllegalArgumentException("Estimated arrival (" + estimatedArrival
                    + ") cannot be before dispatch date (" + dispatchDate + ")");
        }
    }
}