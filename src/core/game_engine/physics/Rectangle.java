package core.game_engine.physics;

public class Rectangle {
    public Rectangle(float x, float y, float w, float h){
        this.width = w;
        this.height = h;
        this.updateBounds(x, y);
    }
    private float x, y, width, height;
    private Point topRight = new Point(1,1);
    private Point bottomLeft = new Point(-1, -1);

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public void setTopRight(Point topRight) {
        this.topRight = topRight;
    }

    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public void updateBounds(float x, float y){
        this.x = x;
        this.y = y;
        this.bottomLeft.setX(this.x - this.width / 2f);
        this.bottomLeft.setY(this.y + this.height / 2f);
        this.topRight.setX(this.x + this.height / 2f);
        this.topRight.setY(this.y - this.height / 2f);
    }
}
