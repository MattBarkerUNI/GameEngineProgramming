import core.game.TestGame;
import processing.core.PApplet;
public class Main extends PApplet {
    private int WIDTH = 1000, HEIGHT = 1000; //need to define window variables outside of function
    private TestGame testGame;
    //Identify different core settings such as window size
    public void settings(){
        size(WIDTH, HEIGHT);
    }

    //Identify main setup features
    public void setup(){
        background(0);
        testGame = new TestGame(this); //Initialise
        testGame.start();
    }
    //What you want to be visable in the game itself.
    public void draw(){
        background(0);
        testGame.update();
    }

    public static void main(String args[]) {
        //System.out.println("Test Message");
        PApplet.main("Main");
    }
}
