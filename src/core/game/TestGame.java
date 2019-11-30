package core.game;
import core.game_engine.GameManager;
import core.game_engine.GameObject;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;

public class TestGame {
    public PApplet parent;
    private GameManager game_manager;
    Car car;
    Track track;
    InputController carInput; //controls the car's input

    public TestGame(PApplet p){
        this.parent = p;
    }
    public void start(){
        game_manager = new GameManager(this.parent); //Link to parent PApplet
        car = new Car(this.parent, 30, 300, 200, 20);
        car.position.x = 280;
        car.position.y = 100;
        track = new Track(this.parent, 30, 300, 200, 20);
        game_manager.add_game_object(track);

        //game_manager.add_game_object(car); //Adds game object to the list

        //add player
        car = new Car(this.parent, 300, 200, 20, 20);
        carInput = new InputController(car);
        game_manager.add_game_object(car);


    }
    public void update(){
        carInput.checkInput();
        game_manager.update();
    }

    public void keyPressed(char key, int keyCode){
        carInput.keyHandler(key, keyCode, true); //pass through key code
    }
    public void keyReleased(char key, int keyCode){
        carInput.keyHandler(key, keyCode, false);
    }
}
