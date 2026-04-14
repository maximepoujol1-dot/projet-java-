package pendu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class jeuDuPendu extends Application {

    private String motATrouver;
    private char[] motDevinette;
    private int vies = 7;

    // Éléments UI
    private Label lblPendu = new Label();
    private Label lblMot = new Label();
    private Label lblStatus = new Label("Devinez le mot !");
    private TextField inputLettre = new TextField();
    private Button btnValider = new Button("Valider");
    private Button btnRetour = new Button("⬅ Menu");
    private Button btnRejouer = new Button("Rejouer");

    // Dessins du pendu selon les vies restantes
    private final String[] ETAPES_PENDU = {
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n      |\n      |\n      |\n      |\n      |\n========="  
    };

    public static void lancer(Stage stage) {
        new jeuDuPendu().start(stage);
    }

    @Override
    public void start(Stage stage) {
        nouvellePartie();

        // Style du dessin du pendu (Police monospace obligatoire pour l'alignement)
        lblPendu.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14px; -fx-font-weight: bold;");
        lblMot.setStyle("-fx-font-size: 20px; -fx-letter-spacing: 5px; -fx-font-weight: bold;");
        inputLettre.setMaxWidth(50);

        btnValider.setOnAction(e -> jouer());
        btnRejouer.setVisible(false);
        btnRejouer.setOnAction(e -> start(stage));
        btnRetour.setOnAction(e -> main.MainView.lancer(stage));

        VBox root = new VBox(15, lblPendu, lblMot, lblStatus, inputLettre, btnValider, btnRejouer, btnRetour);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30;");

        stage.setScene(new Scene(root, 400, 500));
        stage.setTitle("Jeu du Pendu");
        stage.show();
    }

    private void nouvellePartie() {
        String[] dictionnaire = {"INTERFACE", "PROGRAMME", "ORDINATEUR", "CASTOR", "ORNITHORYNQUE", "SANGLIER"};
        motATrouver = dictionnaire[new Random().nextInt(dictionnaire.length)];
        motDevinette = new char[motATrouver.length()];
        for (int i = 0; i < motATrouver.length(); i++) motDevinette[i] = '_';

        vies = 7;
        majAffichage();
        btnValider.setDisable(false);
        btnRejouer.setVisible(false);
    }

    private void jouer() {
        String saisie = inputLettre.getText().toUpperCase();
        inputLettre.clear();

        if (saisie.isEmpty()) return;
        char lettre = saisie.charAt(0);
        boolean trouve = false;

        for (int i = 0; i < motATrouver.length(); i++) {
            if (motATrouver.charAt(i) == lettre) {
                motDevinette[i] = lettre;
                trouve = true;
            }
        }

        if (!trouve) {
            vies--;
        }

        majAffichage();
        verifierFin();
    }

    private void majAffichage() {
        lblPendu.setText(ETAPES_PENDU[vies]);
        lblMot.setText(new String(motDevinette));
        lblStatus.setText("Vies restantes : " + vies);
    }

    private void verifierFin() {
        if (!new String(motDevinette).contains("_")) {
            lblStatus.setText("🎉 BRAVO ! Vous avez gagné !");
            btnValider.setDisable(true);
            btnRejouer.setVisible(true);
        } else if (vies <= 0) {
            lblStatus.setText("💀 PERDU ! Le mot était : " + motATrouver);
            btnValider.setDisable(true);
            btnRejouer.setVisible(true);
        }
    }
}