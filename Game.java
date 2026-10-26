import java.util.ArrayList;
import java.util.List;

/**
 * this is the main file that contains the game
 */

public class Game {
    /**
     * here its creating each of the items in the game and the effect they will have
     * on the geese
     * all items start as active but this can be changed when used
     * the values go as follows: name, image_name, xvalue, yvalue, gooseeffect type,
     * gooseeffect duration and gooseeffect radius.
     */
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        // creates the bread item that distracts the geese
        items.add(new Item("Bread", "bread.png", 10, 10, new GooseEffect(GooseEffect.Type.TAME, 300, 10)));
        // creates the food item
        items.add(new Item("Food", "food.png", 20, 20, new GooseEffect(GooseEffect.Type.TAME, 300, 10)));
        // creates the Potato item
        items.add(new Item("Oats", "oats.png", 30, 30, new GooseEffect(GooseEffect.Type.TAME, 300, 10)));
        // creates the Jacket item
        items.add(new Item("Jacket", "jacket.png", 40, 40, new GooseEffect(GooseEffect.Type.DISTRACT, 5, 10)));
        // creates the Waterbottle item
        items.add(
                new Item("waterbottle", "waterbottle.png", 50, 50, new GooseEffect(GooseEffect.Type.DISTRACT, 5, 10)));
        // creates the Football item
        items.add(new Item("Football", "football.png", 60, 60, new GooseEffect(GooseEffect.Type.DISTRACT, 5, 10)));
        // creates the GeeseToy item
        items.add(new Item("GeeseToy", "geesetoy.png", 70, 70, new GooseEffect(GooseEffect.Type.DISTRACT, 5, 10)));
        // creates the foghorn item
        items.add(new Item("Foghorn", "geesetoy.png", 80, 80, new GooseEffect(GooseEffect.Type.SCARE, 300, 10)));

        for (Item item : items) {
            System.out.println(item);
        }
    }
}