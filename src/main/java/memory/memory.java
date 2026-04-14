package memory;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class memory extends Application {

    private final List<String> deckBase = Arrays.asList("TA", "T2", "T3", "T4", "T5", "CA", "C2", "C3", "C4", "C5");
    private final Map<String, String> cardCheck = Map.of(
            "TA", "CA", "CA", "TA",
            "T2", "C2", "C2", "T2",
            "T3", "C3", "C3", "T3",
            "T4", "C4", "C4", "T4",
            "T5", "C5", "C5", "T5"
    );

    private List<String> cards = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();

    private Button firstButton = null;
    private String firstCard = null;
    private int pairesTrouvees = 0;
    private boolean blockClicks = false;

    public static void lancer(Stage stage) {
        new memory().start(stage);
    }

    @Override
    public void start(Stage stage) {
        cards.addAll(deckBase);
        Collections.shuffle(cards);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Création des 10 boutons (2 lignes de 5)
        for (int i = 0; i < cards.size(); i++) {
            String cardValue = cards.get(i);
            Button btn = new Button("?");
            btn.setPrefSize(70, 100);
            btn.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            btn.setOnAction(e -> handleFlip(btn, cardValue));

            buttons.add(btn);
            grid.add(btn, i % 5, i / 5);
        }

        Label lblTitre = new Label("Jeu de Memory");
        lblTitre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnRetour = new Button("⬅ Menu");
        btnRetour.setOnAction(e -> main.MainView.lancer(stage));

        VBox root = new VBox(20, lblTitre, grid, btnRetour);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30; -fx-background-color: #f0f0f0;");

        stage.setScene(new Scene(root, 500, 450));
        stage.setTitle("Memory");
        stage.show();
    }

    private void handleFlip(Button btn, String value) {
        if (blockClicks || btn == firstButton || btn.getText().equals("")) return;

        btn.setText(value); // On révèle la carte

        if (firstButton == null) {
            firstButton = btn;
            firstCard = value;
        } else {
            blockClicks = true;
            // Vérification de la paire
            if (cardCheck.get(firstCard).equals(value)) {
                // C'est une paire !
                pairesTrouvees++;
                firstButton.setDisable(true);
                btn.setDisable(true);
                resetSelection();
                if (pairesTrouvees == 5) {
                    System.out.println("Gagné !");
                }
            } else {
                // Pas une paire -> on recache après 1 seconde
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        javafx.application.Platform.runLater(() -> {
                            firstButton.setText("?");
                            btn.setText("?");
                            resetSelection();
                        });
                    } catch (InterruptedException e) { e.printStackTrace(); }
                }).start();
            }
        }
    }

    private void resetSelection() {
        firstButton = null;
        firstCard = null;
        blockClicks = false;
    }
}