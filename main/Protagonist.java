package main;

/**
 * A subclass of Character. An added variable is an skillLevel, inventory array, gallery array, and itemInUse. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */
public class Protagonist extends Character {
    private int skillLevel;
    private Item inventory[] = {null, null, null, null, null, null, null, null, null};
    private String gallery[] = {null, null, null};
    private Item itemInUse;

    /**
     * Initializes the item with parameters
     * @param name 
     * Initializes skill level at 1 for the beggining
     */
    public Protagonist(String name) {
        super(name, "Protagonist");
        skillLevel = 1;
    }

    /** 
     * @return inventory array
     */
    public Item[] getInventory() {
        return inventory;
    }
    /**
     * @return gallery array
     */
    public String[] getGallery() {
        return gallery;
    }
    /**
     * @return skillLevel
     */
    public int getSkillLevel() {
        return skillLevel;
    }
    
    /**
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
     * @param i 
     */
    public void inspect(Interactive i) {
        i.interact();
    }
    
    /**
     * Gets the item which is added to the inventory array
     * @param i 
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
     * @param i 
     */
    public void equipItem(Item i) {
        itemInUse = i;
    }
    
    /**
     * Takes a photo which is added to the gallery array
     * @param photo 
     */
    public void takePhoto(String photo) {
        //code here on taking a photo in the game + animations
        for(int i = 0; i < 9; i++) {
            if(gallery[i] == null) {
                gallery[i] = photo;
                break;
            }
            else {
                //call exception
            }
        }
    }
}
