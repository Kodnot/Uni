/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package illegalaliens;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import studijosKTU.ScreenKTU;

/**
 *
 * @author Kodnot
 */
public class IllegalAliens extends ScreenKTU {

    private static final int gameSize = 20;
    private static final int gameStartRow = 1;
    private static final int gameStartCol = 1;
    private static final int gameSleepTime = 80;
    private static final int targetSpawnDelay = 2;
    private Spaceship ship;
    private List<Projectile> projectiles;
    private List<Target> targets;

    private int turn;

    private boolean gameEnded;

    public IllegalAliens() {
        super(36, gameSize + 2);
        ship = new Spaceship(new Point(gameSize / 2, gameSize));
        projectiles = new ArrayList<>();
        targets = new ArrayList<>();
        gameEnded = false;
        turn = 0;
        drawBoard();
    }

    public void playTurn() {
        turn++;
        if (turn % targetSpawnDelay == 0) {
            spawnTargets(1);
        }
        advanceMovingEntities(projectiles);
        if (turn % 2 == 0) {
            advanceMovingEntities(targets);
        }

        discardOutOfBoundsEntities(); // TODO: Probs won't be needed now
        boolean playerCollided = handleEntityCollisions();

        if (playerCollided) {
            gameEnded = true;
            showDeathScreen();
            return;
        }
        drawBoard();
    }

    final void drawBoard() {
        setBackColor(Color.GREEN);
        setFontColor(Color.white);
        print(0, 0, "F*CK ALIENS");
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                setColors(Color.LIGHT_GRAY, Color.RED);
                print(gameStartRow + i, gameStartCol + j, ' ');
            }
        }

        // Draw spaceship
        Point shipPosition = ship.getHeadPos();
        print(shipPosition.y, shipPosition.x, getSpaceshipSymbol(ship));

        // Draw projectiles
        setForeground(Color.red);
        for (Projectile proj : projectiles) {
            Point pos = proj.getPosition();
            setColors(Color.LIGHT_GRAY, Color.RED);
            print(pos.y, pos.x, '*');
        }

        // Draw targets
        setForeground(Color.green);
        for (Target target : targets) {
            Point pos = target.getPosition();
            setColors(Color.LIGHT_GRAY, Color.green);
            print(pos.y, pos.x, '#');
        }
        refresh(gameSleepTime);
    }

    public void keyReleased(KeyEvent ke) {
        char ch = ke.getKeyChar();
        System.out.println("Paspaustas klavišas " + ch);

        // Player cannot move if he ded
        if (gameEnded) {
            return;
        }

        // Only handle given keys
        if (!"adws e".contains(String.valueOf(ch))) {
            return;
        }

        switch (ch) {
            case 'a':
                if (ship.getHeadPosX() - 1 >= gameStartCol) {
                    ship.move(Direction.LEFT);
                }
                break;
            case 'd':
                if (ship.getHeadPosX() + 1 < gameStartCol + gameSize) {
                    ship.move(Direction.RIGHT);
                }
                break;
            case 'w':
                if (ship.getHeadPosY() - 1 >= gameStartRow) {
                    ship.move(Direction.UP);
                }
                break;
            case 's':
                if (ship.getHeadPosY() + 1 < gameStartRow + gameSize) {
                    ship.move(Direction.DOWN);
                }
                break;
            case ' ':
                generateProjectile();
                break;
        }
        playTurn();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IllegalAliens game = new IllegalAliens();
        game.playTurn();
    }

    private void generateProjectile() {
        Projectile proj;
        switch (ship.getDirection()) {
            case UP:
                proj = new Projectile(new Point(ship.getHeadPosX(), ship.getHeadPosY()), 0, -1);
                break;
            case LEFT:
                proj = new Projectile(new Point(ship.getHeadPosX(), ship.getHeadPosY()), -1, 0);
                break;
            case DOWN:
                proj = new Projectile(new Point(ship.getHeadPosX(), ship.getHeadPosY()), 0, 1);
                break;
            case RIGHT:
                proj = new Projectile(new Point(ship.getHeadPosX(), ship.getHeadPosY()), 1, 0);
                break;
            default:
                return;
        }

        projectiles.add(proj);
    }

    private void advanceMovingEntities(List<? extends MovingEntity> entities) {
        for (MovingEntity entity : entities) {
            entity.applyVelocity();
        }
    }

    private void discardOutOfBoundsEntities() {
        projectiles = projectiles.stream().filter(entity -> {
            Point pos = entity.getPosition();
            return pos.x >= gameStartCol && pos.x < gameStartCol + gameSize
                    && pos.y >= gameStartRow && pos.y < gameStartRow + gameSize;
        }).collect(Collectors.toList());

        targets = targets.stream().filter(entity -> {
            Point pos = entity.getPosition();
            return pos.x >= gameStartCol && pos.x < gameStartCol + gameSize
                    && pos.y >= gameStartRow && pos.y < gameStartRow + gameSize;
        }).collect(Collectors.toList());
    }

    private void spawnTargets(int targetCount) {
        Random r = new Random();
        r.ints(targetCount, gameStartCol, gameStartCol + gameSize).forEach(x -> targets.add(new Target(new Point(x, gameStartRow), 0, 1)));
    }

    /**
     * Handles projectile-target and target-player collisions
     *
     * @return true if player collided, false otherwise
     */
    private boolean handleEntityCollisions() {
        // Handle projectile - target collisions
        for (Iterator<Projectile> it = projectiles.iterator(); it.hasNext();) {
            Projectile proj = it.next();
            for (Iterator<Target> targetsIt = targets.iterator(); targetsIt.hasNext();) {
                Target target = targetsIt.next();
                if (target.getPositionX() == proj.getPositionX() && (target.getPositionY() == proj.getPositionY() || target.getPositionY() == proj.getPositionY() + 1)) {
                    targetsIt.remove();
                    it.remove();
                    break;
                }
            }
        }

        // Handle target - player collisions
        for (Target target : targets) {
            if (target.getPositionX() == ship.getHeadPosX() && target.getPositionY() == ship.getHeadPosY()) {
                return true;
            }
        }
        return false;
    }

    private void showDeathScreen() {
        clearAll(Color.BLACK);
        setBackColor(Color.BLACK);
        setFontColor(Color.RED);
        print(gameSize / 2, 2, "YOU DED");/*
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                setColors(Color.LIGHT_GRAY, Color.RED);
                print(gameStartRow + i, gameStartCol + j, ' ');
            }
        }*/
        refresh();
    }

    private char getSpaceshipSymbol(Spaceship ship) {

        switch (ship.getDirection()) {
            case UP:
                return '^';
            case DOWN:
                return 'v';
            case LEFT:
                return '<';
            case RIGHT:
                return '>';
            default:
                return 'o';
        }
    }
}
