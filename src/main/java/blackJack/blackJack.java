package blackJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

 class BlackJack {


     private final ArrayList<String> deckK = new ArrayList<>(Arrays.asList(
             "KA","K2","K3","K4","K5","K6","K7","K8","K9","K10","K11","K12","K13"
     ));

     private final ArrayList<String> deckP = new ArrayList<>(Arrays.asList(
             "PA","P2","P3","P4","P5","P6","P7","P8","P9","P10","P11","P12","P13"
     ));

     private final ArrayList<String> deckT = new ArrayList<>(Arrays.asList(
             "TA","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11","T12","T13"
     ));

     private final ArrayList<String> deckC = new ArrayList<>(Arrays.asList(
             "CA","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13"
     ));

     private final ArrayList<ArrayList<String>> decks = new ArrayList<>(Arrays.asList(deckK, deckP, deckT, deckP));

     private final ArrayList<Integer> deckValue = new ArrayList<>(Arrays.asList(14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));

     private double money;

     private  ArrayList<String>  playerCard;

     private  ArrayList<String>  DealerCard;

     public ArrayList<String> getDeckK() {
         return deckK;
     }

     public ArrayList<String> getDeckP() {
         return deckP;
     }

     public ArrayList<String> getDeckT() {
         return deckT;
     }

     public ArrayList<String> getDeckC() {
         return deckC;
     }

     public ArrayList<ArrayList<String>> getDecks() {
         return decks;
     }

     public ArrayList<Integer> getDeckValue() {
         return deckValue;
     }

     public double getMoney() {
         return money;
     }

     public void setMoney(double money) {
         this.money = money;
     }

     public ArrayList<String> getPlayerCard() {
         return playerCard;
     }

     public void setPlayerCard(ArrayList<String> playerCard) {
         this.playerCard = playerCard;
     }

     public ArrayList<String> getDealerCard() {
         return DealerCard;
     }

     public void setDealerCard(ArrayList<String> dealerCard) {
         DealerCard = dealerCard;
     }

     public BlackJack(double money){
        this.money = money;
     }

    public void game(){
        startGame();
    }

    public void startGame(){
        chooseCard(getPlayerCard());
        chooseCard(getPlayerCard());
        chooseCard(getDealerCard());
        chooseCard(getDealerCard());
        System.out.print(getPlayerCard().get(0));
        System.out.print(getPlayerCard().get(1));
        System.out.print(getDealerCard().get(0));
    }

    public void win(double gain){
        setMoney(gain);
        System.out.print("vous avez gagnez : " + gain);
        System.out.print("votre solde est de : " + getMoney());
    }

    public void handPower(ArrayList<String> hand){
        int power = 0;
        ArrayList<String> theDeck = null;

        for ( int i = 0; i< hand.size() ; i++ )
        {
            String slice = hand.get(i).substring(0, 0);
            if (slice == "C") {
                theDeck = getDeckC();
            } else if (slice == "P") {
                theDeck = getDeckP();
            } else if (slice == "T") {
                theDeck = getDeckT();
            } else if (slice == "K") {
                theDeck = getDeckK();
            }

            for ( int y = 0; y< theDeck.size() ; y++ )
            {
                if (theDeck.get(y)==hand.get(i)){
                    power += getDeckValue().get(y);
                }
            }
        }
    }

     public void ResetHand(){
         setDealerCard(null);
         setPlayerCard(null);
     }

    public void chooseCard(ArrayList<String> user){
        Random random = new Random();
        int randomType = random.nextInt(4);
        ArrayList<String> theDeck = getDecks().get(randomType);
        int randomCard = random.nextInt(12);
        user.add(theDeck.get(12));
    }
 }
