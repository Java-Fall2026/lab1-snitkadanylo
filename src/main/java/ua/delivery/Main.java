package ua.delivery;

import ua.delivery.model.Client;
import ua.delivery.model.Shipment;
import ua.delivery.model.Vehicle;
import ua.delivery.model.Waybill;
import ua.delivery.util.DeliveryUtils;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. \u0421\u0442\u0432\u043e\u0440\u0435\u043d\u043d\u044f \u043e\u0431'\u0454\u043a\u0442\u0456\u0432 \u043a\u043e\u043d\u0441\u0442\u0440\u0443\u043a\u0442\u043e\u0440\u043e\u043c \u0456 \u0444\u0430\u0431\u0440\u0438\u0447\u043d\u0438\u043c \u043c\u0435\u0442\u043e\u0434\u043e\u043c ===");
        Client client1 = Client.of("12345678", "Nova Global Express", "+380501112233");
        Vehicle truck1 = Vehicle.of("1HGCR2F83HA000001", "Volvo FH16", 20000.0);
        
        Shipment shipment1 = new Shipment(
                "TRK-987654",
                client1,
                "fragile",
                4500.0,
                LocalDate.now().minusDays(2),
                "registered"
        );
        Waybill waybill1 = new Waybill(shipment1, truck1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(3));
        System.out.println("\u0421\u0442\u0432\u043e\u0440\u0435\u043d\u043e \u0443\u0441\u043f\u0456\u0448\u043d\u043e:\n" + waybill1 + "\n");

        System.out.println("=== 2. \u041d\u043e\u0440\u043c\u0430\u043b\u0456\u0437\u0430\u0446\u0456\u044f \u0432 \u0434\u0456\u0457 ===");
        System.out.println("\u0412\u0445\u0456\u0434\u043d\u0456 \u0434\u0430\u043d\u0456 \u0431\u0443\u043b\u0438 'fragile' \u0442\u0430 'registered'");
        System.out.println("\u0412 \u043e\u0431'\u0454\u043a\u0442\u0456 \u0437\u0431\u0435\u0440\u0435\u0436\u0435\u043d\u043e cargoType: " + shipment1.getCargoType());
        System.out.println("\u0412 \u043e\u0431'\u0454\u043a\u0442\u0456 \u0437\u0431\u0435\u0440\u0435\u0436\u0435\u043d\u043e status: " + shipment1.getStatus() + "\n");

        System.out.println("=== 3. \u0422\u0440\u0438 \u0440\u0456\u0437\u043d\u0456 \u043f\u043e\u0440\u0443\u0448\u0435\u043d\u043d\u044f \u043f\u0440\u0430\u0432\u0438\u043b \u0432\u0430\u043b\u0456\u0434\u0430\u0446\u0456\u0457 (try/catch) ===");
        try {
            System.out.println("\u0421\u043f\u0440\u043e\u0431\u0430 1: \u0421\u0442\u0432\u043e\u0440\u0435\u043d\u043d\u044f \u043a\u043b\u0456\u0454\u043d\u0442\u0430 \u0437 \u043f\u043e\u0440\u043e\u0436\u043d\u0456\u043c \u0404\u0414\u0420\u041f\u041e\u0423:");
            Client.of("   ", "TechCorp", "+380991234567");
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e \u043e\u0447\u0456\u043a\u0443\u0432\u0430\u043d\u0438\u0439 \u0432\u0438\u043d\u044f\u0442\u043e\u043a: " + e.getMessage());
        }

        try {
            System.out.println("\u0421\u043f\u0440\u043e\u0431\u0430 2: \u0421\u0442\u0432\u043e\u0440\u0435\u043d\u043d\u044f \u0432\u0430\u043d\u0442\u0430\u0436\u0443 \u0437 \u0432\u0456\u0434'\u0454\u043c\u043d\u043e\u044e \u0432\u0430\u0433\u043e\u044e:");
            new Shipment("TRK-001", client1, "STANDARD", -50.0, LocalDate.now(), "REGISTERED");
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e \u043e\u0447\u0456\u043a\u0443\u0432\u0430\u043d\u0438\u0439 \u0432\u0438\u043d\u044f\u0442\u043e\u043a: " + e.getMessage());
        }

        try {
            System.out.println("\u0421\u043f\u0440\u043e\u0431\u0430 3: \u0412\u0430\u0433\u0430 \u0432\u0430\u043d\u0442\u0430\u0436\u0443 \u043f\u0435\u0440\u0435\u0432\u0438\u0449\u0443\u0454 \u0432\u0430\u043d\u0442\u0430\u0436\u043e\u043f\u0456\u0434\u0439\u043e\u043c\u043d\u0456\u0441\u0442\u044c \u0430\u0432\u0442\u043e:");
            Vehicle smallVan = Vehicle.of("VAN-12345", "Ford Transit", 1500.0);
            new Waybill(shipment1, smallVan, LocalDate.now(), LocalDate.now().plusDays(1));
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e \u043e\u0447\u0456\u043a\u0443\u0432\u0430\u043d\u0438\u0439 \u0432\u0438\u043d\u044f\u0442\u043e\u043a: " + e.getMessage());
        }
        System.out.println();

        System.out.println("=== 4. \u0421\u043f\u0440\u043e\u0431\u0430 \u0437\u0456\u043f\u0441\u0443\u0432\u0430\u0442\u0438 \u043a\u043e\u0440\u0435\u043a\u0442\u043d\u0438\u0439 \u043e\u0431'\u0454\u043a\u0442 \u0441\u0435\u0442\u0442\u0435\u0440\u043e\u043c ===");
        try {
            System.out.println("\u0412\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u044e\u0454\u043c\u043e \u043d\u0435\u043a\u043e\u0440\u0435\u043a\u0442\u043d\u0438\u0439 \u0441\u0442\u0430\u0442\u0443\u0441 \u0432\u0430\u043d\u0442\u0430\u0436\u0443 'LOST':");
            shipment1.setStatus("LOST");
        } catch (IllegalArgumentException e) {
            System.out.println("\u0412\u043f\u0456\u0439\u043c\u0430\u043d\u043e \u043e\u0447\u0456\u043a\u0443\u0432\u0430\u043d\u0438\u0439 \u0432\u0438\u043d\u044f\u0442\u043e\u043a: " + e.getMessage());
            System.out.println("\u0421\u0442\u0430\u0442\u0443\u0441 \u0432\u0430\u043d\u0442\u0430\u0436\u0443 \u0437\u0430\u043b\u0438\u0448\u0438\u0432\u0441\u044f \u0431\u0435\u0437\u043f\u0435\u0447\u043d\u0438\u043c: " + shipment1.getStatus() + "\n");
        }

        System.out.println("=== 5. \u041f\u043e\u0440\u0456\u0432\u043d\u044f\u043d\u043d\u044f \u043e\u0431'\u0454\u043a\u0442\u0456\u0432: ==, equals, hashCode ===");
        Client client1Copy = Client.of("12345678", "Nova Global Alternative Name", "+380000000000");
        Client client2 = Client.of("87654321", "Another LLC", "+380509998877");

        System.out.println("client1 == client1Copy: " + (client1 == client1Copy) + " (\u0440\u0456\u0437\u043d\u0456 \u043f\u043e\u0441\u0438\u043b\u0430\u043d\u043d\u044f \u0443 \u043f\u0430\u043c'\u044f\u0442\u0456)");
        System.out.println("client1.equals(client1Copy): " + client1.equals(client1Copy) + " (\u043e\u0434\u043d\u0430\u043a\u043e\u0432\u0438\u0439 taxId 12345678)");
        System.out.println("client1.hashCode() == client1Copy.hashCode(): " + (client1.hashCode() == client1Copy.hashCode()));
        System.out.println("client1.equals(client2): " + client1.equals(client2) + " (\u0440\u0456\u0437\u043d\u0456 taxId)\n");

        System.out.println("=== 6. \u0412\u0438\u043a\u043b\u0438\u043a \u043e\u0431\u0447\u0438\u0441\u043b\u044e\u0432\u0430\u043d\u0438\u0445 \u043c\u0435\u0442\u043e\u0434\u0456\u0432 ===");
        long days = DeliveryUtils.transitDays(waybill1);
        double load = DeliveryUtils.loadPercent(waybill1);
        System.out.println("\u0422\u0440\u0438\u0432\u0430\u043b\u0456\u0441\u0442\u044c \u043f\u0435\u0440\u0435\u0432\u0435\u0437\u0435\u043d\u043d\u044f: " + days + " \u0434\u043d.");
        System.out.printf("\u0417\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f \u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0456\u043b\u044f (4500 \u043a\u0433 / 20000 \u043a\u0433): %.2f%%\n\n", load);

        System.out.println("=== 7. \u0412\u0438\u0432\u0456\u0434 \u0443\u0441\u0456\u0445 \u0441\u0443\u0442\u043d\u043e\u0441\u0442\u0435\u0439 \u0432\u0430\u0440\u0456\u0430\u043d\u0442\u0430 \u0447\u0435\u0440\u0435\u0437 toString() ===");
        System.out.println(client1);
        System.out.println(truck1);
        System.out.println(shipment1);
        System.out.println(waybill1);
        System.out.println();

        // 8. \u0417\u0430\u043a\u043e\u043c\u0435\u043d\u0442\u043e\u0432\u0430\u043d\u0438\u0439 \u0440\u044f\u0434\u043e\u043a, \u044f\u043a\u0438\u0439 \u043d\u0435 \u043a\u043e\u043c\u043f\u0456\u043b\u044e\u0454\u0442\u044c\u0441\u044f \u0447\u0435\u0440\u0435\u0437 \u043e\u0431\u043c\u0435\u0436\u0435\u043d\u043d\u044f \u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u0456:
        // ua.delivery.util.ValidationHelper.requireNotNull(client1, "test");
    }
}