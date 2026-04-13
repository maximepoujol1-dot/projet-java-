package com.plusOuMoins; // Doit correspondre au nom du dossier créé

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class plusOuMoins extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Créer un composant (Node)
        Button btn = new Button("Clique sur moi !");
        btn.setOnAction(e -> System.out.println("Bonjour le monde !"));

        // 2. Créer un layout (Conteneur) et y ajouter le bouton
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // 3. Créer la scène avec des dimensions (Largeur, Hauteur)
        Scene scene = new Scene(root, 300, 250);

        // 4. Configurer le Stage (la fenêtre)
        primaryStage.setTitle("Ma Super App JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show(); // Afficher la fenêtre
    }

    public static void main(String[] args) {
        launch(args); // Lance l'application
    }
}
