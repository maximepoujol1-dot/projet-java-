package trueOrFalse;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class trueOrFalse extends Application {

    private ArrayList<Question> baseDeDonnees;
    private int indexQuestion = 0;
    private int score = 0;

    private Label lblQuestion = new Label();
    private Label lblScore = new Label("Score : 0");
    private Label lblFeedback = new Label("");
    private Button btnVrai = new Button("Vrai");
    private Button btnFaux = new Button("Faux");
    private Button btnRejouer = new Button("Rejouer");
    private Button btnRetour = new Button("⬅ Menu");

    public static void lancer(Stage stage) {
        new trueOrFalse().start(stage);
    }

    @Override
    public void start(Stage stage) {
        initialiserQuestions();

        // Style
        lblQuestion.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        lblQuestion.setWrapText(true);
        lblFeedback.setStyle("-fx-font-style: italic;");

        // Actions des boutons
        btnVrai.setOnAction(e -> verifierReponse(true));
        btnFaux.setOnAction(e -> verifierReponse(false));

        btnRejouer.setVisible(false);
        btnRejouer.setOnAction(e -> lancer(stage));

        // Retour au menu principal
        btnRetour.setOnAction(e -> main.MainView.lancer(stage));

        // Mise en page
        HBox boutonsChoix = new HBox(20, btnVrai, btnFaux);
        boutonsChoix.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, lblScore, lblQuestion, boutonsChoix, lblFeedback, btnRejouer, btnRetour);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 40;");

        afficherQuestion();

        stage.setScene(new Scene(root, 450, 350));
        stage.setTitle("Quiz Vrai ou Faux");
        stage.show();
    }

    private void initialiserQuestions() {
        baseDeDonnees = new ArrayList<>();
        baseDeDonnees.add(new Question("Java est un langage de programmation ?", true));
        baseDeDonnees.add(new Question("Le Soleil tourne autour de la Terre ?", false));
        baseDeDonnees.add(new Question("L'eau bout à 100°C à pression normale ?", true));
        baseDeDonnees.add(new Question("Le 3e empereur de la dynastie Ming était Zhū Yǔnwén ?", false));
        baseDeDonnees.add(new Question("Charlemagne a été sacré en l'an 800 ?", true));
        baseDeDonnees.add(new Question("Leif Erikson a découvert l'Amérique ?", true));
        baseDeDonnees.add(new Question("Le cheval blanc d'Henri IV était blanc ?", false));
        Collections.shuffle(baseDeDonnees);
    }

    private void afficherQuestion() {
        if (indexQuestion < baseDeDonnees.size()) {
            lblQuestion.setText(baseDeDonnees.get(indexQuestion).texte);
        } else {
            lblQuestion.setText("FÉLICITATIONS !");
            lblFeedback.setText("Partie terminée.");
            btnVrai.setDisable(true);
            btnFaux.setDisable(true);
            btnRejouer.setVisible(true);
        }
    }

    private void verifierReponse(boolean reponseUser) {
        if (reponseUser == baseDeDonnees.get(indexQuestion).reponse) {
            score++;
            lblFeedback.setText("Correct !");
        } else {
            lblFeedback.setText("Faux !");
        }

        indexQuestion++;
        lblScore.setText("Score : " + score + " / " + baseDeDonnees.size());
        afficherQuestion();
    }

    public static void main(String[] args) {
        launch(args);
    }
}