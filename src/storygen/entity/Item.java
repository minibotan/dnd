package storygen.entity;

public class Item {
    public String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
