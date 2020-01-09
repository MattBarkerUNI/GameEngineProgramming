package core.game_engine.physics;

import core.game.TestGame;
import core.game_engine.Component;
import core.game_engine.GameManager;
import core.game_engine.LayerTypes;
import core.game_engine.Sprite;
import processing.core.PVector;

public class PhysicsComponent extends Component {
    private PVector velocity = new PVector(0, 0, 0);
    public float maxSpeed = 5f;
    private float friction = 0.9f;
    private float spacer = 0.3f;
    private float gravity = 0f;
    private BoxCollider2D boxCollider2D;
    PVector dir = new PVector(0, 1, 0);
    public PhysicsComponent(Sprite g, BoxCollider2D b){
        super(g);
        this.boxCollider2D = b;

    }

    @Override
    public void update() {
        velocity.mult(friction);
        if(velocity.mag() > maxSpeed){
            velocity.setMag(maxSpeed);
        }
        if(this.boxCollider2D.getOtherColliders().size() > 0){
            for(BoxCollider2D b : this.boxCollider2D.getOtherColliders()) {
                if (b.gameObject.getLayerType() == LayerTypes.INTERACTABLE) {
                    //add score, remove score, power up etc.
                    GameManager.GAME_SCORE -= 40;
                    b.gameObject.setActive(false);
                } else {
                    //static objects or moving
                    //setCollisionSide(b);
                    //restart
                    TestGame.INSTANCE.game_over();
                }
            }
                //consider what happens with the collision now
                //maxSpeed = 1f;
                //moveBack();
            this.boxCollider2D.getOtherColliders().clear();
        }
        this.velocity.mult(friction);
        this.gameObject.position.set(this.gameObject.next_position.copy()); //quickly update current position with next position || allows to rest cars position if collision
        this.gameObject.next_position.add(this.velocity);
    }

public void turn(float dir){
    this.dir.rotate(dir);
    }

    public void moveFwd() {
        this.velocity.add(dir.setMag(5));
    }
    public void moveBack() {
        this.velocity.sub(dir.setMag(5));
    }
    public void setVelocity(float x, float y) {
        velocity.x += x;
        velocity.y += y;
    }
    private void setCollisionSide(BoxCollider2D otherBox2D){
        this.boxCollider2D.findCollisionSide(otherBox2D);
        Point otherTopRight = otherBox2D.getBounds().getTopRight();
        Point otherBottomLeft = otherBox2D.getBounds().getBottomLeft();
        //switch case for the side hit
        if(Math.abs(this.velocity. x) > Math.abs( this.velocity.y)){
            //if the velocity horizontal is greater than vertical use the horizontal!
            switch (this.boxCollider2D.getHitSideH()){

                case LEFT:
                    this.gameObject.next_position.x =  otherTopRight.getX() + ( this.boxCollider2D.getBounds().getWidth() / 2f ) + spacer;
                    velocity.x = 0;

                    break;
                case RIGHT:
                    this.gameObject.next_position.x = otherBottomLeft.getX() - (this.boxCollider2D.getBounds().getWidth() / 2f ) - spacer;
                    velocity.x = 0;

                    break;
            }
        }else{
            switch (this.boxCollider2D.getHitSideV()){

                case TOP:
                    //put this object on the bottom
                    this.gameObject.next_position.y = otherBottomLeft.getY() + this.boxCollider2D.getBounds().getHeight() / 2f + spacer;
                    velocity.y = 0;
                    break;
                case BOTTOM:
                    float tmpY = otherTopRight.getY();
                    float tmpYmove = this.boxCollider2D.getBounds().getHeight() / 2f - spacer;
                    this.gameObject.next_position.y = otherTopRight.getY() - this.boxCollider2D.getBounds().getHeight() / 2f - spacer;
                    velocity.y = 0;
                    break;
            }
        }


    }
}
