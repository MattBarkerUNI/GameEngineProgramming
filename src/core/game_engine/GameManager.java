package core.game_engine;

import processing.core.PApplet;

import java.util.ArrayList;

public class GameManager {
    public PApplet parent;
    private ArrayList<Sprite> game_objects; //Stores all of the objects from the game
    public GameManager(PApplet p){ //constructor
        this.parent = p;
        startup(); //Creates an empty list
    }
    public void add_game_object(Sprite g){
        //Add gameobjects to list
        this.game_objects.add(g); //Add gameobject to the gameobjects list ||| Adds directly to list as oppose to manipulating the gameobjects list
    }

    public ArrayList<Sprite> getGame_objects(){
        return game_objects;
    }

    public void startup(){
        //Initialising the list
        this.game_objects = new ArrayList<Sprite>(); //Defining what gameobjects we are using
    }
    public void add_sprite_array(ArrayList<Sprite> newSprites){
        this.game_objects.addAll(newSprites);
    }
    public void update(){
        //Update all objects in the GameManager
        for(int i = 0; i < this.game_objects.size(); i ++) {
            Sprite gA = this.game_objects.get(i); //Reference to gameobjects
            gA.update(); //Updates all game objects as oppose to one
            for(int j = i + 1; j < this.game_objects.size(); j++){ //each object 1 at a time, apply collision for next object as oppose to every one.
                Sprite gB = this.game_objects.get(j);
                //if gA is static, don't check collisions
                //CHECK that MOVING objects are added to the game_objects before other types
                if(gA.getLayerType() == LayerTypes.MOVING && gB.getLayerType() != LayerTypes.BACKGROUND){
                    gA.boxCollider2D.checkCollisions(gB.boxCollider2D);
                }
                //use layers to separate moving from stationary
                gA.boxCollider2D.checkCollisions(gB.boxCollider2D);

            }
        }
    }

}
