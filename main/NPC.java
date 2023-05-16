package main;
import java.util.*;

/**
 * A subclass of Character. An added variable is an appearance and dialog array. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */
public class NPC extends Character {
    private String appearance; 
    private static ArrayList<String> dialog = new ArrayList(); 
  
    /**
     * Initializes an NPC with parameters. 
     * @param name the name of the NPC
     * @param appearance the file name and location of the appearance of the NPC
     * @param d adds a line of dialog to the array
     */
    public NPC(String name, String appearance, String d) {
        super(name, "NPC");
        this.appearance = appearance;
        dialog.add(d);
    }
    
    /** 
     * Gets the image file as the appearance of the NPC
     * @return appearance variable 
     */
    public String getAppearance() {
        return appearance;
    }
    
    /**
     * Gets the number of dialogs an NPC has
     * @return size of the dialog array
     */
    public static int getDialogArrLength() {
        return dialog.size();
    }
    
    /**
     * Adds a dialog to the list of the NPC
     * @param d adds a line of dialog to the array
     */
    public void addDialog(String d) {
        dialog.add(d);
    }
    
    /**
     * The dialog is referenced by the index and printed in the game 
     * @param index index of the dialog
     */
    public String printDialog(int index){
        return dialog.get(index);
    }
}
