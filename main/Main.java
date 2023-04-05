package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.beans.binding.Bindings.when;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.input.MouseEvent;
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

public class Main extends Application {
    boolean back = false;
    int currentLevel = 0, horizontalMovement = 250, verticalMovement = 250, entityResize = 800, xChange = 50;
    
    Protagonist user = new Protagonist("USER");
    BorderPane main = new BorderPane();
    GridPane inventoryGrid = new GridPane();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Last Night, Last Night");
    
        //INITIALIZING BORDER PANE (MAIN GAME LAYOUT)
        Scene game = new Scene(main);
        primaryStage.setScene(game);
        game.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        
        //MAIN CODE
        Item camera = new Item("camera", "item", "A camera given to you take any photos you wish to capture", "img/camera.png"); 
        user.getItem(camera);        
        main.setCenter(StartDisplay());

        //MAIN KEY EVENTS
        main.setOnKeyPressed((KeyEvent ke) -> {
            switch (ke.getCode()) {
                case ESCAPE:
                    if(!back) {
                        main.setCenter(SettingsDisplay());
                        back = true;
                    }
                    else {
                        main.setCenter(GameDisplay());
                        back = false;
                    }   break;
                case DIGIT0:
                    if(!back) {
                        main.setCenter(GalleryDisplay());
                        back = true;
                    }
                    else {
                        main.setCenter(GameDisplay());
                        main.setBottom(InventoryDisplay());
                        back = false;
                    }   break;
                case DIGIT1:
                    if(!back) {
                        InHandShow(0);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT2:
                    if(!back) {
                        InHandShow(1);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT3:
                    if(!back) {
                        InHandShow(2);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT4:
                    if(!back) {
                        InHandShow(3);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT5:
                    if(!back) {
                        InHandShow(4);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT6:
                    if(!back){
                        InHandShow(5);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT7:
                    if(!back) {
                        InHandShow(6);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT8:
                    if(!back) {
                        InHandShow(7);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case DIGIT9:
                    if(!back) {
                        InHandShow(8);
                        back = true;
                    }
                    else {
                        RemoveInHand();
                        back = false;
                    }   break;
                case A:
                    horizontalMovement += xChange;
                    main.setCenter(GameDisplay());
                    break;
                case D:
                    horizontalMovement -= xChange;
                    main.setCenter(GameDisplay());
                    break;
                case W:
                    entityResize += xChange;
                    main.setCenter(GameDisplay());
                    break;
                case S:
                    entityResize -= xChange;
                    main.setCenter(GameDisplay());
                    break;
                case F:
                    verticalMovement += 200;
                    main.setCenter(GameDisplay());
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
                    main.setCenter(GameDisplay());
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

    //METHODS (TO KEEP MAIN METHOD CLEAN)
    private Node StartDisplay() {
        VBox start = new VBox(10);
        
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
        
        confirmName.setOnAction((ActionEvent event) -> {
            if (name.getText().equals("Enter Name")) { user.setName("USER"); }
            else { user.setName(name.getText()); }
            
            Button startBtn = new Button("START");
            startBtn.setFont(Font.loadFont(getClass().getResourceAsStream("font/who-asks-satan.ttf"), 100));
            startBtn.setTextFill(Color.web("800000"));
            startBtn.setBlendMode(BlendMode.SCREEN);
            startBtn.getStyleClass().add("lightButton");
            startBtn.getStyleClass().add("transparent");
            start.getChildren().add(startBtn);
            
            startBtn.setOnAction((ActionEvent event1) -> {
                main.setTop(HeaderDisplay());
                main.setLeft(LeftStatsDisplay());
                main.setRight(RightStatsDisplay());
                main.setCenter(GameDisplay());
                main.setBottom(InventoryDisplay());
            });
        });      
                
        start.setAlignment(Pos.CENTER);        
        return start;
    }

    private Node HeaderDisplay() {
        FlowPane header = new FlowPane();
        
        header.setBackground(new Background(new BackgroundFill(Color.web("800000", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        header.setAlignment(Pos.TOP_CENTER);
        header.setPadding(new Insets(20, 50, 20, 50));
        header.setHgap(20);
        
        Text gameTitle = new Text("Last Night, Last Night");
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
        if (entityResize > 850) {
            entityResize = 850;
        }
        
        i.setTranslateX(horizontalMovement);
        i.setTranslateY(verticalMovement);
        i.setFitHeight(entityResize);
        i.setPreserveRatio(true);
    }
    
    private Node NPCInteractionDisplay(ImageView img, NPC npc) {
        HBox NPCInteractions = new HBox(10);

        img.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            Button talkBtn = new Button("TALK");
            NPCInteractions.getChildren().add(talkBtn);
            talkBtn.setOnAction((ActionEvent event) -> {
                int i = 0;
                while(i < npc.getDialogArrLength()) {
                    NPCInteractions.getChildren().add(new Text(npc.printDialog(i)));
                    i++;
                }
            });
            Button attackBtn = new Button("ATTACK");
            NPCInteractions.getChildren().add(attackBtn);
            attackBtn.setOnAction((ActionEvent event) -> {
                npc.health(10);
            });
        });
        
        return NPCInteractions;
    }
    
    private Node ItemInteractionDisplay(ImageView img, Item i) {
        HBox ITEMInteractions = new HBox(10);
        
        img.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            Button interactBtn = new Button("INTERACT");
            ITEMInteractions.getChildren().add(interactBtn);
            interactBtn.setOnAction((ActionEvent event) -> {
                i.interact();
            });
            Button getBtn = new Button("GET");
            ITEMInteractions.getChildren().add(getBtn);
            getBtn.setOnAction((ActionEvent event) -> {
                user.getItem(i);
            });
        });
        
        return ITEMInteractions;
    }
    
    private Node GameDisplay() { 
        FlowPane gameDisplay = new FlowPane();
        
        //STAGE 1
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
        
        user.takePhoto("img/testphoto1.png");
        NPC backroomsMonster = new NPC("Kobe", "img/stage1/npc.png", "RAHHHH");
        
        Image npc = new Image(Main.class.getResourceAsStream(backroomsMonster.getAppearance()));
        ImageView npcView = new ImageView();
        npcView.setImage(npc);
        ApplyMovement(npcView);
        
        gameDisplay.getChildren().add(npcView);
        gameDisplay.getChildren().add(NPCInteractionDisplay(npcView, backroomsMonster));
        
        gameDisplay.setPrefWrapLength(1920);
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
                galleryView.setFitWidth(400);
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
                galleryView.setFitWidth(400);
                Image photo = new Image(Main.class.getResourceAsStream(user.getGallery()[i]));    
                galleryView.setImage(photo);
                btn[i].setGraphic(galleryView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
                btn[i].getStyleClass().add("darkButton");
                
                btn[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(!back) {
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
                            back = true;                            
                        }
                        else {
                            main.setCenter(GalleryDisplay());
                            back = false;
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
        inventoryGrid.setTranslateY(-156);
        inventoryGrid.setTranslateX(47);
        
        return inHandView;
    } 
    
    private Node RemoveInHand() {
        main.setBottom(InventoryDisplay());
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
                
                btn[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(!back) {
                            user.equipItem(mainItem);
                            inHandView.setFitHeight(200);
                            inHandView.setFitWidth(200);
                            inventoryGrid.add(inHandView, 9, 0);
                            inventoryGrid.setTranslateY(-156);
                            inventoryGrid.setTranslateX(47);
                            back = true;
                        }
                        else {
                            main.setBottom(InventoryDisplay());
                            inventoryGrid.getChildren().remove(inHandView);
                            inventoryGrid.setTranslateY(0);
                            inventoryGrid.setTranslateX(0);
                            back = false;
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
                if(!back) {
                    main.setCenter(GalleryDisplay());
                    inventoryGrid.setTranslateX(-100);
                    back = true;
                }
                else {
                    main.setCenter(GameDisplay());
                    main.setBottom(InventoryDisplay());
                    back = false;
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
}
