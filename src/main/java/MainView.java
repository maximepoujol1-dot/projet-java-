package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
// Importation de ton jeu
import plusOuMoins.plusOuMoins;
import trueOrFalse.trueOrFalse;
import pendu.jeuDuPendu;
import blackJack.BlackJack;
import memory.memory;

public class MainView extends Application {

    // ✅ CETTE MÉTHODE MANQUAIT : Elle permet de revenir au menu
    public static void lancer(Stage stage) {
        new MainView().start(stage);
    }

    @Override
    public void start(Stage stage) {
        Label titre = new Label("Choisis un jeu");

        Button btnBlackJack = new Button("BlackJack");
        Button btnMemory = new Button("Memory");
        Button btnPendu = new Button("Pendu");
        Button btnPlusOuMoins = new Button("Plus ou Moins");
        Button btnTrueOrFalse = new Button("True or False");

        // ✅ ON RELIE LE BOUTON AU JEU
        btnPlusOuMoins.setOnAction(e -> plusOuMoins.lancer(stage));
        btnTrueOrFalse.setOnAction(e -> trueOrFalse.lancer(stage));

        // Les autres restent vides pour l'instant
        btnBlackJack.setOnAction(e -> BlackJack.lancer(stage));
        btnMemory.setOnAction(e -> memory.lancer(stage));
        btnPendu.setOnAction(e -> jeuDuPendu.lancer(stage));

        VBox root = new VBox(10, titre, btnBlackJack, btnMemory, btnPendu, btnPlusOuMoins, btnTrueOrFalse);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 300, 350);
        stage.setTitle("Menu des jeux");
        stage.setScene(scene);
        stage.show(); // ✅ Indispensable pour voir le menu
    }

    public static void main(String[] args) {
        launch(args);
    }
}