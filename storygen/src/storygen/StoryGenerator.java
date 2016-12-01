package storygen;

import storygen.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoryGenerator {
    private Random randomGenerator = new Random();

    private List<Need> initialNeeds = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Item> requiredItems = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    private boolean despair = false;

    public StoryGenerator() {
        Need beerNeed = new Need("drink a beer", "%1$s wants to have a beer.");
        initialNeeds.add(beerNeed);

        Need fightNeed = new Need("fight", "%1$s feels strong and big.");

        Need healNeed = new Need("heal", "%1$s is injured now.");

        Need escapePrison = new Need("escape prison", "%1$s is caught and thrown into a cell.");

        Need escapeDungeon = new Need("escape dungeon", "%1$s in thrown into a dungeon with monsters and traps.");

        Need compensateDamage = new Need("compensate dmg", "%1$s breaks many things and the owner is mad.");

        Item beer = new Item("beer");

        Action drinkBeer = new Action("drink beer", "drinks a beer");
        drinkBeer.solves(beerNeed);
        drinkBeer.produces(beer);
        drinkBeer.resultsIn(fightNeed);
        actions.add(drinkBeer);

        Action fightInABar = new Action("fight in a bar", "fights some bulls in a bar");
        fightInABar.solves(fightNeed);
        fightInABar.resultsIn(healNeed);

        fightInABar.anotherSetOfConsequences();
        fightInABar.resultsIn(escapePrison);

        fightInABar.anotherSetOfConsequences();
        fightInABar.resultsIn(compensateDamage);
        actions.add(fightInABar);

        Action goToEvilHealer = new Action("evil haler", "goes to that healer he was told about");
        goToEvilHealer.solves(healNeed);
        goToEvilHealer.resultsIn(escapeDungeon);
        actions.add(goToEvilHealer);

        Action fightOnAStreet = new Action("fight on a street", "fights some bulls on a street");
        fightOnAStreet.solves(fightNeed);
        fightOnAStreet.resultsIn(escapePrison);
        actions.add(fightOnAStreet);


        /*Need thirst = new Need("drink", "%1$s is getting thirsty.");
        initialNeeds.add(thirst);

        Need fatigue = new Need("rest", "%1$s is tired.");
        Need escapeGuards = new Need("escape guards", "Guards is now after %1$s.");

        Need moneyNeed = new Need("be rich", "%1$s want to be richer.");
        initialNeeds.add(moneyNeed);

        Need gloryNeed = new Need("bathe in the glory", "%1$s starts to dream of being famous.");
        initialNeeds.add(gloryNeed);*/

        /*Item health = new Item("health");
        requiredItems.add(health);

        Item food = new Item("food");
        items.add(food);

        Item beverage = new Item("beverage");
        items.add(beverage);

        Item money = new Item("money");
        items.add(money);*/

        /*Action eat = new Action("eat", "eats {cost}");
        eat.solves(hunger);
        eat.costs(food);
        actions.add(eat);

        Action drink = new Action("drink", "drinks {cost}");
        drink.solves(thirst);
        drink.costs(beverage);
        actions.add(drink);

        Action sleep = new Action("sleep", "sleeps");
        sleep.solves(fatigue);
        actions.add(sleep);

        Action theft = new Action("steal", "steals {product}");
        theft.produces(money);
        theft.resultsIn(escapeGuards);
        actions.add(theft);

        Action work = new Action("work", "works and earns money");
        work.solves(moneyNeed);
        work.produces(money);
        work.resultsIn(fatigue);
        actions.add(work);

        Action buy = new Action("buy", "buys {product}");
        buy.costs(money);
        buy.produces(food);
        buy.produces(beverage);
        actions.add(buy);

        Action hide = new Action("hide", "hides");
        hide.solves(escapeGuards);
        actions.add(hide);

        Action dragonFight = new Action("fight", "fights a dragon");
        dragonFight.resultsIn(fatigue);
        dragonFight.costs(health);
        dragonFight.solves(gloryNeed);
        dragonFight.solves(moneyNeed);
        dragonFight.produces(money);
        dragonFight.resultsIn(gloryNeed);
        actions.add(dragonFight);

        Action flyToMoon = new Action("fly to the Moon", "flies to the Moon");
        flyToMoon.solves(gloryNeed);
        flyToMoon.costs(money);
        flyToMoon.requires(health);
        actions.add(flyToMoon);*/
    }

    public String generate() {
        Subject johndoe = new Subject("John Doe");

        requiredItems.forEach(johndoe::giveItem);

        items.forEach((item) -> {
            if (Math.random() > 0.7) {
                johndoe.giveItem(item);
            }
        });

        johndoe.addNeed(initialNeeds.get(randomGenerator.nextInt(initialNeeds.size())));

        StringBuilder sb = new StringBuilder();
        while (johndoe.hasNeeds() && !despair) {
            sb.append(solveNeed(johndoe, johndoe.getRandomNeed()));
        }

        return sb.toString();
    }

    private String solveNeed(Subject subject, Need need) {
        StringBuilder sb = new StringBuilder();

        sb.append(subject.name).append(" wants to ").append(need.label).append(". ");

        Action solution = findSolution(need);

        if (solution == null) {
            sb.append(subject.name).append(" has no ways of doing so. He despairs and cries.");
            despair = true;
        } else {
            sb.append(tryPerformAction(subject, solution, null));
        }

        return sb.toString();
    }

    private Action findSolution(Need need) {
        Action solution = null;
        List<Boolean> used = new ArrayList<>(actions.size());
        actions.forEach(action -> used.add(false));
        while (solution == null) {
            int index = randomGenerator.nextInt(actions.size());
            while (used.get(index) && used.contains(false)) {
                index = randomGenerator.nextInt(actions.size());
            }
            if (used.get(index)) {
                return null;
            }
            used.set(index, true);
            Action tmp = actions.get(index);

            if (tmp.canSolve(need)) {
                solution = tmp;
            }
        }
        return solution;
    }

    private Action findSolutionForItem(Item item) {
        Action solution = null;
        List<Boolean> used = new ArrayList<>(actions.size());
        actions.forEach(action -> used.add(false));
        while (solution == null) {
            int index = randomGenerator.nextInt(actions.size());
            while (used.get(index) && used.contains(false)) {
                index = randomGenerator.nextInt(actions.size());
            }
            if (used.get(index)) {
                return null;
            }
            used.set(index, true);
            Action tmp = actions.get(index);

            if (tmp.canProduce(item)) {
                solution = tmp;
            }
        }
        return solution;
    }

    private String tryPerformAction(Subject subject, Action action, Item desirableProduct) {
        StringBuilder sb = new StringBuilder();

        if (subject.canPerform(action)) {
            if (action.costsAnything()) {
                sb.append(subject.name).append(" have ").append(action.costsDescription()).append(". ");
            }
        } else {
            if (action.costsAnything()) {
                sb.append(subject.name).append(" don't have ").append(action.costsDescription()).append(". ");
            }
        }

        while (!subject.canPerform(action)) {
            Item missingItem = subject.getMissingItemFor(action);

            Action solution = findSolutionForItem(missingItem);

            if (solution == null) {
                sb.append(subject.name).append(" has no ways of acquiring it. He despairs and cries.");
                despair = true;
                break;
            } else {
                sb.append(tryPerformAction(subject, solution, missingItem));
            }
        }

        if (subject.canPerform(action)) {
            List<Need> consequences = subject.perform(action, desirableProduct);
            sb.append(subject.name).append(" ").append(action.getActionDescription(desirableProduct)).append(". ");
            if (action.hasConsequences()) {
                sb.append(Action.consequencesDescription(subject, consequences));
            }
        }

        return sb.toString();
    }

}
