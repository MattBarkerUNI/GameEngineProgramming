package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;

import java.util.ArrayList;

public class BoxCollider2D extends Component {
    private boolean hasCollided = false;
    private Rectangle bounds;
    private SIDES hitSideV = SIDES.NONE;
    private SIDES hitSideH = SIDES.NONE;
    public boolean mouse_over = false; //flag for mouse interaction
    public SIDES getHitSideH(){ return hitSideH; }
    public SIDES getHitSideV(){ return hitSideV; }
    public Rectangle getBounds(){ return bounds; }

    private ArrayList<BoxCollider2D> otherColliders = new ArrayList<>();

    public ArrayList<BoxCollider2D> getOtherColliders() {
        return otherColliders;
    }

    public BoxCollider2D(Sprite g, float w, float h){
        super(g);
        this.bounds = new Rectangle(gameObject.position.x, gameObject.position.y, w, h);
        //update next
    }
    @Override
    public void update(){
        this.bounds.updateBounds(gameObject.position.x, gameObject.position.y); //create a variable for the new position to be updated
        this.mouse_over = this.bounds.pointHit(this.gameObject.parent.mouseX, this.gameObject.parent.mouseY);
    }
    //check for collision
    public void checkCollisions(BoxCollider2D other){
        //todo implement an overlap test between object
        this.hasCollided = this.bounds.isOverlapping(other.bounds);
        if(this.hasCollided){
            this.otherColliders.add(other);
        }
    }
    public void findCollisionSide(BoxCollider2D otherBox2D){
        //overlap has been confirmed, now detect the actual side

        hitSideV = SIDES.NONE;
        //is touching above
        boolean isTouchingAbove = this.bounds.getIsTouchingAbove(otherBox2D.getBounds());
        boolean isTouchingBelow = false;
        if(!isTouchingAbove){
            //check below
            isTouchingBelow = this.bounds.getIsTouchingBelow(otherBox2D.getBounds());
        }
        if(isTouchingAbove){
            hitSideV = SIDES.BOTTOM;
        } else if(isTouchingBelow){
            hitSideV = SIDES.TOP;
        }


        //do side
        hitSideH = SIDES.NONE;
            boolean isTouchingRight = this.bounds.getIsTouchingRight(otherBox2D.getBounds());
            boolean isTouchingLeft = false;

            if(!isTouchingRight){
                isTouchingLeft = this.bounds.getIsTouchingLeft(otherBox2D.getBounds());
            }
            if(isTouchingLeft){
                hitSideH = SIDES.LEFT;
            }else if(isTouchingRight){
                hitSideH = SIDES.RIGHT;
            }

    }
}
