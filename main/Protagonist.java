package main;

import main.Item;
import main.Interactive;
import main.Character;

public class Protagonist extends Character {
    private int health, maxHealth = 100, energy, maxEnergy = 120, sanity, maxSanity = 100, hunger, maxHunger = 100, skillLevel;
    private Item inventory[] = {null, null, null, null, null, null, null, null, null};
    private String gallery[] = {null, null, null};
    //removed appearance, as it would be useless (game will be fully played in 1st person POV)

    public Protagonist(String name) {
        super(name, "Protagonist");
        health = 100;
        energy = 120;
        sanity = 100;
        hunger = 100;
        skillLevel = 1;
    }

    //GETTER METHODS
    public Item[] getInventory() {
        return inventory;
    }
    public String[] getGallery() {
        return gallery;
    }
    public int getHealth() {
        return health;
    }
    public int getEnergy() {
        return energy;
    }
    public int getSanity() {
        return sanity;
    }
    public int getHunger() {
        return hunger;
    } 
    public int getSkillLevel() {
        return skillLevel;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }
    public int getMaxSanity() {
        return maxSanity;
    }
    public int getMaxHunger() {
        return maxHunger;
    }

    //OPERATION METHODS
    public void health() {
        health -= 1;
    }
    public void sanity() {
        sanity -= 1;
    }
    public void energy() {
        energy -= 1;
    }
    public void hunger() {
        hunger -= 1;
    }
    public void skillLevel() {
        skillLevel += 1;
    }
    
    public void inspect(Interactive i) {
        i.interact();
    }

    public void openInventory() {
        System.out.printf("%s opened the inventory.%n", name);
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

    public void equipItem() {
        System.out.printf("%s placed item in hand.%n", name);
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

    public void openGallery() {
        System.out.printf("%s opened the gallery.%n", name);
    }
}