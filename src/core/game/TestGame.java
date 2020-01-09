package core.game;
//import com.sun.source.tree.SwitchTree;

import core.game_engine.GameManager;
import core.game_engine.data_management.DataManager;
import core.game_engine.input_commands.InputController;
import processing.core.PApplet;

public class TestGame {
    public static TestGame INSTANCE;
    public PApplet parent;
    private GameManager game_manager;
    Car car;
    Track track;
    InputController carInput; //controls the car's input
    DataManager dataManager;
    public GameMode gameMode = GameMode.START;
    LevelManager levelManager;

    private int endScore = 0;

    public void game_over(){
        this.gameMode = GameMode.GAME_OVER;
    }

    public void WINNER() {
        this.gameMode = GameMode.WINNER;
    }

    public TestGame(PApplet p) {
        this.parent = p;
        TestGame.INSTANCE = this;
    }

    public void start() {
        game_manager = new GameManager(this.parent); //Link to parent PApplet
        dataManager = new DataManager(this.parent);
        levelManager = new LevelManager(this.parent, game_manager);
        load_game();


        /*car = new Car(this.parent, 30, 300, 200, 20);
        car.position.x = 280;
        car.position.y = 100;
        //add player
        car = new Car(this.parent, 300, 200, 30, 50);
        game_manager.add_game_object(car);

        track = new Track(this.parent, 120, 300, 200, 40);
        game_manager.add_game_object(track);

        Collectable collectable = new Collectable(parent, 300, 300, 20, 20);
        game_manager.add_game_object(collectable); //Adds game object to the list*/
    }

    private void load_game() {
        this.game_manager.startup();
        dataManager.load();
        //add car, cast the 1st member from load_car as the player
        car = (Car)levelManager.load_cars().get(0);
        carInput = new InputController(car);
        levelManager.load_level("Level 1");

        /*if (dataManager.game_data.hasKey("car")) {
            //populate game manager with cars
            JSONArray carsArray = dataManager.get_json_array("car");
            if (carsArray != null) {
                //create cars
                for (int i = 0; i < carsArray.size(); i++) {
                    JSONObject carData = (JSONObject) carsArray.get(i);
                    Car savedCar = new Car(this.parent, carData.getInt("x"), carData.getInt("y"), 200, 20);
                    game_manager.add_game_object(savedCar);
                    car = savedCar;
                }

            }
        } else {
            car = new Car(this.parent, 30, 300, 200, 20);
            game_manager.add_game_object(car);
            dataManager.save(game_manager.getGame_objects(), "car");
        }*/
    }

    public void update() {
        switch (gameMode) {
            case START:
                welcome_screen();
                break;
            case PLAY:
                endScore = (GameManager.GAME_SCORE / 60);
                carInput.checkInput();
                game_manager.update();
                parent.text("Score " + endScore, 300, 200);
                break;
            case EDIT:
                //level editor mode
                levelManager.update();
                game_manager.update();
                this.parent.fill(0);
                parent.text("Edit mode: KEYS - Exit 1 | Add Track T | Delete D | Add Collectible C | Save S", 5, 18);

                break;
            case RELOAD:
                //load a level
                load_game();
                gameMode = GameMode.PLAY;
                break;
            case GAME_OVER:
                //parent.text("Loser! Press Space to Restart", 400, 300);
                game_manager.update();
                parent.text("Time:  " + endScore, 300, 200);
            case WINNER:
                parent.text("Press Space to Restart", 400, 300);
                game_manager.update();
                parent.text("Time:  " + endScore, 300, 200);
                break;
        }
    }

    public void keyPressed(char key, int keyCode) {
        switch (gameMode) {
            case START:
                break;
            case PLAY:
                carInput.keyHandler(key, keyCode, true); //pass through key code
                break;
            case EDIT:
                break;
            case RELOAD:
                break;
            case GAME_OVER:
            case WINNER:
                if(key == ' ') {
                    gameMode = GameMode.RELOAD;
                }
                break;
        }
    }

    public void keyReleased(char key, int keyCode) {
        switch (gameMode) {
            case START:
                switch(key) {
                    case '1':
                        gameMode = GameMode.EDIT;
                        game_manager.startup();
                        levelManager.load_level("Level 1");
                        break;
                        default:
                            gameMode = GameMode.RELOAD;
                            break;
                }

                break;
            case PLAY:
                carInput.keyHandler(key, keyCode, false);
                switch (key){
                    case '1':
                        gameMode = GameMode.START;
                        break;
                }
                break;
            case EDIT:
                switch (key){
                    case '1':
                        gameMode = GameMode.START;

                }
                break;
            case RELOAD:
                break;
        }
    }
    private void welcome_screen(){
        parent.pushMatrix();
        parent.translate(parent.width / 4, parent.height / 4);
        parent.rectMode(PApplet.CORNERS);
        parent.fill(0, 255, 0);
        parent.textSize(100);
        parent.text("Welcome", 20, 0);
        parent.textSize(28);
        parent.text("Press Space to play", 110, 60);
        parent.text("Press '1' to edit the level", 80, 120);
        parent.popMatrix();
    }

}


