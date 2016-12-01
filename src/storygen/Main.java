package storygen;

public class Main {
    public static void main(String[] args) {
        StoryGenerator generator = new StoryGenerator();
        String story = generator.generate();
        System.out.println(story);
    }
}
