package storygen.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Need {
    private Random randomGenerator = new Random();

    public String label;
    private String consequenceDescription;

    List<LocationType> locations = new ArrayList<>();
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

    public void consequenceCanHappenIn(LocationType location) {
        locations.add(location);
    }

    public LocationType getRandomPossibleLocation() {
        return locations.get(randomGenerator.nextInt(locations.size()));
    }

    public boolean hasLocations() {
        return locations.size() > 0;
    }

    public List<LocationType> getPossibleLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return label;
    }
}
