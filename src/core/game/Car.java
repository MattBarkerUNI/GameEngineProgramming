package core.game;
import com.sun.tools.javac.Main;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Car extends GameObject {
    public PVector size;
    float angle = (float) 0.06981317007;
    float rotation = 0;
    PVector move = new PVector(250, 250, 0);
    PVector dir = new PVector(0, 1, 0);
    boolean fwd, lft, rgt;

    public Car(PApplet p, int x, int y, int w, int h){
        this.parent = p;
        this.size = new PVector(w, h, 0);
        this.position = new PVector(x, y, 0);
        this.parent.pushMatrix();
        //this.parent.rectMode(CENTER);
        this.parent.translate(move.x, move.y);
        this.parent.rotate(rotation);
        this.parent.rect(0, 0, 30, 50);
        this.parent.popMatrix();
        if (lft) {
            rotation -= angle;
            dir.rotate(-angle);
        } else if (rgt) {
            rotation += angle;
            dir.rotate(angle);
        }
        if (fwd) {
            move.add(dir.setMag(5));
        }
    }

    @Override
    public void update(){
        //this.position.x +=2;
        //Car rectangle
        parent.rect(this.position.x, this.position.y, 50, 75); //Link to PApplet parent ||| using variable for position instead of hard-coding
    }


    public void move(){
        //move Car
        //use arrow keys/WASD to move car
        if(parent.keyPressed){
            if(parent.key == 'a'){
                //move left
                lft = true;
                rgt = false;
            }else if(parent.key == 'd'){
                //move right
                lft = false;
                rgt = true;
            }if(parent.key == 'w'){
                //move forward
                fwd = true;
            }

        }


        //if(parent.keyReleased();


    }

    //  public void start() {
    //}

    //@Override
    //public static void update(){
    // move();
    //this.parent.rect(this.position.x, this.position.y, this.size.x, this.size.y);
    // }
}

