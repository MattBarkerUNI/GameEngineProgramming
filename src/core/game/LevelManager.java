package core.game;

import core.game_engine.GameManager;
import core.game_engine.Sprite;
import core.game_engine.data_management.DataManager;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class LevelManager {
    private PApplet parent;
    private GameManager gameManager;
    char currentKey = 't';
    private String level_name = "Level 1";
    private int trackWidth = 25;
    private int trackHeight = 25;
    private int collectableWidth = 25;
    private int collectableHeight = 25;
    private int winnerWidth = 25;
    private int winnerHeight = 25;
    String itemType = "Track";
    boolean mouse_down = false;
    DataManager dataManager;
    ArrayList<Sprite> loaded_game_objects;


    public LevelManager(PApplet p, GameManager g){

        parent = p;
        gameManager = g;
        dataManager = new DataManager(this.parent);
        dataManager.load();
    }

    public void load_level(String _level_name){
        level_name = _level_name;
        load_object_list(level_name);
        this.gameManager.add_sprite_array(loaded_game_objects);
    }

    public ArrayList<Sprite> load_cars(){
        load_object_list("car");
        if(loaded_game_objects.size() == 0){
            Car car = new Car(this.parent, 300, 100, 20, 20);
            loaded_game_objects.add(car);
            this.gameManager.add_sprite_array(loaded_game_objects);
            dataManager.save(gameManager.getGame_objects(), "car");
        }else {
            this.gameManager.add_sprite_array(loaded_game_objects);
        }
        return loaded_game_objects;
    }

    private void load_object_list(String list_name){
        loaded_game_objects = new ArrayList<>();
        if(dataManager.game_data != null && dataManager.game_data.hasKey(list_name)){
            JSONArray levelItemsArray = dataManager.game_data.getJSONArray(list_name);
            for(int i = 0; i < levelItemsArray.size(); i++){
                JSONObject itemData = (JSONObject)levelItemsArray.get(i);
                itemType = itemData.getString("type");
                add_object(itemData.getInt("x"), itemData.getInt("y"), itemData.getInt("w"), itemData.getInt("h"));
            }
        }
    }

    private void save_level(){
        dataManager.save(gameManager.getGame_objects(), level_name);
    }

    public void update(){
        if(parent.keyPressed){
            if(currentKey != parent.key){
                currentKey = parent.key;
                handle_key();
            }
        }
        if(parent.mousePressed){
            if(!mouse_down){
                mouse_down = true;
                handle_key();
            }
        }else{
            mouse_down = false;
        }
        show_menu();
    }
    private void show_menu(){
        parent.pushMatrix();
        parent.rectMode(PApplet.CORNERS);
        parent.fill(255);
        parent.rect(0,0,parent.width, 25);
        parent.fill(0);
        parent.textSize(12);
        parent.text("Edit mode: KEYS - Exit 1 | Add Track T | Delete D | Add Collectible C | Save S", 5, 50);
        parent.popMatrix();
    }

    private void handle_key(){
        System.out.println("handle_key currentKey " +currentKey);
        switch (currentKey){
            case 'w':
            case 'W':
                itemType = "Winner";
                System.out.println("Add winner");
                Sprite new_winner = add_object(grid_placement(parent.mouseX, winnerWidth), grid_placement(parent.mouseY, winnerWidth), winnerWidth, winnerHeight);
                gameManager.add_game_object(new_winner);
                break;
            case 't':
            case 'T':
                itemType = "Track";
                System.out.println("Add track");
                Sprite new_sprite = add_object(grid_placement(parent.mouseX, trackWidth), grid_placement(parent.mouseY, trackWidth), trackWidth, trackHeight);
                gameManager.add_game_object(new_sprite);
                break;
            case 'c':
            case 'C':
                itemType = "Collectable";
                Sprite new_collectable = add_object(grid_placement(parent.mouseX, collectableWidth), grid_placement(parent.mouseY, collectableWidth), collectableWidth, collectableHeight);
                gameManager.add_game_object(new_collectable);
                System.out.println("Collectable");
                break;
            case 's':
            case 'S':
                System.out.println("Save");
                save_level();
                break;
            case 'd':
            case 'D':
                System.out.println("Delete");
                remove_object();
                break;
        }
    }

    private void remove_object(){
        //go through game objects and delete
        for(int i = 0; i < gameManager.getGame_objects().size(); i ++){
            Sprite gA = gameManager.getGame_objects().get(i);
            if(gA.boxCollider2D.mouse_over){
                gameManager.getGame_objects().remove(i);
            }
        }
    }

    private Sprite add_object(int x, int y, int w, int h){
        Sprite sprite = null;
        switch (itemType){
            case "Track":

                Track gameTrack = new Track(this.parent, x, y, w, h);
                //gameManager.add_game_object(gameTrack);
                loaded_game_objects.add(gameTrack);
                sprite = gameTrack;
                break;
            case "Collectable":
                Collectable gameCollectable = new Collectable(this.parent, x, y, w, h);
                //gameManager.add_game_object(gameTrack);
                loaded_game_objects.add(gameCollectable);
                sprite = gameCollectable;
                break;
            case "Winner":
                Winner gameWinner = new Winner(this.parent, x, y, w, h);
                //gameManager.add_game_object(gameTrack);
                loaded_game_objects.add(gameWinner);
                sprite = gameWinner;
                break;
            case "Car":
                //add car
                Car car = new Car(this.parent, x, y, w, h);
                loaded_game_objects.add(car);
                sprite = car;
                break;
        }
        return sprite;
    }
    private int grid_placement(int num, int sizeOfGrid){
        int grid = sizeOfGrid * Math.floorDiv(num,sizeOfGrid) + sizeOfGrid / 2;
        return grid;
    }
}
