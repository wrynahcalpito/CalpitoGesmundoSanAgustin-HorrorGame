package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.control.ContentDisplay.GRAPHIC_ONLY;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
        
    int currentLevel = 0;
    Protagonist user = new Protagonist("Kobe");
    BorderPane main = new BorderPane();
        
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Last Night, Last Night");
                
        //MAIN CODE
        Item camera = new Item("camera", "tool", "A camera given to you take any photos you wish to capture", "camera.jpg"); 
        user.getItem(camera);
        user.takePhoto("testphoto1.jpg");
        user.takePhoto("testphoto2.png");
    
        //INITIALIZING BORDER PANE (MAIN GAME LAYOUT)
        Scene lvl1 = new Scene(main);
        primaryStage.setScene(lvl1);
        

        //BACKGROUND
        Image backgroundImage = new Image(Main.class.getResourceAsStream("backgroundImage.jpg"));
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
                );
        Background bg = new Background(bgImage);
        main.setBackground(bg);
        
        //SETTING ELEMENTS
        main.setTop(HeaderDisplay());
        main.setLeft(LeftStatsDisplay());
        main.setRight(RightStatsDisplay());
        main.setCenter(GameDisplay());
        main.setBottom(InventoryDisplay());
        //main.setCenter(GalleryDisplay());
        main.setCenter(SettingsDisplay());
                
        /*main.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESC: lvl1.setCenter(SettingsDisplay());
                }
            }
        }); //add the condition that this will appear with Esc*/
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);       
    }

    //METHODS (TO KEEP MAIN METHOD CLEAN)
    private Node HeaderDisplay() {
        FlowPane header = new FlowPane();
        
        header.setBackground(new Background(new BackgroundFill(Color.web("800000", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        header.setAlignment(Pos.TOP_CENTER);
        header.setPadding(new Insets(20, 50, 20, 50));
        header.setHgap(20);
        
        Text gameTitle = new Text("Last Night, Last Night");
        gameTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 45));
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
        healthStr.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        healthStr.setFill(Color.web("f0f1f2", 1.0));
        statsLeft.getChildren().add(healthStr);
        
        statsLeft.getChildren().add(BarsDisplay(user.getHealth(), user.getMaxHealth()));
        
        Text sanityStr = new Text("Sanity");
        sanityStr.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        sanityStr.setFill(Color.web("f0f1f2", 1.0));
        statsLeft.getChildren().add(sanityStr);
        
        statsLeft.getChildren().add(BarsDisplay(user.getSanity(), user.getMaxSanity()));
        
        return statsLeft;
    }

    private Node RightStatsDisplay() {
        VBox statsRight = new VBox();
        
        Text energyStr = new Text("Energy");
        energyStr.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        energyStr.setFill(Color.web("f0f1f2", 1.0));
        statsRight.getChildren().add(energyStr);
        
        statsRight.getChildren().add(BarsDisplay(user.getEnergy(), user.getMaxEnergy()));
        
        Text hungerStr = new Text("Hunger");
        hungerStr.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        hungerStr.setFill(Color.web("f0f1f2", 1.0));
        statsRight.getChildren().add(hungerStr);
        
        statsRight.getChildren().add(BarsDisplay(user.getHunger(), user.getMaxHunger()));
        
        return statsRight;
    }
    
    private Node GameDisplay() {
        FlowPane gameDisplay = new FlowPane();
        //insert 3D models but pictures for now
        Image npc = new Image(Main.class.getResourceAsStream("npc.png"));
        ImageView gameView = new ImageView();
        gameView.setTranslateX(250);
        gameView.setTranslateY(150);
        gameView.setFitHeight(800);
        gameView.setPreserveRatio(true);
        gameView.setImage(npc);
        gameDisplay.getChildren().add(gameView);
        gameDisplay.setPrefWrapLength(1920);
        
        return gameDisplay;
    }
    
    private Node GalleryDisplay(){
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
                Image photo = new Image(Main.class.getResourceAsStream("null.png"));
                galleryView.setImage(photo);
                btn[i].setGraphic(galleryView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
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
            }
            
            gallery.getChildren().add(btn[i]);
        }
        gallery.setAlignment(Pos.CENTER);
           
        return gallery;
    }
    
    private Pane InventoryDisplay() {
        GridPane inventoryGrid = new GridPane();
        Button[] btn = new Button[10];
        
        for(int i = 0; i < 9; i++) {
            btn[i] = new Button("Button-"+i);

            if(user.getInventory()[i] == null) {
                btn[i] = new Button("Button-"+i);
                ImageView itemView = new ImageView();
                itemView.setFitHeight(60);
                itemView.setFitWidth(60);
                Image item = new Image(Main.class.getResourceAsStream("null.png"));
                itemView.setImage(item);
                btn[i].setGraphic(itemView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
            }
            else if(user.getInventory()[i] != null) {
                btn[i] = new Button("Button-"+i);
                ImageView itemView = new ImageView();
                itemView.setFitHeight(60);
                itemView.setFitWidth(60);
                Image item = new Image(Main.class.getResourceAsStream(user.getInventory()[i].getAppearance()));    
                itemView.setImage(item);
                btn[i].setGraphic(itemView);
                btn[i].setContentDisplay(GRAPHIC_ONLY);
            }
        }
        
        btn[9] = new Button("Button-9");
        ImageView itemView = new ImageView();
        itemView.setFitHeight(60);
        itemView.setFitWidth(60);
        Image item = new Image(Main.class.getResourceAsStream("galleryicon.jpg"));
        itemView.setImage(item);
        btn[9].setGraphic(itemView);
        btn[9].setContentDisplay(GRAPHIC_ONLY);
        
        int j = 0;
        for(Button b : btn) {
            if (j < 10) {
                inventoryGrid.add(b, j, 0);
                j++;
            }
        }
        
        inventoryGrid.setAlignment(Pos.BOTTOM_CENTER);
        
        btn[9].setOnAction((ActionEvent event) -> {
            main.setCenter(GalleryDisplay());
        });
        
        return inventoryGrid;
    }
    
    private Pane SettingsDisplay() {
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
        settingSliders.add(controlsBtn, 0, 13);
        settingSliders.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        settingSliders.setOpacity(0.6);
        main.setCenter(ControlsDisplay());
        
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
        controlSettings.add(new TextField("W"), 1, 1);
        
        controlSettings.add(new Text("Left"), 0, 2);
        controlSettings.add(new TextField("A"), 1, 2);
        
        controlSettings.add(new Text("Backward"), 0, 3);
        controlSettings.add(new TextField("S"), 1, 3);
        
        controlSettings.add(new Text("Right"), 0, 4);
        controlSettings.add(new TextField("D"), 1, 4);
        
        controlSettings.add(new Text("Inspect"), 0, 5);
        controlSettings.add(new TextField("Q"), 1, 5);
        
        controlSettings.add(new Text("Settings"), 0, 6);
        controlSettings.add(new TextField("ESC"), 1, 6);
        
        controlSettings.add(new Text("Forward"), 0, 7);
        controlSettings.add(new TextField("W"), 1, 7);
        
        controlSettings.add(new Text("Gallery"), 0, 8);
        controlSettings.add(new TextField("0"), 1, 8);
        
        controlSettings.add(new Text("Jump"), 0, 9);
        controlSettings.add(new TextField("SPACE"), 1, 9);
        
        controlSettings.add(new Text("Sprint"), 0, 10);
        controlSettings.add(new TextField("CTRL"), 1, 10);
        
        controlSettings.add(new Text("Crouch"), 0, 11);
        controlSettings.add(new TextField("SHIFT"), 1, 11);
        controlSettings.setBackground(new Background(new BackgroundFill(Color.web("f0f1f2", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        controlSettings.setOpacity(0.6);
        
        controls.add(controlSettings, 0, 11);
        
        controls.setAlignment(Pos.CENTER);
        return controls;
    }
}
