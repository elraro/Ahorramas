package eu.elraro.ahorramas;

public class Item {

    private int photo;
    private String name;
    private double price;
    private int quantity;
    private double valuation;
    private long id;

    public Item(int photo, String name, double price, int quantity, double valuation) {
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.valuation = valuation;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValuation() {
        return valuation;
    }

    public void setValuation(double valuation) {
        this.valuation = valuation;
    }

}
