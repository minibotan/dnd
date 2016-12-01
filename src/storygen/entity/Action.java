package storygen.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Action {
    private Random randomGenerator = new Random();

    public String label;
    public String actionDescription;

    List<Need> solvableNeeds = new ArrayList<>();

    List<Item> requirements = new ArrayList<>();
    List<Item> cost = new ArrayList<>();
    List<Item> products = new ArrayList<>();
    List<List<Need>> consequences = new ArrayList<>();

    private int currentConsequencesSet = 0;

    public Action(String label, String actionDescription) {
        this.label = label;
        this.actionDescription = actionDescription;
        consequences.add(new ArrayList<>());
    }

    public void solves(Need need) {
        solvableNeeds.add(need);
        need.solutions.add(this);
    }

    public void requires(Item item) {
        requirements.add(item);
    }

    public void costs(Item item) {
        cost.add(item);
    }

    public void produces(Item item) {
        products.add(item);
    }

    public void resultsIn(Need need) {
        consequences.get(currentConsequencesSet).add(need);
    }

    public void anotherSetOfConsequences() {
        currentConsequencesSet++;
        consequences.add(new ArrayList<>());
    }

    public boolean canSolve(Need need) {
        return solvableNeeds.contains(need);
    }

    public boolean canProduce(Item item) {
        return products.contains(item);
    }

    public boolean costsAnything() {
        return cost.size() > 0;
    }

    public boolean producesAnything() {
        return products.size() > 0;
    }

    public boolean hasConsequences() {
        return consequences.size() > 0;
    }

    public String productsDescription() {
        return getNarrativeEnum(products);
    }

    public String costsDescription() {
        return getNarrativeEnum(cost);
    }

    public static String consequencesDescription(Subject subject, List<Need> needs) {
        StringBuilder sb = new StringBuilder();
        for (Need need : needs) {
            sb.append(need.getConsequenceDescription(subject)).append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return label;
    }

    private String getNarrativeEnum(List<Item> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i).toString());
            if (i < size - 2) {
                sb.append(", ");
            } else if (i == size - 2) {
                sb.append(" and ");
            }
        }
        return sb.toString();
    }

    public String getActionDescription(Item desirableProduct) {
        String s = actionDescription;
        s = s.replace("{cost}", costsDescription());
        if (desirableProduct != null) {
            s = s.replace("{product}", desirableProduct.toString());
        }
        return s;
    }

    public List<Need> getRandomConsequencesSet() {
        return consequences.get(randomGenerator.nextInt(consequences.size()));
    }
}
