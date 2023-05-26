package main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.control.ContentDisplay.GRAPHIC_ONLY;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main is the class where the environment of the game is set up. 
 * Main has the Instructions Display, Header Display, Bars Displays, LeftStats Display, RightStats Display, Gallery Display, Inventory Display, Interaction Displays, Intro Display, Game Display, Settings Display, Controls Display, Game Over Screen, and Ending Screen. 
 * 
 * @author TRUTH - CALPITO, GESMUNDO, SAN AGUSTIN
 */

public class Main extends Application {
    /** Initializes text for the game title displayed 
     */ private Text gameTitle = new Text();
    /** Initializes text for the introduction screen
     */ private Text introText = new Text();
    /** Variable for the introduction animation
     */ private final static int BLACKOUT_TIME_MS = 10;
    /** Checks if the inventory is returned 
     */ private static boolean inventoryBack = false;
    /** Checks if the gallery or settings is returned
     */ private static boolean otherBack = false;
    /** Checks if the start 
     */ private static boolean startbool = false; 
    /**
     */ private static boolean interactbool = false;
    /**
     */ private static boolean isIntro = false;
    /**
     */ private static boolean isStage1 = true, isStage2 = false, update = false;
    private String introMessage = "Where am I? Where am I! Last night, I was in my room...last night, everything was normal. But now, I can't move! I'm paralyzed—the world around me is paralyzed. But I can see a world so strange that I could not understand. It's dark; too dark for tonight.                                                                                                                                         ";
    private int currentLevel = 0, horizontalMovement = 250, verticalMovement = 250, entityResize = 100, zMovement = 100, xChange = 50, introMessageLength = introMessage.length(), currentCharIndex = 0, currentButcherRoom = 1, timer = 0;
    
    
    Protagonist user = new Protagonist("USER");
    BorderPane main = new BorderPane();
    Scene game = new Scene(main);
    VBox start = new VBox(10);
    GridPane inventoryGrid = new GridPane();
    FlowPane gameDisplay = new FlowPane();
    ImageView door, doorExit, npcView, itemView;
    Item doorKey = new Item("Door Key", "Key", "img/stage2/key.png");
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Last Night, Last Night");
            
        //INITIALIZING BORDER PANE (MAIN GAME LAYOUT)
        primaryStage.setScene(game);
        game.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setMaximized(true);
        
        //MAIN CODE
        Item camera = new Item("camera", "item", "img/camera.png"); 
        user.getItem(camera);
        
