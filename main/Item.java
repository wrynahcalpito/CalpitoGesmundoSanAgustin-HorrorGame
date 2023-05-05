package main;
/**
 * Items include Keys and WrittenClues. Each Item has an identifier, type, description,
 * and appearance. Items are interactive.
 * 
 * @author TRUTH
 */
import java.util.ArrayList;

public class Item {
    /**
     * identifier: functions as a name, unique to the item
     * type: used to reference to a kind of item
     * appearance: image file name for the appearance of the item
     */
    protected String identifier, type, appearance; //appearance is hardcoded
    private static ArrayList<Item> itemList = new ArrayList();
    
    /** Initializes the item with parameters
     * @param identifier
     * @param type
     * @param appearance 
     * Adds the item when declared to the itemList
     */
    public Item(String identifier, String type, String appearance) {
        this.identifier = identifier;
        this.type = type;
        this.appearance = appearance;
        itemList.add(this);
    }

    /** GETTER METHODS
     * @return values of variables 
     */
    public String getIdentifier() {
        return identifier;
    }
    public String getType() {
        return type;
    }
    public String getAppearance() {
        return appearance;
    }

    /**
     * Collates the information on the item declared
     * @return information
     */
    public String interact() {
        String information = "Item: " + identifier + "%nType: " + type + "%n";
        return information;
    }
}
