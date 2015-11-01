package bloomberg.deck;

import java.util.Random;

/**
 * Created by yingtan on 10/26/15.
 */
public class Deck {

    private Card[] cards;
    private int currentNum;
    public static final int DECK_SIZE = 52;

    public Deck() {
        cards = new Card[DECK_SIZE];
        currentNum = DECK_SIZE;

        for (int i = 0 ; i < 13; i ++) { // each color : 13 cards
            // Important !!!
            cards[i] = new Card(1, (char)(i + 1));
            cards[i + 13] = new Card(2, (char)(i + 1));
            cards[i + 26] = new Card(3, (char)(i + 1));
            cards[i + 39] = new Card(4, (char)(i + 1));
        }
    }

    public Card deal() {
        currentNum --;
        return cards[currentNum];
    }

    public void shuffle() {
        Random randomIndex = new Random();
        for (int i = 0 ; i < currentNum ; i ++) {
            // pick a card from current deck randomly
            // Important !!!
            int changeIndex = randomIndex.nextInt(currentNum);
            Card tmp = cards[i];
            cards[i] = cards[changeIndex];
            cards[changeIndex] = tmp;
        }
    }
}
