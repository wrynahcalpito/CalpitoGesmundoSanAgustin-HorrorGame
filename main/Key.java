package main;

/**
 * A subclass of Item, and the Key is the Item that the Protagonist will be using to progress through doors in the game.
 * An added variable is compatibleDoor. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */
public class Key extends Item {
    /*** The door that the key could open
     */ protected String compatibleDoor;
    
    /**
     * Initializes the key with parameters
     * @param identifier Functions as the name of the key
     * @param compatibleDoor The door that it can open
     */
    public Key(String identifier, String compatibleDoor) {
        super(identifier, "key", "AKeyModel");
        this.compatibleDoor = compatibleDoor;
    }
  
    /** 
     * Gets the door that the Key can open
     * @return compatibleDoor
     */
    public String getCompatibleDoor() {
        return compatibleDoor;
    }

    /**
     * Evaluates if compatibleDoor of the key is equivalent to x through its identifier
     * @param x Item that is trying to open the door
     */
    public void openDoor(Item x) {
        if(compatibleDoor.equals(x.getIdentifier())) {
            System.out.println("Door has been opened.");
        }
        else {
            System.out.println("Key is not compatible.");
        }
    }
}
