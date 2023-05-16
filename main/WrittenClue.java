package main;

/**
 * A subclass of Item that deals with clues in the game. An added variable is clue. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */
public class WrittenClue extends Item {
    /** The riddle or any kind of clue that will be used in storage room stage to help the player in the hunt
     */ private String clue;
    
    /**
     * Initializes the key with parameters
     * @param identifier Acts as the name of the written clue
     * @param clue The content of the written clue
     */
    public WrittenClue(String identifier, String clue) {
        super(identifier, "writtenClue", null);
        this.clue = clue;
    }
  
    /** 
     * Gets the clue 
     * @return clue to be integrated in rectangular layout in main
     */
    public String printClue() {
        return clue;
    }
}
