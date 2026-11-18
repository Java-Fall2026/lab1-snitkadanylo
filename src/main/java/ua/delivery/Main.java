package ua.delivery;

import ua.delivery.model.CargoType;
import ua.delivery.model.Client;
import ua.delivery.model.Shipment;
import ua.delivery.model.ShipmentStatus;
import ua.delivery.model.Vehicle;
import ua.delivery.model.Waybill;
import ua.delivery.util.DeliveryUtils;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. \u0421\u0442\u0432\u043e\u0440\u0435\u043d\u043d\u044f record-\u043e\u0431'\u0454\u043a\u0442\u0456\u0432 \u0456 \u0437\u0432\u0435\u0440\u043d\u0435\u043d\u043d\u044f \u0434\u043e \u043a\u043e\u043c\u043f\u043e\u043d\u0435\u043d\u0442\u0456\u0432 ===");
        Client client1 = Client.of("  12345678  ", "Nova Global Express", "+380501112233");
        Vehicle truck1 = Vehicle.of("1HGCR2F83HA000001", "Volvo FH16", 20000.0);

        System.out.println("\u041a\u043e\u043c\u043f\u043e\u043d\u0435\u043d\u0442\u0438 Client: taxId=" + client1.taxId()
                + ", companyName=" + client1.companyName()
                + ", contactPhone=" + client1.contactPhone());
        System.out.println("\u041a\u043e\u043c\u043f\u043e\u043d\u0435\u043d\u0442\u0438 Vehicle: vinCode=" + truck1.vinCode()
                + ", model=" + truck1.model()
                + ", maxWeightKg=" + truck1.maxWeightKg() + "\n");

        System.out.println("=== 2. Enum: values(), \u043f\u043e\u043b\u0435 \u043a\u043e\u043d\u0441\u0442\u0430\u043d\u0442\u0438, valueOf \u0443 try/catch ===");
        for (CargoType type : CargoType.values()) {
            System.out.println(type.name() + " -> \u043a\u043e\u0435\u0444\u0456\u0446\u0456\u0454\u043d\u0442 \u0442\u0430\u0440\u0438\u0444\u0443: " + type.getRateMultiplier());
        }
        try {
            CargoType.valueOf("UNKNOWN_CARGO");
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e IllegalArgumentException \u043f\u0440\u0438 valueOf: " + e.getMessage() + "\n");
        }

        System.out.println("=== 3. \u041e\u0431\u0438\u0434\u0432\u0430 switch-\u0432\u0438\u0440\u0430\u0437\u0438 \u0432 \u0440\u043e\u0431\u043e\u0442\u0456 ===");
        Shipment shipment1 = new Shipment(
                "TRK-987654",
                client1,
                CargoType.FRAGILE,
                4500.0,
                LocalDate.now().minusDays(2),
                ShipmentStatus.REGISTERED
        );
        System.out.println("\u041e\u043f\u0438\u0441 \u0432\u0430\u043d\u0442\u0430\u0436\u0443 (switch 1): " + DeliveryUtils.describeCargoType(shipment1.getCargoType()));
        System.out.println("\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u0430 \u0434\u0456\u044f (switch 2): " + DeliveryUtils.nextAvailableAction(shipment1.getStatus()));

        shipment1.setStatus(ShipmentStatus.IN_TRANSIT);
        System.out.println("\u041f\u0456\u0441\u043b\u044f \u043e\u043d\u043e\u0432\u043b\u0435\u043d\u043d\u044f \u0441\u0442\u0430\u0442\u0443\u0441\u0443 (" + shipment1.getStatus() + "): "
                + DeliveryUtils.nextAvailableAction(shipment1.getStatus()) + "\n");

        System.out.println("=== 4. Record \u0437 \u043d\u0435\u0432\u0430\u043b\u0456\u0434\u043d\u0438\u043c\u0438 \u0434\u0430\u043d\u0438\u043c\u0438 -> IllegalArgumentException ===");
        try {
            Client.of("   ", "TechCorp", "+380991234567");
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e \u0437 \u043a\u043e\u043c\u043f\u0430\u043a\u0442\u043d\u043e\u0433\u043e \u043a\u043e\u043d\u0441\u0442\u0440\u0443\u043a\u0442\u043e\u0440\u0430 Client: " + e.getMessage());
        }

        try {
            Vehicle smallVan = Vehicle.of("VAN-12345", "Ford Transit", 1500.0);
            new Waybill(shipment1, smallVan, LocalDate.now(), LocalDate.now().plusDays(1));
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e \u0437 \u043a\u043e\u043c\u043f\u0430\u043a\u0442\u043d\u043e\u0433\u043e \u043a\u043e\u043d\u0441\u0442\u0440\u0443\u043a\u0442\u043e\u0440\u0430 Waybill: " + e.getMessage() + "\n");
        }

        System.out.println("=== 5. \u0414\u0432\u0430 \u043e\u0434\u043d\u0430\u043a\u043e\u0432\u0456 record-\u043e\u0431'\u0454\u043a\u0442\u0438: equals \u0456 hashCode ===");
        Client clientDuplicate = Client.of("12345678", "Nova Global Express", "+380501112233");
        System.out.println("client1 == clientDuplicate: " + (client1 == clientDuplicate));
        System.out.println("client1.equals(clientDuplicate): " + client1.equals(clientDuplicate));
        System.out.println("client1.hashCode() == clientDuplicate.hashCode(): " + (client1.hashCode() == clientDuplicate.hashCode()) + "\n");

        System.out.println("=== 6. \u0412\u0438\u0432\u0456\u0434 record \u0447\u0435\u0440\u0435\u0437 \u0437\u0433\u0435\u043d\u0435\u0440\u043e\u0432\u0430\u043d\u0438\u0439 toString() ===");
        Waybill waybill1 = new Waybill(shipment1, truck1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(3));
        System.out.println(client1);
        System.out.println(truck1);
        System.out.println(waybill1);
        System.out.printf("\u0422\u0440\u0438\u0432\u0430\u043b\u0456\u0441\u0442\u044c: %d \u0434\u043d., \u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f: %.2f%%\n",
                DeliveryUtils.transitDays(waybill1), DeliveryUtils.loadPercent(waybill1));
    }
}