        //START SCREEN
        Image backgroundImage = new Image(Main.class.getResourceAsStream("img/startBG.png"));
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
                );
        Background bg = new Background(bgImage);
        main.setBackground(bg);
        
        Text title = new Text("Last Night, Last Night");
        title.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 200));
        title.setFill(Color.web("800000", 1.0));
        start.getChildren().add(title);  
        
        TextField name = new TextField("Enter Name");
        name.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 50));
        name.setBlendMode(BlendMode.SCREEN);
        name.getStyleClass().add("transparent");
        start.getChildren().add(name);
        
        Button confirmName = new Button("Confirm name?");
        confirmName.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 25));
        confirmName.setTextFill(Color.web("800000"));
        confirmName.setBlendMode(BlendMode.SCREEN);
        confirmName.getStyleClass().add("lightButton");
        confirmName.getStyleClass().add("transparent");
        start.getChildren().add(confirmName);
        
        confirmName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (name.getText().equals("Enter Name")) { user.setName("USER"); }
                else { user.setName(name.getText()); }
                
                if (startbool==false) {
                    Button startBtn = new Button("START");
                    startBtn.setFont(Font.loadFont(Main.this.getClass().getResourceAsStream("font/who-asks-satan.ttf"), 100));
                    startBtn.setTextFill(Color.web("800000"));
                    startBtn.setBlendMode(BlendMode.SCREEN);
                    startBtn.getStyleClass().add("lightButton");
                    startBtn.getStyleClass().add("transparent");
                    start.getChildren().add(startBtn);
                    startbool = true;
                    
                    startBtn.setOnAction((ActionEvent event1) -> {
                    main.setTop(HeaderDisplay());
                    main.setLeft(LeftStatsDisplay());
                    main.setRight(RightStatsDisplay());
                    main.setCenter(GameDisplay(currentButcherRoom));
                    main.setBottom(InventoryDisplay());
                    npcView.setImage(new Image(Main.class.getResourceAsStream("img/stage2/butcher.png")));
                    primaryStage.setMaximized(true);
        
                    if (isIntro) {
                        IntroDisplay(primaryStage);
                        primaryStage.setMaximized(true);
                    }
                });
                }     
            }
        });      
        
        Button manualBtn = new Button("Open Instruction Manual"); 
        manualBtn.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 25));
        manualBtn.setTextFill(Color.web("800000"));
        manualBtn.setBlendMode(BlendMode.SCREEN);
        manualBtn.getStyleClass().add("lightButton");
        manualBtn.getStyleClass().add("transparent");
        manualBtn.setOnAction((ActionEvent event1) -> {
            main.setCenter(InstructionsDisplay());
        });
        start.getChildren().add(manualBtn);  
        start.setAlignment(Pos.CENTER);
        main.setCenter(start);

        //MAIN KEY EVENTS
        main.setOnKeyPressed((KeyEvent ke) -> {
            switch (ke.getCode()) {
                case ESCAPE:
                    if(!otherBack) {
                        main.setCenter(SettingsDisplay());
                        otherBack = true;
                    }
                    else {
                        main.setCenter(GameDisplay(currentButcherRoom));
                        update = true;
                        otherBack = false;
                    }   break;
                case DIGIT0:
                    if(!otherBack) {
                        main.setCenter(GalleryDisplay());
                        otherBack = true;
                    }
                    else {
                        main.setCenter(GameDisplay(currentButcherRoom));
                        update = true;
                        otherBack = false;
                    }   break;
                case DIGIT1:
                    if(!inventoryBack) {
                        InHandShow(0);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT2:
                    if(!inventoryBack) {
                        InHandShow(1);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT3:
                    if(!inventoryBack) {
                        InHandShow(2);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT4:
                    if(!inventoryBack) {
                        InHandShow(3);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT5:
                    if(!inventoryBack) {
                        InHandShow(4);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT6:
                    if(!inventoryBack){
                        InHandShow(5);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT7:
                    if(!inventoryBack) {
                        InHandShow(6);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT8:
                    if(!inventoryBack) {
                        InHandShow(7);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case DIGIT9:
                    if(!inventoryBack) {
                        InHandShow(8);
                        inventoryBack = true;
                    }
                    else {
                        RemoveInHand();
                        inventoryBack = false;
                    }   break;
                case A:
                    horizontalMovement += xChange;
                    main.setCenter(GameDisplay(currentButcherRoom));
                    update = true;
                    break;
                case D:
                    horizontalMovement -= xChange;
                    main.setCenter(GameDisplay(currentButcherRoom));
                    update = true;
                    break;
                case W:
                    zMovement += xChange;
                    main.setCenter(GameDisplay(currentButcherRoom));
                    update = true;
                    break;
                case S:
                    zMovement -= xChange;
                    main.setCenter(GameDisplay(currentButcherRoom));
                    
                    update = true;
                    break;
                case F:
                    verticalMovement += 200;
                    main.setCenter(GameDisplay(currentButcherRoom));
                    break;
                case CONTROL:
                    xChange *= 4;
                    break;
                case SHIFT:
                    xChange *= 0.25;
                    break;
                default:
                    break;
            }
        });
        
        main.setOnKeyReleased((KeyEvent ke) -> {
            switch (ke.getCode()) {
                case F:
                    verticalMovement -= 200;
                    main.setCenter(GameDisplay(currentButcherRoom));
                    break;
                default:
                    break;
            }
        });
            
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);       
    }
    
    private Node InstructionsDisplay() {
        VBox manual = new VBox(5);
        manual.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);" +
                        "-fx-border-width: 5;" +
                        "-fx-border-insets: 5;" + 
                        "-fx-border-color: black;");
        manual.setOpacity(1);
        
        Text moveIt = new Text("How to move?");
        moveIt.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 65));
        manual.getChildren().addAll(moveIt, new Text("Use WASD to move forward, to the left, to the right, and backwards respectively."), new Text("Use F to jump, CTRL to sprint, and SHIFT to crouch."));
        
        Text interactIt = new Text("How to interact?");
        interactIt.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 65));
        manual.getChildren().addAll(interactIt, new Text("To access settings, press ESC. To access gallery, press 0 or click the icon by the inventory."), new Text("To interact with NPCs and items in the game, hover over the NPC or item and click on the appearing buttons."));
        
        Text equipIt = new Text("How to equip items?");
        equipIt.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 65));
        manual.getChildren().addAll(equipIt, new Text("Click on the inventory slots below. Pick the item you want to equip."), new Text("You can also use hotkeys (keys 1-9) to equip items. Click the number corresponding to the slot of the desired item."));
        
        Button backBtn = new Button("Back"); 
        backBtn.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 25));
        backBtn.setTextFill(Color.web("800000"));
        backBtn.getStyleClass().add("lightButton");
        backBtn.getStyleClass().add("transparent");
        backBtn.setOnAction((ActionEvent event1) -> {
            main.setCenter(start);
        });
        manual.getChildren().add(backBtn);
        
        startbool = false;
        
        return manual;
    }

    private Node HeaderDisplay() {
        FlowPane header = new FlowPane();
        
        header.setBackground(new Background(new BackgroundFill(Color.web("800000", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        header.setAlignment(Pos.TOP_CENTER);
        header.setPadding(new Insets(20, 50, 20, 50));
        header.setHgap(20);
        
        gameTitle = new Text("Last Night, Last Night");
        gameTitle.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 65));
        gameTitle.setFill(Color.web("f0f1f2", 1.0));
        header.getChildren().add(gameTitle);     
        
        return header;
    }
    
    private Node BarsDisplay(int statValue, int maxStatValue) {
        FlowPane bar = new FlowPane();
        
        Rectangle barIN = new Rectangle((statValue/maxStatValue)*300, 20.0, Color.RED);
        bar.getChildren().add(barIN);
        Rectangle barOUT = new Rectangle((maxStatValue - statValue)/maxStatValue, 20.0, Color.WHITE);
        bar.getChildren().add(barOUT);
        
        return bar;
    } 
    
    private Node LeftStatsDisplay() {
        VBox statsLeft = new VBox();
        
        Text healthStr = new Text("Health");
        healthStr.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 30));
        healthStr.setFill(Color.web("f0f1f2", 1.0));
        statsLeft.getChildren().add(healthStr);
        
        statsLeft.getChildren().add(BarsDisplay(user.getHealth(), user.getMaxHealth()));
        
        Text sanityStr = new Text("Sanity");
        sanityStr.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 30));
        sanityStr.setFill(Color.web("f0f1f2", 1.0));
        statsLeft.getChildren().add(sanityStr);
        
        statsLeft.getChildren().add(BarsDisplay(user.getSanity(), user.getMaxSanity()));
        
        return statsLeft;
    }

    private Node RightStatsDisplay() {
        VBox statsRight = new VBox();
        
        Text energyStr = new Text("Energy");
        energyStr.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 30));
        energyStr.setFill(Color.web("f0f1f2", 1.0));
        statsRight.getChildren().add(energyStr);
        
        statsRight.getChildren().add(BarsDisplay(user.getEnergy(), user.getMaxEnergy()));
        
        Text hungerStr = new Text("Hunger");
        hungerStr.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 30));
        hungerStr.setFill(Color.web("f0f1f2", 1.0));
        statsRight.getChildren().add(hungerStr);
        
        statsRight.getChildren().add(BarsDisplay(user.getHunger(), user.getMaxHunger()));
        
        return statsRight;
    }
    
    private void ApplyMovement(ImageView i) {
        if (zMovement >= 800) {
            zMovement = 800;
        }
        if (zMovement <= 50) {
            zMovement =  50;
        }
        
        i.setTranslateX(horizontalMovement);
        i.setTranslateY(verticalMovement);
        i.setFitHeight(entityResize);
        i.setPreserveRatio(true);
    }
    
    private Node NPCInteractionDisplay(ImageView img, NPC npc) {
        HBox NPCInteractions = new HBox(10); 
        
        Button talkBtn = new Button("TALK");
        Button attackBtn = new Button("ATTACK");
        NPCInteractions.getChildren().addAll(talkBtn, attackBtn);
        NPCInteractions.setTranslateX(horizontalMovement-200);
        NPCInteractions.setTranslateY(verticalMovement+400);
        
        talkBtn.setVisible(false);
        attackBtn.setVisible(false);

        img.hoverProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                talkBtn.setVisible(true);
                attackBtn.setVisible(true);
            } else {
                talkBtn.setVisible(false);
                attackBtn.setVisible(false);
            }
        });

        talkBtn.setOnAction(event -> {
            int i = 0;
            while (i < npc.getDialogArrLength()) {
                NPCInteractions.getChildren().add(new Text(npc.printDialog(i)));
                i++;
            }
        });

        attackBtn.setOnAction(event -> {
            npc.health(10);
        });
        
        return NPCInteractions;
    }
    
    private Node ItemInteractionDisplay(ImageView img, Item i, int x, int y) {
        HBox ITEMInteractions = new HBox(10);
        
        Button getBtn = new Button("GET ITEM");
        getBtn.getStyleClass().add("lightButton");
        ITEMInteractions.getChildren().addAll(getBtn);
        ITEMInteractions.setTranslateX(10+x);
        ITEMInteractions.setTranslateY(125+y);

        getBtn.setVisible(false);

        img.hoverProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                getBtn.setVisible(true);
            } else {
                //interactBtn.setVisible(false);
                //getBtn.setVisible(false);
            }
        });

        getBtn.setOnAction((ActionEvent event) -> {
            for (int t = 0; t<9; t++) {
                if (user.getInventory()[t] == i) {
                    user.removeItem(t);
                }
            }
            user.getItem(i);
            main.setBottom(InventoryDisplay());
        });

        return ITEMInteractions;
    }
    
    private Node BoxInteractionDisplay(ImageView box, int x, int y, boolean hasKey) {
        HBox BOXInteractions = new HBox(10);
        
        Button openBtn = new Button("OPEN BOX");
        openBtn.getStyleClass().add("lightButton");
        BOXInteractions.getChildren().addAll(openBtn);
        BOXInteractions.setTranslateX(10+x);
        BOXInteractions.setTranslateY(125+y);

        openBtn.setVisible(false);

        box.hoverProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                openBtn.setVisible(true);
            } else {
                //openBtn.setVisible(false);
            }
        });

        openBtn.setOnAction((ActionEvent event) -> {
            if(hasKey == true) {
                openBtn.setText("Key Found!");
                user.getItem(doorKey);
            }
            else {
                openBtn.setText("No Key Found");
            }
            main.setBottom(InventoryDisplay());
        });

        return BOXInteractions;
    }
    
    private Node DoorInteractionDisplay(ImageView img, int x, int y, int stage) {
        HBox DOORInteractions = new HBox(10);
        
        Button openBtn = new Button("OPEN DOOR WITH KEY");
        openBtn.getStyleClass().add("lightButton");
        DOORInteractions.getChildren().addAll(openBtn);
        DOORInteractions.setTranslateX(100+x);
        DOORInteractions.setTranslateY(250+y);
        
        openBtn.setVisible(false);

        img.hoverProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                if (user.returnItem() == doorKey) {  
                    openBtn.setVisible(true);
                }
            } 
        });

        openBtn.setOnAction(event -> {
            if (stage == 1) {
                //Move to Stage 2
                isStage1 = false;
                isStage2 = true;
                openBtn.setVisible(false);
                for (int t = 0; t<9; t++) {
                    if (user.getInventory()[t] == doorKey) {
                        user.removeItem(t);
                    }
                }
                inventoryBack = false;
                RemoveInHand();
                doorExit = ChangeRoom(currentButcherRoom);
                main.setCenter(GameDisplay(currentButcherRoom));
            }
            else if (stage == 2) {
                switch(currentButcherRoom) {
                    case 1:
                        currentButcherRoom = 2;
                        main.setCenter(GameDisplay(currentButcherRoom));
                        openBtn.setVisible(false);
                        break;
                    case 2:
                        currentButcherRoom = 3;
                        main.setCenter(GameDisplay(currentButcherRoom));
                        openBtn.setVisible(false);
                        break;
                    case 3:
                        //change to the ending na 
                        currentButcherRoom = 1;
                        main.setCenter(GameDisplay(currentButcherRoom));
                        openBtn.setVisible(false);
                        break;
                }
                inventoryBack = false;
                RemoveInHand();
            }
            openBtn.setVisible(false);
        });

        return DOORInteractions;
    }
    
    private ImageView ChangeRoom(int i) {
        ImageView newDoor = null;
        Image backgroundImage, doorImage;
        BackgroundImage bgImage;
        Background bg;
        Timer tr1 = new Timer();
        Timer tr2 = new Timer();
        Timer tr3 = new Timer();
        
        doorImage = new Image(Main.class.getResourceAsStream("img/stage2/door.png"));
        door = new ImageView();
        door.setImage(doorImage);
        door.setTranslateX(500);
        door.setTranslateY(300);
        door.setFitHeight(560);
        door.setFitWidth(420);

        newDoor = door;
                
        switch(i) {
            case 1:
                entityResize = 100;
                timer = 0;
                backgroundImage = new Image(Main.class.getResourceAsStream("img/stage2/meatstoreBG.png"));
                bgImage = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100,100,true,true,true,true)
                );
                bg = new Background(bgImage);
                main.setBackground(bg);
                
                tr1.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    timer += 1;
                    gameTitle.setText("Time Remaining: " + (180-timer));
                    if (timer % 2 == 0) {
                        entityResize += 3;
                        ResizeButcher(entityResize);
                    }
                    if (currentButcherRoom == 2) {
                        tr1.cancel();
                        timer = 0;
                        System.out.println("Congrats!");
                    }
                    else if(timer >= 180) {
                        tr1.cancel();
                        timer = 0;
                        GameOver();
                        door = null;
                    }
                }}, 0, 1000);
                break;
            case 2:
                entityResize = 100;
                backgroundImage = new Image(Main.class.getResourceAsStream("img/stage2/room1.png"));
                bgImage = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100,100,true,true,true,true)
                );
                bg = new Background(bgImage);
                main.setBackground(bg);
                
                tr2.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    timer += 1;
                    gameTitle.setText("Time Remaining: " + (120-timer));
                    if (timer % 2 == 0) {
                        entityResize += 5;
                        ResizeButcher(entityResize);
                    }
                    if (currentButcherRoom == 3) {
                        tr2.cancel();
                        timer = 0;
                        System.out.println("Congrats!");
                    }
                    else if(timer >= 120) {
                        tr2.cancel();
                        timer = 0;
                        GameOver();
                        door = null;
                    }
                }}, 0, 1000);
                break;
            case 3:
                entityResize = 100;
                backgroundImage = new Image(Main.class.getResourceAsStream("img/stage2/room2.png"));
                bgImage = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100,100,true,true,true,true)
                );
                bg = new Background(bgImage);
                main.setBackground(bg);
                
                tr3.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    timer += 1;
                    gameTitle.setText("Time Remaining: " + (60-timer));
                    if (timer % 2 == 0) {
                        entityResize += 6;
                        ResizeButcher(entityResize);
                    }
                    if (currentButcherRoom == 1) {
                        tr3.cancel();
                        timer = 0;
                        System.out.println("Congrats!");
                    }
                    else if(timer >= 60) {
                        tr3.cancel();
                        timer = 0;
                        GameOver();
                        door = null;
                    }
                }}, 0, 1000);
                break;
            default:
                newDoor = null;
                break;
        }
        
        newDoor = door;
        return newDoor;
    }
    
    private void ResizeButcher (int i) {
        npcView.setFitWidth(i);
        npcView.setPreserveRatio(true);
        doorExit.setTranslateX(600-i);
    }
    
    private void IntroDisplay(Stage ps) {
        FlowPane introPane = new FlowPane();
        
        if(isIntro) {
        introPane.setStyle("-fx-background-color: black");
        introPane.setAlignment(Pos.CENTER);

        introText.setFill(Color.WHITE);
        introText.setFont(Font.font("Chiller", 40));
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.millis(250), e -> {
                if (currentCharIndex <= introMessageLength) {
                    introText.setText(introMessage.substring(0, currentCharIndex));
                    currentCharIndex++;
                } else {
                    timeline.stop();
                    isIntro=false;
                    isStage1=true;
                }
            })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        
        introPane.getChildren().add(introText);
        Scene scene = new Scene(introPane, 400, 400);
        ps.setMaximized(true);
        ps.setScene(scene);
        ps.show();

        timeline.play();
 
        Button nextBtn = new Button("NEXT");
        nextBtn.setFont(Font.loadFont(Main.this.getClass().getResourceAsStream("font/who-asks-satan.ttf"), 25));
        nextBtn.setTextFill(Color.web("800000"));
        nextBtn.setBlendMode(BlendMode.SCREEN);
        nextBtn.getStyleClass().add("lightButton");
        nextBtn.getStyleClass().add("transparent");
        introPane.getChildren().add(nextBtn);
        
        //if (timeline.onFinishedProperty() == true) {
            //System.out.println("done");
        //}
        nextBtn.setOnAction((ActionEvent event1) -> {
            timeline.stop();
            isIntro=false;
            isStage1=true;
            ps.setMaximized(true);
            main.setTop(HeaderDisplay());
            main.setLeft(LeftStatsDisplay());
            main.setRight(RightStatsDisplay());
            main.setCenter(GameDisplay(currentButcherRoom));
            main.setBottom(InventoryDisplay());
        });
        }
    }
    
    private Node GameDisplay(int i) {
        gameDisplay.getChildren().clear();
        for (int t = 0; t<9; t++) {
            if (user.getInventory()[t] == doorKey) {
                user.removeItem(t);
            }
        }
        
        //STORAGE STAGE
        if (isStage1 == true) {
            //BACKGROUND FOR STAGE 1
            Image backgroundImage = new Image(Main.class.getResourceAsStream("img/stage1/storageBG.png"));
            BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
            );
            Background bg = new Background(bgImage);
            main.setBackground(bg);
            
            //FLICKERING LIGHTS CODE
            Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, event -> {
                // set the scene color to black
                game.setFill(Color.BLACK);
            }),
            new KeyFrame(Duration.millis(BLACKOUT_TIME_MS), event -> {
                // remove the fill from the scene
                game.setFill(null);
            })
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            if (user.getGallery()[0] == null) {
                user.takePhoto("img/testphoto1.png");
            }
            
            Item box1 = new Item("box1", "box", "img/stage1/box1.png");
            Image box1Img = new Image(Main.class.getResourceAsStream(box1.getAppearance()));
            ImageView box1View = new ImageView();
            box1View.setImage(box1Img);
            ApplyMovement(box1View);
            
            Item box2 = new Item("box2", "box", "img/stage1/box2.png");
            Image box2Img = new Image(Main.class.getResourceAsStream(box2.getAppearance()));
            ImageView box2View = new ImageView();
            box1View.setImage(box2Img);
            ApplyMovement(box2View);
            
            Item box3 = new Item("box2", "box", "img/stage1/box3.png");
            Image box3Img = new Image(Main.class.getResourceAsStream(box3.getAppearance()));
            ImageView box3View = new ImageView();
            box3View.setImage(box3Img);
            ApplyMovement(box3View);
            
            Image doorImage = new Image(Main.class.getResourceAsStream("img/stage2/door.png"));
            doorExit = new ImageView();
            doorExit.setImage(doorImage);
            doorExit.setTranslateX(100);
            doorExit.setTranslateY(100);
            doorExit.setFitHeight(600);
            doorExit.setPreserveRatio(true);
        
            gameDisplay.getChildren().addAll(box1View, BoxInteractionDisplay(box1View, 100, 125, true), box2View, BoxInteractionDisplay(box2View, 100, 125, false), box3View, BoxInteractionDisplay(box3View, 100, 125, false));
            gameDisplay.getChildren().add(doorExit);
            gameDisplay.getChildren().add(DoorInteractionDisplay(doorExit, 0, -200, 1));
        }

        //BUTCHER STAGE (STAGE 2)
        else if (isStage2 == true) {
            NPC butcher = new NPC("The Butcher", "img/stage2/butcher.png", "Meat...");
            Image npc = new Image(Main.class.getResourceAsStream(butcher.getAppearance()));
            npcView = new ImageView();
            npcView.setImage(npc);
            npcView.setFitWidth(entityResize);
            npcView.setPreserveRatio(true);
            if (!update) {
                doorExit = ChangeRoom(i);
            }
            else {
                System.out.println("updated");
                update = false;
            }
            
            int translateY = 0, translateX = 0;
            switch(currentButcherRoom) {
                case 1:
                    Image key1 = new Image(Main.class.getResourceAsStream("img/stage2/findKey.png"));
                    itemView = new ImageView();
                    itemView.setImage(key1);
                    itemView.setFitHeight(50);
                    itemView.setPreserveRatio(true);
                    translateY = 100;
                    translateX = 50;
                    break;
                case 2:
                    Image key2 = new Image(Main.class.getResourceAsStream("img/stage2/findKey.png"));
                    itemView = new ImageView();
                    itemView.setImage(key2);
                    itemView.setFitHeight(50);
                    itemView.setPreserveRatio(true);
                    translateY = 500;
                    translateX = 100;
                    break;
                case 3:
                    Image key3 = new Image(Main.class.getResourceAsStream("img/stage2/findKey.png"));
                    itemView = new ImageView();
                    itemView.setImage(key3);
                    itemView.setFitHeight(50);
                    itemView.setPreserveRatio(true);
                    translateY = 500;
                    translateX = 200;
                    break;
            }
            itemView.setTranslateY(translateY);
            itemView.setTranslateX(translateX);
            gameDisplay.getChildren().add(itemView);
            gameDisplay.getChildren().add(ItemInteractionDisplay(itemView, doorKey, translateY, translateX));
            gameDisplay.getChildren().add(npcView);
            gameDisplay.getChildren().add(doorExit);
            gameDisplay.getChildren().add(DoorInteractionDisplay(doorExit, 0, 0, 2));
            
            gameDisplay.setPrefWrapLength(1920);
            update = false;
        }
        //while(isStage3) {
        //}*/
        return gameDisplay;
    }
    
    private Node GalleryDisplay(){ //SCREEN 2
        HBox gallery = new HBox();
        Button[] btn = new Button[3];
        gallery.setOpacity(0.7);
        
        for(int i = 0; i < 3; i++) {
            btn[i] = new Button("Button-"+i);

            if(user.getGallery()[i] == null) {
                btn[i] = new Button("Button-"+i);
                ImageView galleryView = new ImageView();
                galleryView.setFitHeight(300);
                galleryView.setFitWidth(300);
                Image photo = new Image(Main.class.getResourceAsStream("img/null.png"));
                galleryView.setImage(photo);
                btn[i].setGraphic(galleryView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
                btn[i].getStyleClass().add("darkButton");
            }
            else if(user.getGallery()[i] != null) {
                btn[i] = new Button("Button-"+i);
                ImageView galleryView = new ImageView();
                galleryView.setFitHeight(300);
                galleryView.setFitWidth(300);
                Image photo = new Image(Main.class.getResourceAsStream(user.getGallery()[i]));    
                galleryView.setImage(photo);
                btn[i].setGraphic(galleryView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
                btn[i].getStyleClass().add("darkButton");
                
                btn[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(!otherBack) {
                            VBox photoView = new VBox(10);
                            galleryView.setFitHeight(600);
                            galleryView.setFitWidth(800);
                            photoView.getChildren().add(galleryView);
                            
                            Button backBtn = new Button("Back");
                            photoView.getChildren().add(backBtn);
                            backBtn.getStyleClass().add("lightButton");
                            
                            backBtn.setOnAction((ActionEvent event1) -> {
                                main.setCenter(GalleryDisplay());
                            });
                            
                            main.setCenter(photoView);
                            otherBack = true;                            
                        }
                        else {
                            main.setCenter(GalleryDisplay());
                            otherBack = false;
                        }
                    }
                });
            }
            
            gallery.getChildren().add(btn[i]);
        }
        gallery.setAlignment(Pos.CENTER);
           
        return gallery;
    }
    
    ImageView inHandView = new ImageView();
    private Node InHandShow(int i) {
        Item mainItem = user.getInventory()[i];
        user.equipItem(mainItem);

        Image itemINHAND = new Image(Main.class.getResourceAsStream(user.getInventory()[i].getAppearance()));
        inHandView.setImage(itemINHAND);                
        inHandView.setFitHeight(200);
        inHandView.setFitWidth(200);                
        inventoryGrid.add(inHandView, 9, 0);
        inventoryGrid.setTranslateY(0);
        inventoryGrid.setTranslateX(47);
        
        return inHandView;
    } 
    
    private Node RemoveInHand() {
        main.setBottom(InventoryDisplay());
        user.equipItem(null);
        inventoryGrid.getChildren().remove(inHandView);
        inventoryGrid.setTranslateY(0);
        inventoryGrid.setTranslateX(0);
        return null;
    }
    
    private Pane InventoryDisplay() {
        Button[] btn = new Button[10];
        
        for(int i = 0; i < 9; i++) {
            btn[i] = new Button("Button-"+i);
            Item mainItem = user.getInventory()[i];

            if(user.getInventory()[i] == null) {
                btn[i] = new Button("Button-"+i);
                ImageView itemView = new ImageView();
                itemView.setFitHeight(60);
                itemView.setFitWidth(60);
                Image item = new Image(Main.class.getResourceAsStream("img/null.png"));
                itemView.setImage(item);
                btn[i].setGraphic(itemView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
                btn[i].getStyleClass().add("darkButton");
            }
            else if(user.getInventory()[i] != null) {
                btn[i] = new Button("Button-"+i);
                ImageView itemView = new ImageView();
                ImageView inHandView = new ImageView();
                itemView.setFitHeight(60);
                itemView.setFitWidth(60);
                Image item = new Image(Main.class.getResourceAsStream(user.getInventory()[i].getAppearance()));    
                itemView.setImage(item);
                Image itemINHAND = new Image(Main.class.getResourceAsStream(user.getInventory()[i].getAppearance())); 
                inHandView.setImage(itemINHAND);
                btn[i].setGraphic(itemView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
                btn[i].getStyleClass().add("darkButton");
                
                int inventoryNumber = i;
                btn[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(!inventoryBack) {
                            InHandShow(inventoryNumber);
                            inventoryBack = true;
                        }
                        else {
                            RemoveInHand();
                            inventoryBack = false;
                        }
                    }
                });
            }
        }
        
        btn[9] = new Button("Button-9");
        ImageView itemView = new ImageView();
        itemView.setFitHeight(60);
        itemView.setFitWidth(60);
        Image item = new Image(Main.class.getResourceAsStream("img/galleryIcon.png"));
        itemView.setImage(item);
        btn[9].setGraphic(itemView);
        btn[9].setContentDisplay(GRAPHIC_ONLY);
        btn[9].getStyleClass().add("darkButton");
        
        int j = 0;
        for(Button b : btn) {
            if (j < 10) {
                inventoryGrid.add(b, j, 1);
                j++;
            }
        }
        
        inventoryGrid.setAlignment(Pos.BOTTOM_CENTER);
        btn[9].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!otherBack) {
                    main.setCenter(GalleryDisplay());
                    inventoryGrid.setTranslateX(-100);
                    otherBack = true;
                }
                else {
                    main.setCenter(GameDisplay(currentButcherRoom));
                    main.setBottom(InventoryDisplay());
                    otherBack = false;
               }
            }
        });
        
        return inventoryGrid;
    }
    
    private Pane SettingsDisplay() { //SCREEN 3
        GridPane settings = new GridPane();
        settings.setStyle("-fx-background-color: transparent;");
        
        GridPane settingMenu = new GridPane();
        GridPane settingStats = new GridPane();
        GridPane settingSliders = new GridPane();
        GridPane filler = new GridPane();
        filler.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        filler.setOpacity(0.6);
                
        settingMenu.add(new Text("SETTINGS MENU"), 0, 0);
        settingMenu.add(new Text(""), 0, 1);
        settingMenu.add(new Text(user.getName()), 0, 2);
        settingMenu.add(new Text("Room #" + currentLevel), 0, 3);
        settingMenu.add(new Text(""), 0, 4);
        settingMenu.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        settingMenu.setOpacity(0.6);
        settingMenu.setPadding(new Insets(10,10,10,10));
        
        settingStats.add(new Text(""), 0, 0);
        settingStats.add(new FlowPane(BarsDisplay(user.getHealth(), user.getMaxHealth())), 0, 1);
        settingStats.add(new Text(""), 0, 1);
        settingStats.add(new Text("Health"), 0, 2);
        settingStats.add(new Text(""), 0, 3);
        settingStats.add(new FlowPane(BarsDisplay(user.getSanity(), user.getMaxSanity())), 0, 4);
        settingStats.add(new Text("Sanity"), 0, 6);
        settingStats.add(new Text(""), 0, 7);
        settingStats.add(new FlowPane(BarsDisplay(user.getEnergy(), user.getMaxEnergy())), 0, 8);
        settingStats.add(new Text("Energy"), 0, 9);
        settingStats.add(new Text(""), 0, 10);
        settingStats.add(new FlowPane(BarsDisplay(user.getHunger(), user.getMaxHunger())), 0, 11);
        settingStats.add(new Text("Hunger"), 0, 12);
        settingStats.add(new Text(""), 0, 13);
        settingStats.add(new Text("Skill Level: " + user.getSkillLevel()), 0, 14);
        settingStats.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        settingStats.setOpacity(0.6);
        settingStats.setPadding(new Insets(0,0,10,10));
        
        settingSliders.add(new Text(""), 0, 0);
        settingSliders.add(new Slider(1, 100, 100), 0, 1);
        settingSliders.add(new Text("BGM"), 0, 2);
        settingSliders.add(new Text(""), 0, 3);
        settingSliders.add(new Slider(1, 100, 100), 0, 4);
        settingSliders.add(new Text("SFX"), 0, 5);
        settingSliders.add(new Text(""), 0, 6);
        settingSliders.add(new Slider(1, 100, 100), 0, 7);
        settingSliders.add(new Text("Dialogue"), 0, 8);
        settingSliders.add(new Text(""), 0, 9);
        settingSliders.add(new Slider(1, 100, 100), 0, 10);
        settingSliders.add(new Text("Display Size"), 0, 11);
        settingSliders.add(new Text(""), 0, 12);
        
        Button controlsBtn = new Button("Controls");
        controlsBtn.getStyleClass().add("lightButton");
        settingSliders.add(controlsBtn, 0, 13);
        settingSliders.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        settingSliders.setOpacity(0.6);
        //main.setCenter(ControlsDisplay());
        settingSliders.setPadding(new Insets(10,10,10,10));
        
        settings.add(settingMenu, 0, 4);
        settings.add(settingStats, 0, 5);
        settings.add(filler, 2, 4);
        settings.add(settingSliders,2,5);

        settings.setAlignment(Pos.CENTER);
        
        controlsBtn.setOnAction((ActionEvent event) -> {
            main.setCenter(ControlsDisplay());
        });
        
        return settings;
    }
    
    private Pane ControlsDisplay() {
        GridPane controls = new GridPane();
        controls.setStyle("-fx-background-color: transparent;");
        
        GridPane controlSettings = new GridPane();
        
        controlSettings.add(new Text("Controls"), 0, 0);
        
        controlSettings.add(new Text("Forward"), 0, 1);
        controlSettings.add(new TextField("W"), 2, 1);
        
        controlSettings.add(new Text("Left"), 0, 2);
        controlSettings.add(new TextField("A"), 2, 2);
        
        controlSettings.add(new Text("Backward"), 0, 3);
        controlSettings.add(new TextField("S"), 2, 3);
        
        controlSettings.add(new Text("Right"), 0, 4);
        controlSettings.add(new TextField("D"), 2, 4);
        
        controlSettings.add(new Text("Settings"), 0, 6);
        controlSettings.add(new TextField("ESC"), 2, 6);
        
        controlSettings.add(new Text("Gallery"), 0, 7);
        controlSettings.add(new TextField("0"), 2, 7);
        
        controlSettings.add(new Text("Jump"), 0, 8);
        controlSettings.add(new TextField("F"), 2, 8);
        
        controlSettings.add(new Text("Sprint"), 0, 9);
        controlSettings.add(new TextField("CTRL"), 2, 9);
        
        controlSettings.add(new Text("           "),1,0);
        controlSettings.add(new Text("Crouch"), 0, 10);
        controlSettings.add(new TextField("SHIFT"), 2, 10);
        Button settingsBtn = new Button("Settings");
        settingsBtn.getStyleClass().add("lightButton");
        controlSettings.add(settingsBtn, 2, 11);
        controlSettings.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        controlSettings.setOpacity(0.6);
        controlSettings.setPadding(new Insets(10,10,10,10));
        
        controls.add(controlSettings, 0, 11);
        
        settingsBtn.setOnAction((ActionEvent event) -> {
            main.setCenter(SettingsDisplay());
        });
        
        controls.setAlignment(Pos.CENTER);
        return controls;
    }
    
    private void GameOver() {
        ResizeButcher(800);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image gameOverImage = new Image(Main.class.getResourceAsStream("img/gameOverScreen.png"));
        BackgroundImage bgImage = new BackgroundImage(
            gameOverImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100,100,true,true,true,true)
        );
        Background bg = new Background(bgImage);
        main.setBackground(bg);
        
        gameDisplay.setVisible(false);
    }
}
