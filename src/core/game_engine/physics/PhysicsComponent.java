package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;
import processing.core.PVector;

public class PhysicsComponent extends Component {
    private PVector velocity = new PVector(0, 0, 0);
    public float maxSpeed = 5f;
    private BoxCollider2D boxCollider2D;
    public PhysicsComponent(Sprite g, BoxCollider2D b){
        super(g);
        this.boxCollider2D = b;

    }

    @Override
    public void update() {
        if(velocity.mag() > maxSpeed){
            velocity.setMag(maxSpeed);
        }
        if(this.boxCollider2D.getOtherColliders().size() > 0){
            for(BoxCollider2D otherBoxCollider: this.boxCollider2D.getOtherColliders()){
                //consider what happens with the collision now
                this.velocity.x = 0;
                this.velocity.y = 0;
            }
            this.boxCollider2D.getOtherColliders().clear();
        }
        this.gameObject.position.set(this.gameObject.next_position.copy()); //quickly update current position with next position || allows to rest cars position if collision
        this.gameObject.next_position.add(this.velocity);
    }

    public void setVelocity(float x, float y) {
        velocity.x += x;
        velocity.y += y;
    }
}
