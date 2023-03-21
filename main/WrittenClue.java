package main;

public class WrittenClue extends Item {
    private String clue;
    
    public WrittenClue(String identifier, String type, String desc, String clue) {
        super(identifier, "writtenClue", desc, "APieceOfPaperWithString");
        this.clue = clue;
    }
    
    public String printClue() {
        return clue;
    }
}