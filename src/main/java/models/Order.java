package models;

import lombok.Data;

@Data
public class Order {
    int id;
    int petId;
    int quantity;
    String shipDate;
    String status;

    boolean complete;

    public enum orderStatus {
        placed,
        approved,
        delivered
    }

    @Override
    public String toString() {
        return "Order{ \n" +
                " id=" + id + ", \n" +
                " petId=" + petId + ", \n" +
                " quantity=" + quantity + ", \n" +
                " shipDate='" + shipDate + ", \n" +
                " status='" + status + ", \n" +
                " complete=" + complete + " \n" +
                '}';
    }

}
