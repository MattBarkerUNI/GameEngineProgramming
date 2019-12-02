package core.game;

import core.game_engine.Sprite;
import processing.core.PApplet;
import processing.core.PVector;

public class Track extends Sprite {
    public PVector size;
    public Track(PApplet p, int x, int y, int w, int h){
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);
        this.position = new PVector(x, y, 0);
    }

    @Override
    public void update(){
        super.update();
        parent.pushMatrix();
            parent.rectMode(PApplet.CENTER);
            parent.translate(this.position.x, this.position.y);
            parent.fill(255);
            this.parent.rect(0, 0, this.size.x, this.size.y);
        parent.popMatrix();
    }

}
