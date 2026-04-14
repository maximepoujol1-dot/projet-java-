module demo.jeu {
    requires javafx.controls;
    requires javafx.fxml;

    opens main to javafx.graphics;
    opens plusOuMoins to javafx.graphics;
    opens trueOrFalse to javafx.graphics;
    opens pendu to javafx.graphics;
    opens blackJack to javafx.graphics;
    opens memory to javafx.graphics;


    exports main;
    exports plusOuMoins;
    exports trueOrFalse;
    exports pendu;
    exports blackJack;
    exports memory;
}