module demo.jeu {
    requires javafx.controls;
    requires javafx.fxml;


    opens demo.jeu to javafx.fxml;
    exports demo.jeu;
}