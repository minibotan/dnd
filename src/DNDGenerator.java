import MapGenerator.MapMaker;
import storygen.StoryGenerator;
import storygen.entity.LocationType;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DNDGenerator {
    public static void main(String[] args) {
        try {
            long time = System.currentTimeMillis();
            String foldName = "Results/" + time;
            File d = new File(foldName);
            d.mkdir();

            new MapMaker(time);

            Map<LocationType, Integer> locations = new EnumMap<>(LocationType.class);
            List<Integer> locationsCountUtil = new ArrayList<>(1);
            locationsCountUtil.add(0);
            try {
                Files.lines(Paths.get(foldName + "locations.dnd")).forEach(s -> {
                    String[] parts = s.split(":");
                    locationsCountUtil.set(0, locationsCountUtil.get(0) + Integer.valueOf(parts[1]));
                    locations.put(LocationType.valueOf(parts[0].toUpperCase()), Integer.valueOf(parts[1]));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            int locationsCount = locationsCountUtil.get(0);

            StoryGenerator generator = new StoryGenerator(locations);
            StringBuilder stories = new StringBuilder();

            for (int i = 0; i < Math.min(Math.round(locationsCount / Math.log(locationsCount)), 30); i++) {
                stories.append(generator.generate()).append("\n\n");
            }

            PrintWriter writer = new PrintWriter(foldName + "/quests_base.txt", "UTF-8");
            writer.println(stories.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
