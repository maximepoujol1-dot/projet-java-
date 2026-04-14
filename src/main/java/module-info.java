module demo.jeu {
    requires javafx.controls;
    requires javafx.fxml;

    // Ouvre les packages pour que JavaFX puisse afficher les fenêtres
    opens main to javafx.graphics;
    opens plusOuMoins to javafx.graphics;

    exports main;
    exports plusOuMoins;
}