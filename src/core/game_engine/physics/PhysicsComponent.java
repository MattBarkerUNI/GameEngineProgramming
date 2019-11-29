package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;
import processing.core.PVector;

public class PhysicsComponent extends Component {
    private PVector velocity = new PVector(0, 0, 0);
    public float maxSpeed = 5f;
    public PhysicsComponent(Sprite g){
        super(g);

    }

    @Override
    public void update() {
        if(velocity.mag() > maxSpeed){
            velocity.setMag(maxSpeed);
        }
        this.gameObject.position.add(velocity);
    }

    public void setVelocity(float x, float y) {
        velocity.x += x;
        velocity.y += y;
    }
}
