package main;

/**
 * A subclass of Item. An added variable is compatibleDoor. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */
public class Key extends Item {
    /**
     * compatibleDoor: the door that the key could open
     */
    protected String compatibleDoor;
    
    /**
     * Initializes the key with parameters
     * @param identifier functions as the name of the key
     * @param compatibleDoor the door that it can open
     */
    public Key(String identifier, String compatibleDoor) {
        super(identifier, "key", "AKeyModel");
        this.compatibleDoor = compatibleDoor;
    }
  
    /** GETTER METHODS
     * @return values of variables 
     */
    public String getCompatibleDoor() {
        return compatibleDoor;
    }

    /**
     * Evaluates if compatibleDoor of the key is equivalent to x through its identifier
     * @param x item that is trying to open the door
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
