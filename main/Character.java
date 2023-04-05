package main;

public abstract class Character {
  protected String name, type;
  protected int health, maxHealth = 100, energy, maxEnergy = 120, sanity, maxSanity = 100, hunger, maxHunger = 100; 

  public Character(String name, String type) { 
    this.name = name;
    this.type = type;
    health = 100;
    energy = 120;
    sanity = 100;
    hunger = 100;
  }

  //GETTER METHODS
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
  
    //OPERATION METHODS
    public void setName(String n) {
        name = n;
    }
    public void health() {
        health -= 1;
    }
    public void health(int atk) {
        health -= 1*atk;
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
}
