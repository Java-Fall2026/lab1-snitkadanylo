package ua.delivery.model;

import ua.common.BaseEntity;
import ua.delivery.util.DeliveryUtils;

import java.util.Objects;

public class Client extends BaseEntity {

    private final String taxId;
    private final String companyName;
    private final String contactPhone;

    private Client(String taxId, String companyName, String contactPhone) {
        super();
        this.taxId = DeliveryUtils.cleanText(taxId, "taxId");
        this.companyName = DeliveryUtils.cleanText(companyName, "companyName");
        this.contactPhone = DeliveryUtils.cleanText(contactPhone, "contactPhone");
    }

    public static Client of(String taxId, String companyName, String contactPhone) {
        return new Client(taxId, companyName, contactPhone);
    }

    public String getTaxId() {
        return taxId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(taxId, client.taxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "taxId='" + taxId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}