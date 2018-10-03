/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package illegalaliens;

import java.awt.Point;

/**
 *
 * @author Kodnot
 */
public class Spaceship {

    private Point headPos;
    private Direction direction;

    public Spaceship(Point headPos) {
        this.headPos = headPos;
        direction = Direction.UP;
    }

    public Point getHeadPos() {
        return headPos;
    }

    public int getHeadPosX() {
        return headPos.x;
    }

    public int getHeadPosY() {
        return headPos.y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move(Direction dir) {
        // If direction matches current, move in that direction
        if (dir == direction) {
            switch (dir) {
                case UP:
                    headPos.y -= 1;
                    break;
                case DOWN:
                    headPos.y += 1;
                    break;
                case LEFT:
                    headPos.x -= 1;
                    break;
                case RIGHT:
                    headPos.x += 1;
                    break;
            }
        } else {
            direction = dir;
        }
    }

    public void moveHorizontally(int diff) {
        headPos.x += diff;
        direction = diff == -1 ? Direction.LEFT : Direction.RIGHT;
    }

    public void moveVertically(int diff) {
        headPos.y += diff;
        direction = diff == -1 ? Direction.UP : Direction.DOWN;
    }
}
