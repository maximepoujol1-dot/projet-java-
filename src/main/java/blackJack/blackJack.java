package blackJack;

import java.util.ArrayList;
import java.util.Arrays;
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
     private final ArrayList<ArrayList<String>> decks = new ArrayList<>(Arrays.asList(deckK, deckP, deckT, deckC));
     private final ArrayList<Integer> deckValue = new ArrayList<>(Arrays.asList(11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10));
     private double money;
     private double mise;
     private boolean blackJack;
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
     public double getMise() {
         return mise;
     }
     public boolean isBlackJack() {
         return blackJack;
     }
     public void setBlackJack(boolean blackJack) {
         this.blackJack = blackJack;
     }
     public void setMise(double mise) {
         this.mise = mise;
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
        this.mise = 0;
        this.playerCard = new ArrayList<>();
        this.DealerCard = new ArrayList<>();
        this.blackJack = false;
     }

    public void startGame(){
         if (getMoney()>0){
            hit(getPlayerCard());
            hit(getPlayerCard());
            hit(getDealerCard());
            hit(getDealerCard());
            System.out.print(getPlayerCard().get(0));
            System.out.print(getPlayerCard().get(1));
            System.out.print(getDealerCard().get(0));
            verifBlackJack();
         } else {
            System.out.print("vous êtes ruiné");
         }
    }

    public void newGain(boolean blackJack){
         double gain = 2.0;
         if (blackJack){
             gain =2.5;
         }
        setMoney(getMoney() + getMise()*gain);
        System.out.print("vous avez gagnez : " + getMise());
        System.out.print("votre solde est de : " + getMoney());
    }

    public void winOrNot(ArrayList<String> playerHand, ArrayList<String> dealerHand) {
        int powerPlayer = handPower(playerHand);
        int powerDealer = handPower(dealerHand);

        if (powerPlayer == powerDealer || powerPlayer > 21 && powerDealer > 21){
            setMoney(getMoney()+getMise());
            resetHand();
        } else if (powerPlayer > powerDealer && powerPlayer <=21){
            newGain(isBlackJack());
            resetHand();
         } else {
             System.out.print("vous avez perdu");
             System.out.print("votre solde est de : " + getMoney());
         }
    }

    public int handPower(ArrayList<String> hand){
        int power = 0;
        ArrayList<String> theDeck = null;

        for ( int i = 0; i< hand.size() ; i++ )
        {
            String slice = hand.get(i).substring(0, 1);
            theDeck = switch (slice) {
                case "C" -> getDeckC();
                case "P" -> getDeckP();
                case "T" -> getDeckT();
                case "K" -> getDeckK();
                default -> theDeck;
            };

            for ( int y = 0; y< theDeck.size() ; y++ )
            {
                if (theDeck.get(y).equals(hand.get(i))){
                    power += getDeckValue().get(y);
                    String slice1 = hand.get(i).substring(1, 2);
                    String slice2 = hand.get(i).substring(1, 2);
                    if (power>21 &&( slice1.equals("A") || slice2.equals("A") )){
                        power -=10;
                    }
                }
            }
        }
        return power;
    }

     public void resetHand(){
         setDealerCard(new ArrayList<>());
         setPlayerCard(new ArrayList<>());
         setMise(0);
         setBlackJack(false);
     }

    public void hit(ArrayList<String> user){
        Random random = new Random();
        int randomType = random.nextInt(4);
        ArrayList<String> theDeck = getDecks().get(randomType);
        int randomCard = random.nextInt(13);
        user.add(theDeck.get(randomCard));
    }

    public void dealerComportement(){
        while (handPower(getDealerCard())< 17) {
             hit(getDealerCard());
        }
    }

    public void howMany(double theMise){
         if (theMise> getMoney()){
             System.out.print("vous n'avez pas assez d'argent");
         } else {
             setMise(theMise);
             setMoney(getMoney()-theMise);
         }
    }

     public void stand() {
         dealerComportement();
         winOrNot(getPlayerCard(), getDealerCard());
     }

     public void verifBlackJack(){
         if (getPlayerCard().size() == 2 && handPower(getPlayerCard()) == 21) {
             setBlackJack(true);
             stand();
         }
     }
 }
