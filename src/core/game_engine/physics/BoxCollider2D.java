package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;

import java.util.ArrayList;

public class BoxCollider2D extends Component {
    private boolean hasCollided = false;
    private Rectangle bounds;
    public Rectangle getBounds(){
        return this.bounds;
    }

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
    }
    //check for collision
    public void checkCollisions(BoxCollider2D other){
        //todo implement an overlap test between object
        this.hasCollided = this.bounds.isOverlapping(other.bounds);
        if(this.hasCollided){
            this.otherColliders.add(other);
        }
    }
}
