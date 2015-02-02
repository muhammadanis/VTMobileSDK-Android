package veritrans.co.id.mobile.sdk.entity;

/**
 * Created by muhammadanis on 1/29/15.
 */
public class VTProduct {
    private String id;
    private String name;
    private boolean limitless;
    private int price;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLimitless() {
        return limitless;
    }

    public void setLimitless(boolean limitless) {
        this.limitless = limitless;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, limitless: %s, price: %d, quantity: %d",getId(),getName(),Boolean.toString(isLimitless()),getPrice(),getQuantity());
    }
}
