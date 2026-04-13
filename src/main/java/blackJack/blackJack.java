package blackJack;

import java.util.List;

 class BlackJack {

    private final List<String> deckK = List.of(
            "KA","K2","K3","K4","K5","K6","K7","K8","K9","K10","K11","K12","K13"
    );

    private final List<String> deckP = List.of(
            "PA","P2","P3","P4","P5","P6","P7","P8","P9","P10","P11","P12","P13"
    );

    private final List<String> deckT = List.of(
            "TA","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11","T12","T13"
    );

     private final List<String> deckC = List.of(
             "CA","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13"
     );

     private final List<List<String>> decks = List.of(deckK, deckP,deckT,deckP);

     private final List<Integer> deckValue = List.of(
             14,2,3,4,5,6,7,8,9,10,11,12,13
     );

     private double money;

     private  List<String> playerCard;

     private  List<String> DealerCard;


     public List<String> getDeckP() {
         return deckP;
     }
     public List<String> getDeckT() {
         return deckT;
     }
     public List<String> getDeckC() {
         return deckC;
     }
     public List<String> getDeckK() {
         return deckK;
     }
     public List<List<String>> getDecks() {
         return decks;
     }
     public List<Integer> getDeckValue() {
         return deckValue;
     }
     public double getMoney() {
         return money;
     }
     public void setMoney(double money) {
         this.money = money;
     }
     public List<String> getPlayerCard() {
         return playerCard;
     }
     public void setPlayerCard(List<String> playerCard) {
         this.playerCard = playerCard;
     }
     public List<String> getDealerCard() {
         return DealerCard;
     }
     public void setDealerCard(List<String> dealerCard) {
         DealerCard = dealerCard;
     }

     public BlackJack(double money){
        this.money = money;

     }

    public void turn(){

    }

    public void defeat(){

    }

    public void win(){

    }

    public void ChooseCard(int max){
    }


}
