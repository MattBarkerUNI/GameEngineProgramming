package core.game;
import core.game_engine.GameManager;
import core.game_engine.GameObject;
import processing.core.PApplet;

public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    Car gameCar;
    public TestGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent); //Link to parent PApplet
        gameCar = new Car(this.parent, 30, 300, 200, 20);
        gameCar.position.x = 280;
        gameCar.position.y = 100;
        game_manager.add_game_object(gameCar); //Adds game object to the list

        //add player
    }
    public void update(){
        game_manager.update();
    }
}
