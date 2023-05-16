package main;

/**
 * Characters include Protagonists and NPCs and is the general class for declaring characters in the game. 
 * Each Character has a name, type, health, energy, sanity, and hunger.
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */

public abstract class Character {
    /*** Identifies the character 
     */ protected String name;
    /*** Clusters characters in one kind 
     */ protected String type;
    /*** Health of the character
     */ protected int health;
    /*** Maximum value of health variable, set at 100
     */ protected int maxHealth = 100;
    /*** The amount of energy left in the character
     */ protected int energy;
    /*** Maximum value of energy variable, set to 120
     */ protected int maxEnergy = 120;
    /*** Sanity of the character
     */ protected int sanity;
    /*** Maximum value of sanity variable, set to 100
     */ protected int maxSanity = 100;
    /*** Hunger of the character
     */ protected int hunger;
    /*** Maximum value of hunger, set to 100
     */ protected int maxHunger = 100;           
        
    /**
     * Initializes a character with parameters. 
     * Health, energy, sanity, and hunger are set to maximum value. 
     * @param name name of the Character
     * @param type refers to multiple characters
     */
    public Character(String name, String type) {
        this.name = name;
        this.type = type;
        health = 100;
        energy = 120;
        sanity = 100;
        hunger = 100;
    }
    
    /**
     * Gets the name of the Character
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the type of the character
     * @return type
     */
    public String getType() {
        return type;
    }
    
    /** 
     * Gets the health of the character
     * @return health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Gets the energy of the character 
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }
    
    /**
     * Gets the sanity of the character
     * @return sanity
     */
    public int getSanity() {
        return sanity;
    }
    
    /**
     * Gets the hunger of the character
     * @return hunger
     */
    public int getHunger() {
        return hunger;
    } 
    
    /** 
     * Gets the maximum health value
     * @return maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Gets the maximum energy value
     * @return maxEnergy
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }
    
    /**
     * Gets the maximum sanity value
     * @return maxSanity
     */
    public int getMaxSanity() {
        return maxSanity;
    }
    
    /** 
     * Gets the maximum hunger value
     * @return maxHunger
     */
    public int getMaxHunger() {
        return maxHunger;
    }
  
    /** 
     * Sets a new name for the character
     * @param n New name for character
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
     * @param atk Increases the amount of decrease of the variable by its value
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
