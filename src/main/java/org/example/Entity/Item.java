package org.example.Entity;

public class Item {
    private int id;
    private String name;
    private int price;
    private int unitId;

    public Item(String name, int price, int unitId) {
        this.name = name;
        this.price = price;
        this.unitId = unitId;
    }

    public Item(int id, String name, int price, int unitId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unitId = unitId;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", price=" + price +
                ", unit_id=" + unitId +
                '}';
    }
}
