package main;
/**
 * Items include Keys and WrittenClues. Each Item has an identifier, type, description,
 * and appearance. Items are interactive.
 * 
 * @author TRUTH
 */
import java.util.ArrayList;

public class Item {
    protected String identifier, type, desc, appearance; //appearance is hardcoded
    private static ArrayList<Item> itemList = new ArrayList();
    
    public Item(String identifier, String type, String desc, String appearance) {
        this.identifier = identifier;
        this.type = type;
        this.desc = desc;
        this.appearance = appearance;
        itemList.add(this);
    }

    //GETTER METHODS
    public String getIdentifier() {
        return identifier;
    }
    public String getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    } 
    public String getAppearance() {
        return appearance;
    }

    //OPERATION METHODS
    public String interact() {
        String information = "Item: " + identifier + "%nType: " + type + "%nDescription: " + desc + "%n";
        return information;
    }
}
