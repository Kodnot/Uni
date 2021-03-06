package illegalaliens;

import java.awt.Point;

/**
 * Represents an entity moving with a constant velocity
 *
 * @author Kodnot
 */
class MovingEntity {

    protected Point currentPosition;
    protected Point previousPosition;
    protected int xVelocity;
    protected int yVelocity;

    public MovingEntity(Point cPos, int xVelocity, int yVelocity) {
        this.currentPosition = cPos;
        this.previousPosition = cPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public Point getPosition() {
        return currentPosition;
    }

    public int getPositionX() {
        return currentPosition.x;
    }

    public int getPositionY() {
        return currentPosition.y;
    }

    public Point getPreviousPosition() {
        return previousPosition;
    }

    public void applyVelocity() {
        previousPosition = currentPosition;
        currentPosition.x += xVelocity;
        currentPosition.y += yVelocity;
    }
}
