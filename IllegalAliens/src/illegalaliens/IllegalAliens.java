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

    private static final int gameSize = 34;
    private static final int gameStartRow = 1;
    private static final int gameStartCol = 1;
    private static final int gameSleepTime = 80;

    private static final char bombChar = (char) 0x2202;
    private static final char targetChar = '卐';
    private static final char projectileChar = '*';
    private static final String gameTitle = "NAZI RUSH";

    private Spaceship ship;
    private List<Projectile> projectiles;
    private List<Target> targets;

    private int turn = 0;
    private int killCount = 0;
    private int bombCount = 3;
    private int targetSpawnDelay = 10;
    private boolean gameEnded = false;

    public IllegalAliens() {
        super(28, gameSize + 2);
        initialize();
    }

    public void initialize() {
        ship = new Spaceship(new Point(gameSize / 2, gameSize));
        projectiles = new ArrayList<>();
        targets = new ArrayList<>();
        turn = 0;
        killCount = 0;
        bombCount = 3;
        gameEnded = false;
        drawBoard();
    }

    public void addKill() {
        killCount++;
        // Add a bomb every 20th kill
        if (killCount % 40 == 0) {
            bombCount++;
        }
    }

    private void scaleDifficulty(int turn) {
        targetSpawnDelay = 10 - turn / 50;
        if (targetSpawnDelay < 1) {
            targetSpawnDelay = 1;
        }
    }

    public void playTurn() {
        scaleDifficulty(turn);
        if (turn % targetSpawnDelay == 0) {
            spawnTargets(1);
        }
        turn++;

        adjustTargetVelocities(ship.getHeadPos());
        advanceMovingEntities(projectiles);
        boolean playerCollided = handleEntityCollisions();
        if (turn % 2 == 0) {
            advanceMovingEntities(targets);
        }

        discardOutOfBoundsEntities();
        // Collisions are handled twice to avoid enemies skipping projectiles
        playerCollided |= handleEntityCollisions();

        if (playerCollided) {
            gameEnded = true;
            showDeathScreen();
            return;
        }
        drawBoard();
    }

    final void drawBoard() {
        setBackColor(Color.BLACK);
        setFontColor(Color.getHSBColor(127, 1.0f, 0.5f));
        fillBorder(0, 0, gameSize + 2, gameSize + 2, (char) 0x2593);

        setBackColor(Color.GREEN);
        setFontColor(Color.white);
        fillRect(0, 0, 1, gameSize + 2);
        print(0, 0, String.format("%s Turn:%d, Kills:%d, ∂:%d", gameTitle, turn + 1, killCount, bombCount));
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
            print(pos.y, pos.x, projectileChar);
        }

        // Draw targets
        setForeground(Color.green);
        for (Target target : targets) {
            Point pos = target.getPosition();
            setColors(Color.LIGHT_GRAY, Color.getHSBColor(127, 1.0f, 0.5f));
            print(pos.y, pos.x, targetChar);
        }
        refresh(gameSleepTime);
    }

    public void keyReleased(KeyEvent ke) {
        char ch = ke.getKeyChar();
        System.out.println("Paspaustas klavišas " + ch);

        // Player cannot move if he ded
        if (gameEnded) {
            if (ch == 'r') {
                initialize();
            } else {
                return;
            }
        }

        // Only handle given keys
        if (!"adws ef".contains(String.valueOf(ch))) {
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
            case 'f':
                if (bombCount > 0) {
                    detonateExplosion(ship.getHeadPos(), 3);
                    return;
                } else {
                    break;
                }
        }
        playTurn();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IllegalAliens game = new IllegalAliens();
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

    private void detonateExplosion(Point source, int radius) {
        bombCount--;
        for (Iterator<Target> targetsIt = targets.iterator(); targetsIt.hasNext();) {
            Target target = targetsIt.next();
            Point targetPos = target.getPosition();
            if (Math.abs(targetPos.x - source.x) <= radius && Math.abs(targetPos.y - source.y) <= radius) {
                targetsIt.remove();
                addKill();
            }
        }

        fillRect(source.y - radius, source.x - radius, radius * 2 + 1, radius * 2 + 1, bombChar);
        refresh();
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

        // TODO: Targets will never be out of bounds, at least at the moment.
        targets = targets.stream().filter(entity -> {
            Point pos = entity.getPosition();
            return pos.x >= gameStartCol && pos.x < gameStartCol + gameSize
                    && pos.y >= gameStartRow && pos.y < gameStartRow + gameSize;
        }).collect(Collectors.toList());
    }

    private void spawnTargets(int targetCount) {
        Random r = new Random();
        for (int i = 0; i < targetCount; ++i) {
            boolean sideChoice = r.nextBoolean();
            int x, y;
            if (sideChoice) {
                // Spawn left/right
                y = r.nextInt(gameStartRow + gameSize) + gameStartRow;
                x = r.nextBoolean() ? gameStartCol : gameStartCol + gameSize - 1;
            } else {
                // Spawn top/bottom
                x = r.nextInt(gameStartCol + gameSize) + gameStartCol;
                y = r.nextBoolean() ? gameStartRow : gameStartRow + gameSize - 1;
            }
            // Initially velocities are 0
            targets.add(new Target(new Point(x, y), 0, 0));
        }
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
            // A projectile can destroy multiple enemies on the same cell
            boolean collided = false;
            for (Iterator<Target> targetsIt = targets.iterator(); targetsIt.hasNext();) {
                Target target = targetsIt.next();
                if (target.getPositionX() == proj.getPositionX() && target.getPositionY() == proj.getPositionY()) {
                    targetsIt.remove();
                    collided = true;
                    addKill();
                }
            }
            if (collided) {
                it.remove();
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
        setBackColor(Color.BLACK);
        setFontColor(Color.RED);
        fillRect(0, 0, 1, gameSize + 2);
        print(0, 0, String.format("You DED! Turn:%d, Kills:%d", turn + 1, killCount));

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

    private void adjustTargetVelocities(Point dest) {
        for (Target target : targets) {
            int xDiff = target.getPositionX() - dest.x;
            int yDiff = target.getPositionY() - dest.y;

            // Edge case where I'm on tom of an enemy
            if (xDiff == 0 && yDiff == 0) {
                continue;
            }

            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                target.setXVelocity(-(xDiff / Math.abs(xDiff)));
                target.setYVelocity(0);
            } else {
                target.setXVelocity(0);
                target.setYVelocity(-(yDiff / Math.abs(yDiff)));
            }
        }
    }
}
