/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 09
 *
 *  Tai yra šaškių žaidimo lentos demonstracinė klasė,
 *    kurioje demonstruojami įvairūs darbo su ekranu ScreenKTU metodai.
 *    Spaudžiant kairį ir dešinį pelės mygtukus formuojamos skirtingų spalvų šaškės.
 *    Taisyklės yra https://lt.wikipedia.org/wiki/%C5%A0a%C5%A1k%C4%97s
 *  Atkreipkite dėmesį į metodą mouseClicked(MouseEvent e), kurio pagalba
 *  yra reaguojama į pelės paspaudimą ant pasirinkto langelio.
 *  IŠBANDYKITE įvairius metodus, modifikuokite juos.
 *  IŠBANDYKITE sukurtos žaidybinės situacijos atspausdinimą.
 *  EKSPERIMENTUOKITE su spalvomis ir ekrano formavimo algoritmais.
 *  SURAŠYKITE reikiamus veiksmus neužbaigtiems metodams.
 *************************************************************************** */
package parodomieji;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import studijosKTU.ScreenKTU;

public class Draughts extends ScreenKTU {

    private static final int gameSize = 10;
    private static final char drSym = 0x25cf;
    private byte board[][];

    public Draughts(byte[][] board) {
        super(36, gameSize + 2);
        this.board = board;
        drawBoard();
    }
    private static final Color[] cellColors = {Color.lightGray, Color.gray};
    private static final Color[] drColors = {Color.yellow, Color.black};
    int startRow = 1, startCol = 1;

    final void drawBoard() {
        setBackColor(Color.GREEN);
        print(0, 0, "PLAY");
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                setColors(cellColors[(i + j) & 1], drColors[(board[i][j] + 2) / 2 - 1]);
                print(startRow + i, startCol + j, board[i][j] > 0 ? drSym : ' ');
            }
        }
        refresh();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY() / cellH;
        int c = e.getX() / cellW;
        if (r >= startRow && r < startRow + gameSize
                && c >= startCol && c < startCol + gameSize) {
            int i = r - startRow;
            int j = c - startCol;
            if (((i + j) & 1) == 1) {
                byte drColor = (byte) (e.getButton() == 1 ? 1 : 2);
                if (drColor != board[i][j]) { // jei nelygu, formuojama nauja fig.
                    setColors(cellColors[1], drColors[drColor - 1]);
                    print(r, c, drSym);
                    board[i][j] = drColor;
                } else {  // langelis išvalomas, jei spalvos sutampa
                    print(r, c, cellColors[1]);
                    board[i][j] = 0;
                }
                refresh(80);
            }
        }
    }

    public void keyTyped(KeyEvent ke) {
        char ch = ke.getKeyChar();
        System.out.println("Paspaustas klavišas " + ch);
        switch (ch) {
            case 'p':
                printBoard();
                break;
            case 'c':
                clearBoard();
                break;
        }
    }

    private void printBoard() {
        System.out.println("Spausdiname lentą int matricos pavidalu");
        for (byte[] row : board) {
            System.out.printf("        {");
            for (byte b : row) {
                System.out.printf("%1d,", b);
            }
            System.out.printf("},\n");
        }
    }

    public static void main(String[] args) {
        // objektai tik sukuriami, tolimesnis vykdymas su pelės paspaudimais
        new Draughts(new byte[gameSize][gameSize]);
        new Draughts(DraughtsMaps.maps[0]);
        new Draughts(DraughtsMaps.maps[1]);
        new Draughts(DraughtsMaps.maps[2]);
    }

    private void clearBoard() {
        /*for (byte[] row : board)
            for (byte b : row)
                b = 0;*/
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] = 0;
            }
        }
        drawBoard();
    }

}
