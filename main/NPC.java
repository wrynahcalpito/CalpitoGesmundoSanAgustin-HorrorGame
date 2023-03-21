package main;

import java.util.*;

public class NPC extends Character {
    private String appearance; 
    private ArrayList<String> dialog = new ArrayList(); //the dialog will be referenced by the index, it will be printed when approrpiate with the decision of the user
  
    public NPC(String name, String appearance, String d) {
        super(name, "NPC"); 
        this.appearance = appearance;
        dialog.add(d);
    }

    //OPERATION METHODS
    public String getAppearance() {
        return appearance;
    }
    
    public void interact() {
        System.out.println(dialog);
    }
    
    public void addString(String d) {
        dialog.add(d);
    }
    
    public String printDialog(int index){
        return dialog.get(index);
    }

    public void scare() {
        System.out.println("The NPC moved quickly toward the screen. It gets bigger and bigger, and changes its appearance.");
    }
}