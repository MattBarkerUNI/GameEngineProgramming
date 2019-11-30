package core.game;
import core.game_engine.Sprite;
import core.game_engine.input_commands.MoveAble;
import core.game_engine.physics.PhysicsComponent;
import processing.core.PApplet;
import processing.core.PVector;
import core.game_engine.GameObject;

public class Car extends Sprite implements MoveAble {
    public PVector size;
    private PhysicsComponent physicsComponent;
    public float acceleration = 2f;
    float angle = (float) 0.08;
    float rotation = 0;
    PVector move = new PVector(250, 250, 0);
    PVector dir = new PVector(0, 1, 0);
    PVector minusDir = new PVector(0, -1, 0);

    public Car(PApplet p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.parent = p;
        this.size = new PVector(w, h, 0);
        physicsComponent = new PhysicsComponent(this, this.boxCollider2D); // reference to this current gameObject being added in
    }

    @Override
    public void update() {
        super.update();
        this.parent.pushMatrix();
            this.parent.rectMode(PApplet.CENTER);
            this.parent.translate(move.x, move.y);
            this.parent.rotate(rotation);
            this.parent.rect(0, 0, 30, 50);
        this.parent.popMatrix();
    }

    @Override
    public void moveLeft() {
        rotation -= angle;
        dir.rotate(-angle);
        minusDir.rotate(-angle);
        //this.physicsComponent.setVelocity(-acceleration, 0);
    }

    @Override
    public void moveRight() {
        rotation += angle;
        dir.rotate(angle);
        minusDir.rotate(angle);
        //this.physicsComponent.setVelocity(acceleration, 0);
    }

    @Override
    public void moveForward() {
        move.add(dir.setMag(5));
        //this.physicsComponent.setVelocity(0, acceleration);
    }

    @Override
    public void moveBackward(){
        move.add(minusDir.setMag(5));
    }
}