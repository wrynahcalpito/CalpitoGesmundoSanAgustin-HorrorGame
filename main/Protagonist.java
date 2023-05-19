package main;

/**
 * A subclass of Character that deals with the user gameplay. 
 * An added variable is an skillLevel, inventory array, gallery array, and itemInUse. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */
public class Protagonist extends Character {
    /** Skill level of the user in the game
     */ private int skillLevel;
    /** Inventory of items of the user
     */ private Item inventory[] = {null, null, null, null, null, null, null, null, null};
    /** Gallery of the user in the game
     */ private String gallery[] = {null, null, null};
   /** The item that the user is currently using
    */ private Item itemInUse;

    /**
     * Initializes the item with parameters and the skill level at 1 for the beginning
     * @param name 
     */
    public Protagonist(String name) {
        super(name, "Protagonist");
        skillLevel = 1;
    }

    /** 
     * Gets the inventory of the Protagonist
     * @return inventory array
     */
    public Item[] getInventory() {
        return inventory;
    }
    /**
     * Gets the gallery of the protagonist
     * @return gallery array
     */
    public String[] getGallery() {
        return gallery;
    }
    /**
     * Gets the skill level of the Protagonist
     * @return skillLevel
     */
    public int getSkillLevel() {
        return skillLevel;
    }
    
    /**
     * Gets the item that the Protagonist is currently using
     * @return itemInUse
     */
    public Item returnItem() {
        return itemInUse;
    }
    
    /**
     * Increases skillLevel variable by 1 every time the method is called
     */
    public void skillLevel() {
        skillLevel += 1;
    }
    
    /**
     * Inspects items and NPCs by calling the Interactive interact() method
     * @param i Item that is inspected or interacted with by the Protagonist
     */
    public void inspect(Interactive i) {
        i.interact();
    }
    
    /**
     * Removes the item from the inventory array
     * @param i 
     */
    public void removeItem(int i) {
        inventory[i] = null;
    }
    /**
     * Gets the item which is added to the inventory array
     * @param i Item to be added to the inventory
     */
    public void getItem(Item i) {
        for(int j = 0; j < 9; j++) {
            if(inventory[j] == null) {
                inventory[j] = i;
                break;
            }
            else {
                //call exception
            }
        }
    }
    
    /**
     * Sets i as itemInUse
     * @param i Item that is currently being used
     */
    public void equipItem(Item i) {
        itemInUse = i;
    }
    
    /**
     * Takes a photo which is added to the gallery array
     * @param photo Image taken and to be displayed in the gallery
     */
    public void takePhoto(String photo) {
        //code here on taking a photo in the game + animations
        for(int i = 0; i < 9; i++) {
            if(gallery[i] == null) {
                gallery[i] = photo;
                break;
            }
        }
    }
}
