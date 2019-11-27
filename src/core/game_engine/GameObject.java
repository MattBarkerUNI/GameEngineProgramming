package core.game_engine;
import processing.core.PApplet;
import processing.core.PVector;

public class GameObject {
    public PApplet parent;
    public PVector position;
    public GameObject(){ //Constructor

    }
    public GameObject(PApplet p){ //Constructor linking PApplet
        this.parent = p;
        this.position = new PVector(0, 0, 0); //parameters for the game objects position
    }

    public void update(){
        this.position.x +=2;
        parent.rect(this.position.x, this.position.y, 50, 75); //Link to PApplet parent ||| using variable for position instead of hard-coding
    }
}
