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
class Target extends MovingEntity {

    public Target(Point cPos, int xVelocity, int yVelocity) {
        super(cPos, xVelocity, yVelocity);
    }

    public void setXVelocity(int value) {
        xVelocity = value;
    }

    public void setYVelocity(int value) {
        yVelocity = value;
    }
}
