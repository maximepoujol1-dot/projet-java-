package memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class memory {

    private final ArrayList<String> deckT = new ArrayList<>(Arrays.asList(
            "TA","T2","T3","T4","T5"
    ));

    private final ArrayList<String> deckC = new ArrayList<>(Arrays.asList(
            "CA","C2","C3","C4","C5"
    ));

    private final ArrayList<String> cards = new ArrayList<>();

    private final Map<String, String> cardCheck = Map.of(
            "TA",  "CA",
            "T2",  "C2",
            "T3",  "C3",
            "T4",  "C4",
            "T5",  "C5"
    );

    private final ArrayList<String> dual = new ArrayList<>();

    private void melange(ArrayList<String> deck1, ArrayList<String> deck2){
        cards.addAll(deck1);
        cards.addAll(deck2);
        Collections.shuffle(cards);
    }

    private void pick(String card){
        if (dual.size() < 2) {
            dual.add(card);
        }
    }

    private void verif(){
        if (cardCheck.get(dual.get(0)).equals(dual.get(1))) {
            cards.remove(dual.get(0));
            cards.remove(dual.get(1));
            dual.clear();
        } else {
            dual.clear();
        }
    }

    private boolean win(){
        return cards.isEmpty();
    }

    private void orchestrator(){
        melange(deckC,deckT);
        win();
    }

}
