package plusOuMoins;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class plusOuMoins extends Application {

    private int nombreRDM;
    private int essais = 0;

    public static void lancer(Stage stage) {
        new plusOuMoins().start(stage);
    }

    @Override
    public void start(Stage stage) {
        nombreRDM = new Random().nextInt(1000) + 1;

        Label instruction = new Label("Entre un nombre entre 1 et 1000 :");
        Label feedback = new Label("");
        TextField input = new TextField();
        Button valider = new Button("Valider");
        Button rejouer = new Button("Rejouer");
        Button retour = new Button("⬅ Menu");

        rejouer.setVisible(false);

        valider.setOnAction(e -> {
            String texte = input.getText().trim();
            if (texte.isEmpty()) return;
            try {
                int guess = Integer.parseInt(texte);
                essais++;
                input.clear();
                if (guess == nombreRDM) {
                    feedback.setText("✅ Gagné en " + essais + " essais !");
                    valider.setDisable(true);
                    rejouer.setVisible(true);
                } else if (guess < nombreRDM) {
                    feedback.setText("⬆️ Plus grand !");
                } else {
                    feedback.setText("⬇️ Plus petit !");
                }
            } catch (NumberFormatException ex) {
                feedback.setText("Entre un nombre valide !");
            }
        });

        rejouer.setOnAction(e -> {
            nombreRDM = new Random().nextInt(1000) + 1;
            essais = 0;
            feedback.setText("");
            input.clear();
            valider.setDisable(false);
            rejouer.setVisible(false);
        });

        // ✅ Appel de la méthode que je viens d'ajouter dans MainView
        retour.setOnAction(e -> main.MainView.lancer(stage));

        VBox root = new VBox(10, instruction, input, valider, feedback, rejouer, retour);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 30;");

        stage.setScene(new Scene(root, 350, 280));
        stage.setTitle("Plus ou Moins");
        stage.show(); // ✅ AJOUTÉ : Pour afficher la fenêtre du jeu
    }

    public static void main(String[] args) {
        launch(args);
    }
}