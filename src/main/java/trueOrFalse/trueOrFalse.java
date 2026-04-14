package trueOrFalse;

import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class trueOrFalse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Question> baseDeDonnees = new ArrayList<>();

        baseDeDonnees.add(new Question("Java est un langage de programmation ?", true));
        baseDeDonnees.add(new Question("Le Soleil tourne autour de la Terre ?", false));
        baseDeDonnees.add(new Question("L'eau bout à 100°C à pression normale ?", true));
        baseDeDonnees.add(new Question("Le 3e empereur de la dynastie Ming était Zhū Yǔnwén ?", false));
        baseDeDonnees.add(new Question("Charlemagne a été sacré en l'an 800 ?", true));
        baseDeDonnees.add(new Question("Leif Erikson a-t-il découvert l'Amérique ?", true));
        baseDeDonnees.add(new Question("Le cheval blanc d'Henri IV était blanc ?", false));

        Collections.shuffle(baseDeDonnees);

        int score = 0;

        Label instruction = new Label("BIENVENUE AU QUIZ VRAI/FAUX");
        System.out.println("Répondez par 'vrai' ou 'faux'.");

        for (Question q : baseDeDonnees) {
            System.out.println("Question : " + q.texte);
            System.out.print("Votre réponse : ");

            String reponseUserStr = sc.nextLine().toLowerCase();
            boolean reponseUser = reponseUserStr.equals("vrai");

            if (reponseUser == q.reponse) {
                System.out.println("Bravo ! C'est correct.");
                score++;
            } else {
                System.out.println("Dommage, c'était faux !");
            }
            System.out.println("---------------------------");
        }

        System.out.println("\nPARTIE TERMINÉE !");
        System.out.println("Votre score final : " + score + " / " + baseDeDonnees.size());

        sc.close();
    }
}