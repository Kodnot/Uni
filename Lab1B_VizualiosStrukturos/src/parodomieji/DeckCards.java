/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2013 09 09
   *
   *  Tai yra demonstracinė kortų kaladės pateikimo ekrane ScreenKTU klasė. 
   *  joje taip pat pateikiamas enum taikymo pavyzdys, kuris paimtas iš
   *   http://docs.oracle.com/javase/1.5.0/docs/guide/language/enums.html
   * 
   *  Atkreipkite dėmesį į metodą mouseClicked(MouseEvent e), kurio pagalba
   *  yra reaguojama į pelės paspaudimą ant pasirinktos kortos.
   *  IŠBANDYKITE įvairius kortų pateikimo variantus, pvz. visos užverstos.
   *  PAPILDYKITE taškų skaičiavimo algoritmais.
   *  SUKURKITE paprastą žaidimą.
   ****************************************************************************/
package parodomieji;

import java.awt.Color;
import static java.awt.Color.*;
import java.awt.event.MouseEvent;
import java.util.*;
import studijosKTU.ScreenKTU;

enum Suit {  SPADE, DIAMOND, CLUB, HEART  }
enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

class Card {  // A card
    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;
    static private final char sym10 = 0x2789;
    
    static private final Color[] suitColor = {black, red, black, red};
    static private final char[] suitSym = {'♠', '♦', '♣', '♥'};
    static private final char[] rankSym = {
        'A', '2', '3', '4', '5', '6', '7', '8', '9', sym10, 'J', 'Q', 'K'};

    Card(Suit suit, Rank rank) {   // constructor
        this.suit = suit;
        this.rank = rank;
        this.faceUp = true;   // jei true - tai korta atversta
    }
    public Rank getRank() { return rank; }
    public Suit getSuit() { return suit; }
    public void reverseFace() {
        faceUp = !faceUp;
    }
    public String toString() {
//       return "This card is " + rank + " of " + suit;
        return ": card is " + rankSym[rank.ordinal()] + " of " + suitSym[suit.ordinal()];
    }
    public void show(ScreenKTU scr, int row, int col) {
        int r = row * 4 + 1;  // kortą sudaro 4x4 simboliai (kartu su tarpu)
        int c = col * 4 + 1;
        if (faceUp) {   // jei korta atversta
            scr.setColors(Color.white, suitColor[suit.ordinal()]);
            scr.fillRect(r, c, 3, 3);      // pagrindas bus baltas
            scr.lineBorder(r, c, 3, 3, 2); // pakraščio linija
            scr.print(r, c, rankSym[rank.ordinal()]);
            scr.print(r + 1, c + 1, suitSym[suit.ordinal()]);
            scr.print(r + 2, c + 2, rankSym[rank.ordinal()]);
        } else {           // jei korta užversta
            scr.setColors(Color.gray, Color.blue);
            scr.fillRect(r, c, 3, 3, '\u2592');
        }
    }
}

public class DeckCards extends ScreenKTU {  // A deck of card
    static final private int cHeight = 22, cWidth = 16;
    static final private int cardsInLine = Rank.values().length; 
    static final private int linesNum = Suit.values().length; 
//    static final int cardsNum = cardLine * linesNum; 
    List<Card> deck = new ArrayList();

    DeckCards() {   // konstruktorius generuoja pilną kortų kaladę
        super(cHeight, cWidth, linesNum*4 + 1, cardsInLine*4 + 1, Fonts.boldB);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) 
                deck.add(new Card(suit, rank));
        }
    }
    public void print() {
        System.out.println("***** begin of CardDeck");
        int nr = 0;
        for (Card card : deck) {
            System.out.println("" + nr++ + card);   // print all cards
        }
        System.out.println("******* end of CardDeck");
    }
    @Override
    public void show() {
        int nr = 0;
        for (Card card : deck) 
            card.show(this, nr / cardsInLine, nr++ % cardsInLine);   // show all cards
        refresh(100);
    }
    public void shuffle() {
        // use java.util.Collections' static method to shuffle the List
        Collections.shuffle(deck);
        Collections.shuffle(deck);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY() / (4 * cellH); // kortą sudaro 4x4 simboliai
        int c = e.getX() / (4 * cellW);
        Card card = deck.get(r * cardsInLine + c);
        card.reverseFace();
        card.show(this, r, c);
        refresh(80);
    }
    public static void main(String[] args) {
        DeckCards deck1 = new DeckCards();
        deck1.show();
        DeckCards deck2 = new DeckCards();
        deck2.shuffle();
        deck2.show();
//        deck1.print();
//        deck2.print();
    }
}
