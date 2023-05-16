package main;

/**
 * Characters include Protagonists and NPCs. Each Character has a name, type, health, 
 * energy, sanity, and hunger.
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */

public abstract class Character {
    /**
     * name: which identifies the character 
     * type: clusters characters in one kind 
     */
    protected String name, type;
    /**
     * health: health of the character
     * maxHealth: maximum value of health variable, set at 100
     * energy: the amount of energy left in the character
     * maxEnergy: maximum value of energy variable, set to 120
     * sanity: sanity of the character
     * maxSanity: maximum value of sanity variable, set to 100
     * hunger: hunger of the character
     * maxHunger: maximum value of hunger, set to 100
     */
    protected int health, maxHealth = 100, energy, maxEnergy = 120, sanity, maxSanity = 100, hunger, maxHunger = 100;           
        
    /**
     * Initializes a character with parameters. 
     * Health, energy, sanity, and hunger are set to maximum value. 
     * @param name
     * @param type 
     */
    public Character(String name, String type) {
        this.name = name;
        this.type = type;
        health = 100;
        energy = 120;
        sanity = 100;
        hunger = 100;
    }
    
    /** GETTER METHODS
     * @return values of variables 
     */
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
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
  
    /** 
     * Sets a new name for the character
     * @param n: new name for character
     */
    public void setName(String n) {
        name = n;
    }
    
    /**
     * Decreases health variable by 1 every time the method is called
     */
    public void health() {
        health -= 1;
    }
    /**
     * @param atk increases the amount of decrease of the variable by its value
     */
    public void health(int atk) {
        health -= 1*atk;
    }
    
    /**
     * Decreases sanity variable by 1 every time the method is called
     */
    public void sanity() {
        sanity -= 1;
    }
    
    /**
     * Decreases energy variable by 1 every time the method is called
     */
    public void energy() {
        energy -= 1;
    }
    
    /**
     * Decreases hunger variable by 1 every time the method is called
     */
    public void hunger() {
        hunger -= 1;
    }
}
