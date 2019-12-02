package core.game_engine;

import core.game_engine.physics.BoxCollider2D;
import core.game_engine.physics.Rectangle;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Sprite extends GameObject {
    public PVector size;
    protected BoxCollider2D boxCollider2D;
    protected Rectangle bounds;

    public Sprite(PApplet p, float x, float y, float w, float h){ //refers to original GameObject constructor
        super(p);
        this.position = new PVector(x, y, 0);
        this.size = new PVector(w, h, 0);
        this.next_position = this.position.copy();
        this.boxCollider2D = new BoxCollider2D(this, w, h);

    }

    @Override
    public void update(){
        for(Component c: this.componentList){ //checking components in the list and updating them
            c.update();
        }
    }
}
