package main;

public abstract class Character {
  protected String name, type;

  public Character(String name, String type) { 
    this.name = name;
    this.type = type;
  }

  //GETTER METHODS
  public String getName() {
    return name;
  }
  public String getType() {
    return type;
  }
  
  //OPERATION METHODS
  public void move() {
    System.out.println("The character has moved.");
  }
}