/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package illegalaliens;

import java.awt.Point;

/**
 * Represents an entity moving with a constant velocity
 *
 * @author Kodnot
 */
class MovingEntity {

    private Point currentPosition;
    private Point previousPosition;
    private int xVelocity;
    private int yVelocity;

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
