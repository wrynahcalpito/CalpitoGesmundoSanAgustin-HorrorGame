package main;
import java.util.ArrayList;

/**
 * Items include Keys and WrittenClues that exist in the game. 
 * Each Item has an identifier, type, description, and appearance. Items are interactive.
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */

public class Item {
    /** Functions as a name, unique to the item
     */ protected String identifier;
    /** Used to reference to a kind of item
     */ protected String type;
    /** Image file name for the appearance of the item
     */ protected String appearance; 
    /** List of all items
     */ private static ArrayList<Item> itemList = new ArrayList();
    
    /** Initializes the item with parameters
     * @param identifier Acts as the name of the Item
     * @param type Used to reference for several items
     * @param appearance Image file name and location
     * Adds the item when declared to the itemList
     */
    public Item(String identifier, String type, String appearance) {
        this.identifier = identifier;
        this.type = type;
        this.appearance = appearance;
        itemList.add(this);
    }

    /** 
     * Gets the identifier of the item
     * @return identifier
     */
    public String getIdentifier() {
        return identifier;
    }
    /**
     * Gets the type of the item
     * @return type
     */
    public String getType() {
        return type;
    }
    /** 
     * Gets the file name and location of the image for the item
     * @return appearannce
     */
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
