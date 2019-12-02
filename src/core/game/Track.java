package core.game;

import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.core.PVector;

public class Track extends Sprite {

    public Track(PApplet p, int x, int y, int w, int h){
        super(p, x, y, w, h);
        this.parent = p;


    }

    @Override
    public void update(){
        super.update();
        parent.pushMatrix();
           parent.rectMode(PApplet.CORNER);
            parent.translate(this.position.x - this.size.x /2, this.position.y - this.size.y /2);
            parent.fill(255);
            this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }

}
