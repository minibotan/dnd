package storygen.entity;

import java.util.ArrayList;
import java.util.List;

public class Need {
    public String label;
    private String consequenceDescription;

    public List<Action> solutions = new ArrayList<>();

    public Need(String label) {
        this.label = label;
    }

    public Need(String label, String consequenceDescription) {
        this.label = label;
        this.consequenceDescription = consequenceDescription;
    }

    public void solvableBy(Action solution) {
        solutions.add(solution);
        solution.solvableNeeds.add(this);
    }

    public String getConsequenceDescription(Subject subject) {
        return String.format(consequenceDescription, subject.name);
    }

    @Override
    public String toString() {
        return label;
    }
}
