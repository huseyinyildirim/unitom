package org.buluton.Models;

public class ComboBox {
    private final int id;
    private final String name;

    public ComboBox(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}