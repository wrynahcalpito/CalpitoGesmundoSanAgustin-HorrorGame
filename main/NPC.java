package main;

import java.util.*;

public class NPC extends Character {
  private String appearance; //appearance being a string is TEMPORARY (will develop proper models)
  private static ArrayList<String> dialog = new ArrayList(); //the dialog will be referenced by the index, it will be printed when approrpiate with the decision of the user
  
  public NPC(String name, String appearance, String d) {
    super(name, "NPC"); //appearance is hardcoded in NPCs
    this.appearance = appearance;
    dialog.add(d);
  }

  //OPERATION METHODS
  public String getAppearance() {
    return appearance;
  }
  public static int getDialogArrLength() {
    return dialog.size();
  }
  public void addDialog(String d) {
    dialog.add(d);
  }
  public String printDialog(int index){
    return dialog.get(index);
  }
}
