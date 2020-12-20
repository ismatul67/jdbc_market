package org.example.Entity;

public class Unit {
    private int id;
    private String code;
    private String description;

    public Unit(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Unit(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }


    public int getId() {
         return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
