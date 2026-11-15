package ua.delivery.model;

import ua.common.BaseEntity;
import ua.delivery.util.DeliveryUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Shipment extends BaseEntity {

    private final String trackingNumber;
    private final Client sender;
    private final CargoType cargoType;
    private final double cargoWeightKg;
    private final LocalDate creationDate;
    private ShipmentStatus status;

    public Shipment(String trackingNumber, Client sender, CargoType cargoType,
                    double cargoWeightKg, LocalDate creationDate, ShipmentStatus status) {
        super();
        this.trackingNumber = DeliveryUtils.cleanText(trackingNumber, "trackingNumber");
        DeliveryUtils.validateNotNull(sender, "sender");
        this.sender = sender;

        DeliveryUtils.validateNotNull(cargoType, "cargoType");
        this.cargoType = cargoType;

        DeliveryUtils.validatePositive(cargoWeightKg, "cargoWeightKg");
        this.cargoWeightKg = cargoWeightKg;

        DeliveryUtils.validateNotFuture(creationDate, "creationDate");
        this.creationDate = creationDate;

        setStatus(status);
    }

    public final void setStatus(ShipmentStatus status) {
        DeliveryUtils.validateNotNull(status, "status");
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Client getSender() {
        return sender;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public double getCargoWeightKg() {
        return cargoWeightKg;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(trackingNumber, shipment.trackingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingNumber);
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", sender=" + sender +
                ", cargoType=" + cargoType +
                ", cargoWeightKg=" + cargoWeightKg +
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}