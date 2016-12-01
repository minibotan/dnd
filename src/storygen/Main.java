package storygen;

import storygen.entity.LocationType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<LocationType, Integer> locations = new EnumMap<>(LocationType.class);
        try {
            Files.lines(Paths.get("locations.dnd")).forEach(s -> {
                String[] parts = s.split(":");
                locations.put(LocationType.valueOf(parts[0].toUpperCase()), Integer.valueOf(parts[1]));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        StoryGenerator generator = new StoryGenerator(locations);
        String story = generator.generate();
        System.out.println(story);
    }
}
