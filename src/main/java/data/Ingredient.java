package data;

import java.io.Serializable;

public class Ingredient implements Serializable {
    static final long serialVersionUID = 1L;
    private String name;
    private String quantity;

    public Ingredient() {
    }

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + "- " + quantity;
    }
}
