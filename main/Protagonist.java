package main;

public class Protagonist extends Character {
    private int skillLevel;
    private Item inventory[] = {null, null, null, null, null, null, null, null, null};
    private String gallery[] = {null, null, null};
    private Item itemInUse;

    public Protagonist(String name) {
        super(name, "Protagonist");
        skillLevel = 1;
    }

    //GETTER METHODS
    public Item[] getInventory() {
        return inventory;
    }
    public String[] getGallery() {
        return gallery;
    }
    public int getSkillLevel() {
        return skillLevel;
    }
    
    //OPERATION METHODS
    public void skillLevel() {
        skillLevel += 1;
    }
    public void inspect(Interactive i) {
        i.interact();
    }

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

    public void equipItem(Item i) {
        itemInUse = i;
    }
    
    public Item returnItem() {
        return itemInUse;
    }

    public void useItem() {
        System.out.printf("%s used item.%n", name);
    }

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
