package ua.delivery.model;

import ua.delivery.util.DeliveryUtils;

public record Client(String taxId, String companyName, String contactPhone) {

    public Client {
        taxId = DeliveryUtils.cleanText(taxId, "taxId");
        companyName = DeliveryUtils.cleanText(companyName, "companyName");
        contactPhone = DeliveryUtils.cleanText(contactPhone, "contactPhone");
    }

    public static Client of(String taxId, String companyName, String contactPhone) {
        return new Client(taxId, companyName, contactPhone);
    }
}