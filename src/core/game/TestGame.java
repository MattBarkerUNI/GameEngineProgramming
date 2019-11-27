package core.game;
import core.game_engine.GameManager;
import core.game_engine.GameObject;
import processing.core.PApplet;

public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    GameObject gameObject;
    public TestGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent); //Link to parent PApplet
        gameObject = new GameObject(this.parent);
        gameObject.position.x = 280;
        gameObject.position.y = 100;
        game_manager.add_game_object(gameObject); //Adds game object to the list
    }
    public void update(){
        game_manager.update();
    }
}
