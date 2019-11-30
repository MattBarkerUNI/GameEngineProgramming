package core.game_engine.physics;

import core.game_engine.Component;
import core.game_engine.Sprite;

public class BoxCollider2D extends Component {
    public BoxCollider2D(Sprite g, float w, float h){
        super(g);
    }
    @Override
    public void update(){

    }
    //check for collision
    public void checkCollisions(){
        //todo implement an overlap test between object
    }
}
