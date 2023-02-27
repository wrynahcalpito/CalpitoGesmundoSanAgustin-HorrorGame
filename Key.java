package main;

public class Key extends Item {
    protected String compatibleDoor;
    
    public Key(String identifier, String type, String desc, String compatibleDoor) {
        super(identifier, "key", desc, "AKeyModel");
        this.compatibleDoor = compatibleDoor;
    }

    //GETTER METHODS
    public String getCompatibleDoor() {
        return compatibleDoor;
    }

    //OPERATION METHODS
    public void openDoor(Item x) {
        if(compatibleDoor.equals(x.getIdentifier())) {
            System.out.println("Door has been opened.");
        }
        else {
            System.out.println("Key is not compatible.");
        }
    }
}