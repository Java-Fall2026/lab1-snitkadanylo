package ua.delivery.model;

import ua.common.BaseEntity;
import ua.delivery.util.DeliveryUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Waybill extends BaseEntity {

    private final Shipment shipment;
    private final Vehicle vehicle;
    private final LocalDate dispatchDate;
    private final LocalDate estimatedArrival;

    public Waybill(Shipment shipment, Vehicle vehicle, LocalDate dispatchDate, LocalDate estimatedArrival) {
        super();
        DeliveryUtils.validateNotNull(shipment, "shipment");
        DeliveryUtils.validateNotNull(vehicle, "vehicle");
        DeliveryUtils.validateNotNull(dispatchDate, "dispatchDate");
        DeliveryUtils.validateNotNull(estimatedArrival, "estimatedArrival");

        if (shipment.getCargoWeightKg() > vehicle.getMaxWeightKg()) {
            throw new IllegalArgumentException("Cargo weight (" + shipment.getCargoWeightKg()
                    + " kg) exceeds vehicle max capacity (" + vehicle.getMaxWeightKg() + " kg)");
        }

        if (dispatchDate.isBefore(shipment.getCreationDate())) {
            throw new IllegalArgumentException("Dispatch date (" + dispatchDate
                    + ") cannot be earlier than shipment creation date (" + shipment.getCreationDate() + ")");
        }

        if (estimatedArrival.isBefore(dispatchDate)) {
            throw new IllegalArgumentException("Estimated arrival (" + estimatedArrival
                    + ") cannot be before dispatch date (" + dispatchDate + ")");
        }

        this.shipment = shipment;
        this.vehicle = vehicle;
        this.dispatchDate = dispatchDate;
        this.estimatedArrival = estimatedArrival;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public LocalDate getEstimatedArrival() {
        return estimatedArrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waybill waybill = (Waybill) o;
        return Objects.equals(shipment, waybill.shipment) &&
                Objects.equals(dispatchDate, waybill.dispatchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipment, dispatchDate);
    }

    @Override
    public String toString() {
        return "Waybill{" +
                "shipment=" + shipment +
                ", vehicle=" + vehicle +
                ", dispatchDate=" + dispatchDate +
                ", estimatedArrival=" + estimatedArrival +
                ", createdAt=" + createdAt +
                '}';
    }
}