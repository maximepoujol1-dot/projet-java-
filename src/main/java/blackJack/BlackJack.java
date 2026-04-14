package blackJack;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BlackJack extends Application {

    // --- Logique du Deck ---
    private final ArrayList<String> deckK = new ArrayList<>(Arrays.asList("KA","K2","K3","K4","K5","K6","K7","K8","K9","K10","K11","K12","K13"));
    private final ArrayList<String> deckP = new ArrayList<>(Arrays.asList("PA","P2","P3","P4","P5","P6","P7","P8","P9","P10","P11","P12","P13"));
    private final ArrayList<String> deckT = new ArrayList<>(Arrays.asList("TA","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11","T12","T13"));
    private final ArrayList<String> deckC = new ArrayList<>(Arrays.asList("CA","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13"));
    private final ArrayList<ArrayList<String>> decks = new ArrayList<>(Arrays.asList(deckK, deckP, deckT, deckC));
    private final ArrayList<Integer> deckValue = new ArrayList<>(Arrays.asList(11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10));

    // --- État du jeu ---
    private double money = 100.0;
    private double mise = 0;
    private ArrayList<String> playerCard = new ArrayList<>();
    private ArrayList<String> dealerCard = new ArrayList<>();

    // --- Éléments UI ---
    private Label lblSolde = new Label("Solde : 100€");
    private Label lblDealer = new Label("Dealer : ?");
    private Label lblPlayer = new Label("Joueur : ?");
    private Label lblResultat = new Label("Placez votre mise !");
    private TextField txtMise = new TextField();
    private Button btnHit = new Button("Tirer (Hit)");
    private Button btnStand = new Button("Rester (Stand)");
    private Button btnMiser = new Button("Parier");

    public static void lancer(Stage stage) {
        new BlackJack().start(stage);
    }

    @Override
    public void start(Stage stage) {
        // Design simple
        lblSolde.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        lblResultat.setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
        txtMise.setPromptText("Mise...");
        txtMise.setMaxWidth(80);

        btnHit.setDisable(true);
        btnStand.setDisable(true);

        // Actions
        btnMiser.setOnAction(e -> demarrerManche());
        btnHit.setOnAction(e -> tirerJoueur());
        btnStand.setOnAction(e -> rester());

        Button btnRetour = new Button("⬅ Menu");
        btnRetour.setOnAction(e -> main.MainView.lancer(stage));

        // Layouts
        HBox actions = new HBox(10, btnHit, btnStand);
        actions.setAlignment(Pos.CENTER);

        HBox miseBox = new HBox(10, txtMise, btnMiser);
        miseBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, lblSolde, lblDealer, lblPlayer, lblResultat, miseBox, actions, btnRetour);
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 400, 450));
        stage.setTitle("JavaFX BlackJack");
        stage.show();
    }

    private void demarrerManche() {
        try {
            double valueMise = Double.parseDouble(txtMise.getText());
            if (valueMise > 0 && valueMise <= money) {
                mise = valueMise;
                money -= mise;
                resetJeu();

                hit(playerCard); hit(playerCard);
                hit(dealerCard); hit(dealerCard);

                majAffichage(false);
                btnHit.setDisable(false);
                btnStand.setDisable(false);
                btnMiser.setDisable(true);
                lblResultat.setText("Bonne chance !");

                if(handPower(playerCard) == 21) rester(); // BlackJack auto
            } else {
                lblResultat.setText("Mise invalide !");
            }
        } catch (Exception ex) { lblResultat.setText("Entrez un nombre !"); }
    }

    private void tirerJoueur() {
        hit(playerCard);
        majAffichage(false);
        if (handPower(playerCard) > 21) {
            lblResultat.setText("Bust ! Vous avez dépassé 21.");
            finDeManche();
        }
    }

    private void rester() {
        while (handPower(dealerCard) < 17) {
            hit(dealerCard);
        }
        majAffichage(true);
        determinerVainqueur();
        finDeManche();
    }

    private void determinerVainqueur() {
        int p = handPower(playerCard);
        int d = handPower(dealerCard);

        if (p > 21) lblResultat.setText("Perdu (Bust)");
        else if (d > 21 || p > d) {
            double gain = (p == 21 && playerCard.size() == 2) ? mise * 2.5 : mise * 2;
            money += gain;
            lblResultat.setText("Gagné ! +" + gain + "€");
        } else if (p == d) {
            money += mise;
            lblResultat.setText("Égalité (Push)");
        } else {
            lblResultat.setText("Le Dealer gagne.");
        }
    }

    private void finDeManche() {
        btnHit.setDisable(true);
        btnStand.setDisable(true);
        btnMiser.setDisable(false);
        lblSolde.setText("Solde : " + money + "€");
    }

    private void majAffichage(boolean devoilerDealer) {
        lblPlayer.setText("Joueur : " + playerCard + " (Total: " + handPower(playerCard) + ")");
        if (devoilerDealer) {
            lblDealer.setText("Dealer : " + dealerCard + " (Total: " + handPower(dealerCard) + ")");
        } else {
            lblDealer.setText("Dealer : [" + dealerCard.get(0) + ", ?]");
        }
        lblSolde.setText("Solde : " + money + "€");
    }

    private void resetJeu() {
        playerCard.clear();
        dealerCard.clear();
    }

    private void hit(ArrayList<String> user) {
        Random random = new Random();
        int randomType = random.nextInt(4);
        ArrayList<String> theDeck = decks.get(randomType);
        user.add(theDeck.get(random.nextInt(13)));
    }

    private int handPower(ArrayList<String> hand) {
        int power = 0;
        int asCount = 0;
        for (String card : hand) {
            String valueStr = card.substring(1);
            int index = deckK.indexOf("K" + valueStr);
            if (index == -1) index = deckP.indexOf("P" + valueStr);
            if (index == -1) index = deckT.indexOf("T" + valueStr);
            if (index == -1) index = deckC.indexOf("C" + valueStr);

            int val = deckValue.get(index);
            if (val == 11) asCount++;
            power += val;
        }
        while (power > 21 && asCount > 0) {
            power -= 10;
            asCount--;
        }
        return power;
    }

    public static void main(String[] args) { launch(args); }
}