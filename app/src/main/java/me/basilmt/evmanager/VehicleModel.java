package me.basilmt.evmanager;

public class VehicleModel {
    private String name;
    private int v_class;

    public VehicleModel(String name, String v_class) {
        this.name = name;
        this.v_class = Integer.parseInt(v_class);
    }

    public String getName() {
        return name;
    }

    public int getV_class() {
        return v_class;
    }
}
