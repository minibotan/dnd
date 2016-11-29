package storygen.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subject {
    private Random randomGenerator = new Random();

    public String name;

    List<Need> needs = new ArrayList<>();
    List<Item> inventory = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
    }

    public void addNeed(Need need) {
        if (!needs.contains(need)) {
            this.needs.add(need);
        }
    }

    public void solveNeed(Need need) {
        if (needs.contains(need)) {
            needs.remove(need);
        }
    }

    public void giveItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(String itemName) throws Exception {
        for (Item item : inventory) {
            if (item.name.equals(itemName)) {
                inventory.remove(item);
                return;
            }
        }
        throw new Exception(name + " does not have item \"" + itemName + "\"");
    }

    public boolean hasNeeds() {
        return needs.size() > 0;
    }

    public List<Need> getNeeds() {
        return needs;
    }

    public Need getRandomNeed() {
        return needs.get(randomGenerator.nextInt(needs.size()));
    }

    public boolean canPerform(Action action) {
        return inventory.containsAll(action.cost) && inventory.containsAll(action.requirements);
    }

    public void perform(Action action, Item desirableProduct) {
        inventory.removeAll(action.cost);
        needs.removeAll(action.solvableNeeds);
        inventory.add(desirableProduct);
        needs.addAll(action.consequences);
    }

    public Item getMissingItemFor(Action action) {
        for (Item item : action.cost) {
            if (!inventory.contains(item)) {
                return item;
            }
        }
        return null;
    }
}
