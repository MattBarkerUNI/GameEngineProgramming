package core.game_engine;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class GameObject {
    public PApplet parent;
    public PVector position;
    public PVector next_position;
    protected LayerTypes layerType = LayerTypes.BACKGROUND;
    protected boolean isActive = true;

    public void setActive(boolean active) {
        isActive = active;
    }

    public LayerTypes getLayerType() {
        return layerType;
    }

    protected ArrayList<Component> componentList = new ArrayList<>();
    public GameObject(){ //Constructor

    }
    public GameObject(PApplet p){ //Constructor linking PApplet
        this.parent = p;
        this.position = new PVector(0, 0, 0); //parameters for the game objects position
        this.next_position = new PVector(0,0,0);
    }

    public void update(){
        //this.position.x +=2;
        parent.rect(this.position.x, this.position.y, 50, 75); //Link to PApplet parent ||| using variable for position instead of hard-coding
    }
    public void addComponentList(Component c){
        componentList.add(c);
    }
}
