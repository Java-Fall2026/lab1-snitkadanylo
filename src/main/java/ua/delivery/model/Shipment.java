package ua.delivery.model;

import ua.common.BaseEntity;
import ua.delivery.util.DeliveryUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Shipment extends BaseEntity {

    private final String trackingNumber;
    private final Client sender;
    private final String cargoType;
    private final double cargoWeightKg;
    private final LocalDate creationDate;
    private String status;

    public Shipment(String trackingNumber, Client sender, String cargoType,
                    double cargoWeightKg, LocalDate creationDate, String status) {
        super();
        this.trackingNumber = DeliveryUtils.cleanText(trackingNumber, "trackingNumber");
        DeliveryUtils.validateNotNull(sender, "sender");
        this.sender = sender;

        String normalizedCargo = DeliveryUtils.cleanUpper(cargoType, "cargoType");
        DeliveryUtils.validateCargoType(normalizedCargo);
        this.cargoType = normalizedCargo;

        DeliveryUtils.validatePositive(cargoWeightKg, "cargoWeightKg");
        this.cargoWeightKg = cargoWeightKg;

        DeliveryUtils.validateNotFuture(creationDate, "creationDate");
        this.creationDate = creationDate;

        setStatus(status);
    }

    public final void setStatus(String status) {
        String normalizedStatus = DeliveryUtils.cleanUpper(status, "status");
        DeliveryUtils.validateShipmentStatus(normalizedStatus);
        this.status = normalizedStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Client getSender() {
        return sender;
    }

    public String getCargoType() {
        return cargoType;
    }

    public double getCargoWeightKg() {
        return cargoWeightKg;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getStatus() {
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
                ", cargoType='" + cargoType + '\'' +
                ", cargoWeightKg=" + cargoWeightKg +
                ", creationDate=" + creationDate +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}