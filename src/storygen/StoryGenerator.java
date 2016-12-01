package storygen;

import storygen.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StoryGenerator {
    private Random randomGenerator = new Random();

    private Map<LocationType, Integer> locations;

    private List<Need> initialNeeds = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Item> requiredItems = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    private boolean despair = false;

    public StoryGenerator(Map<LocationType, Integer> locations) {
        this.locations = locations;

        Need beerNeed = new Need("drink a beer", "%1$s wants to have a beer.");
        beerNeed.consequenceCanHappenIn(LocationType.TAVERN);
        initialNeeds.add(beerNeed);

        Need treasureHideNeed = new Need ("hide a treasure", "%1$s finds a treasure.");
        treasureHideNeed.consequenceCanHappenIn(LocationType.CASTLE);
        treasureHideNeed.consequenceCanHappenIn(LocationType.DUNGEON);
        treasureHideNeed.consequenceCanHappenIn(LocationType.GRAVEYARD);
        treasureHideNeed.consequenceCanHappenIn(LocationType.TEMPLE);
        initialNeeds.add(treasureHideNeed);

        Need beRichNeed = new Need ("be rich", "%1$s understands how good the money are.");
        beRichNeed.consequenceCanHappenIn(LocationType.HOUSE);
        initialNeeds.add(beRichNeed);

        Need beKilledNeed = new Need("be dealt with", "%1$s becomes dangerous for the people around.");
        beKilledNeed.consequenceCanHappenIn(LocationType.HOUSE);
        beKilledNeed.consequenceCanHappenIn(LocationType.GRAVEYARD);
        beKilledNeed.consequenceCanHappenIn(LocationType.TAVERN);

        Need saveALotOfMoney = new Need("save a lot of money", "%1$s becomes rich.");
        saveALotOfMoney.consequenceCanHappenIn(LocationType.HOUSE);

        Need fightNeed = new Need("fight", "%1$s feels strong and big.");

        Need createACoolElixir = new Need("create a magnificent elixir", "%1$s have a great idea for alchemy.");

        Need healNeed = new Need("heal", "%1$s is injured now.");
        healNeed.consequenceCanHappenIn(LocationType.SHOP);

        Need escapePrison = new Need("escape prison", "%1$s is caught and thrown into a cell.");
        escapePrison.consequenceCanHappenIn(LocationType.CASTLE);

        Need escapeDungeon = new Need("escape dungeon", "%1$s in thrown into a dungeon with monsters and traps.");
        escapeDungeon.consequenceCanHappenIn(LocationType.DUNGEON);

        Need compensateDamage = new Need("compensate damage", "%1$s breaks many things and the owner is mad.");

        Need beBuried = new Need("be alive but he cannot", "%1$s is dead.");

        Need createAMapNeed = new Need("create a map for this", "%1$s needs a map to guide here.");

        Need hideMap = new Need("hide the map", "%1$s wants the map to be a secret.");
        hideMap.consequenceCanHappenIn(LocationType.CASTLE);
        hideMap.consequenceCanHappenIn(LocationType.DUNGEON);
        hideMap.consequenceCanHappenIn(LocationType.HOUSE);

        Need beRevengedNeed = new Need("be revenged", "%1$s gets killed by a murderer.");

        Need beBanishedNeed = new Need("be banished from this world", "%1$s becomes a ghost.");

        Need beRescuedNeed = new Need("be rescued", "%1$s is trapped.");

        Need surviveAPlague = new Need("survive a plague", "This results in a dreadful plague.");

        Need suppressCompetition = new Need("suppress competitors", "%1$s becomes so good that he dominates on the market.");

        Need beImmortal = new Need("become immortal", "%1$s is very afraid of death and decides to do something about it.");
        beImmortal.consequenceCanHappenIn(LocationType.HOUSE);
        initialNeeds.add(beImmortal);

        Action excelInAlchemy = new Action("excel in alchemy", "excels in alchemy");
        excelInAlchemy.solves(createACoolElixir);
        excelInAlchemy.solves(beImmortal);
        excelInAlchemy.resultsIn(surviveAPlague);

        excelInAlchemy.anotherSetOfConsequences();
        excelInAlchemy.resultsIn(suppressCompetition);
        actions.add(excelInAlchemy);

        Action workALot = new Action("work a lot", "works a lot");
        workALot.solves(saveALotOfMoney);
        workALot.resultsIn(beBuried);
        actions.add(workALot);

        Action inheritMoney = new Action("inheritMoney", "inherits a lot of money");
        inheritMoney.solves(saveALotOfMoney);
        inheritMoney.resultsIn(beBuried);
        actions.add(inheritMoney);

        Action becomeBandit = new Action("become a bandit", "becomes a bandit");
        becomeBandit.solves(beRichNeed);
        becomeBandit.resultsIn(beKilledNeed);
        actions.add(becomeBandit);

        Action drinkBeer = new Action("drink beer", "drinks a beer");
        drinkBeer.solves(beerNeed);
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

        Action hideInATemple = new Action("hide it", "hides it in a secret place");
        hideInATemple.solves(treasureHideNeed);
        hideInATemple.resultsIn(beBuried);

        hideInATemple.anotherSetOfConsequences();
        hideInATemple.resultsIn(createAMapNeed);

        hideInATemple.anotherSetOfConsequences();
        hideInATemple.resultsIn(beRevengedNeed);

        hideInATemple.anotherSetOfConsequences();
        hideInATemple.resultsIn(beBanishedNeed);

        actions.add(hideInATemple);

        Action tryToEscape = new Action("escape", "looks for a way out");
        tryToEscape.solves(escapeDungeon);
        tryToEscape.resultsIn(beRescuedNeed);

        tryToEscape.anotherSetOfConsequences();
        tryToEscape.resultsIn(treasureHideNeed);
        actions.add(tryToEscape);

        Action createAMap = new Action("create a map", "creates a detailed map of the place and its surroundings");
        createAMap.solves(createAMapNeed);
        createAMap.resultsIn(hideMap);
        actions.add(createAMap);
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

        sb.append(getPlaceDescription(need));
        if (solution == null) {
            //sb.append(subject.name).append(" has no ways of doing so. He despairs and cries.");
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

    private String getPlaceDescription(Need need) {
        if (!need.hasLocations()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        LocationType location = null;
        List<LocationType> possibleLocations = need.getPossibleLocations();
        List<Boolean> used = new ArrayList<>(possibleLocations.size());
        possibleLocations.forEach(l -> used.add(false));
        while (location == null) {
            if (possibleLocations.isEmpty()) {
                return "";
            }
            LocationType tmp = need.getRandomPossibleLocation();
            possibleLocations.remove(tmp);

            if (locations.containsKey(tmp) && locations.get(tmp) > 0) {
                location = tmp;
            }
        }

        sb.append("Location: ").append(location.toString()).append(" #").append(randomGenerator.nextInt(locations.get(location)) + 1).append(". ");

        return sb.toString();
    }

}
