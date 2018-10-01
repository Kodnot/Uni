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

    public Spaceship(Point headPos) {
        this.headPos = headPos;
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

    public void moveHorizontally(int diff) {
        headPos.x += diff;
    }

    public void moveVertically(int diff) {
        headPos.y += diff;
    }
}
