package ua.delivery.model;

public enum CargoType {
    STANDARD(1.0),
    FRAGILE(1.35),
    HAZARDOUS(1.6),
    PERISHABLE(1.25);

    private final double rateMultiplier;

    CargoType(double rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }

    public double getRateMultiplier() {
        return rateMultiplier;
    }
}