package eu.elraro.ahorramas;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

    private int photo;
    private String name;
    private double price;
    private int quantity;
    private long id;

    public Item(int photo, String name, double price, int quantity) {
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.photo);
        dest.writeString(this.name);
        dest.writeDouble(this.price);
        dest.writeInt(this.quantity);
        dest.writeLong(this.id);
    }

    private Item(Parcel in) {
        this.photo = in.readInt();
        this.name = in.readString();
        this.price = in.readDouble();
        this.quantity = in.readInt();
        this.id = in.readLong();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

}
