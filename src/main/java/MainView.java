import blackJack.BlackJack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainView extends BorderPane {

    private final BlackJack blackJack = new BlackJack(100.0);

    // Pages
    private final StackPane pages = new StackPane();
    private Node pageHub;
    private Node pageBlackJack;
    private Node pageMemory;
    private Node pageMoreOrLess;
    private Node pageTrueOrFalse;
    private Node pageHangMan;

    // Bas
    private Label argentLabel;

    // Client
    private ListView<String> panierList;
    private Label totalLabel;
    private Label fideliteLabel;

    // Stock
    private TableView<InventaireStock> stockTable;

    public MainView(Magasin magasin) {
        this.magasin = magasin;

        setPadding(new Insets(10));
        setTop(createTopBar());

        pageHub = createHubPage();
        pageBlackJack = createBlackJackPage();
        pageMemory = createMemoryPage();
        pageHangMan= createHangManPage();
        pageTrueOrFalse = createTrueOrFalsePage();
        pageMoreOrLess = createMoreOrLessPage();

        pages.getChildren().addAll(pageHub,pageBlackJack, pageMemory, pageHangMan, pageTrueOrFalse,pageMoreOrLess);
        show(pageHub);

        setCenter(pages);
        setBottom(createBottomBar());
    }

    // BARRE DU HAUT
    private Node createTopBar() {
        Label title = new Label("MultiGames");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Button btnHub = new Button("Hub");
        Button btnBlackJack = new Button("BlackJack");
        Button btnMemory = new Button("Memory");
        Button btnHangMan = new Button("HangMan");
        Button btnTrueOrFalse = new Button("True or False");
        Button btnMoreOrLess = new Button("More or Less");

        btnHub.setOnAction(e ->  show(pageHub));
        btnBlackJack.setOnAction(e -> { show(pageBlackJack); refreshBlackJack(); });
        btnMemory.setOnAction(e -> { show(pageMemory); refreshMemory(); });
        btnHangMan.setOnAction(e -> { show(pageHangMan); refreshHangMan(); });
        btnTrueOrFalse.setOnAction(e -> { show(pageTrueOrFalse); refreshTrueOrFalse(); });
        btnTrueOrFalse.setOnAction(e -> { show(pageMoreOrLess); refreshMoreOrLess(); });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox top = new HBox(10, title, spacer,
                btnHub,btnBlackJack,btnMemory,btnHangMan,btnTrueOrFalse,btnMoreOrLess);
        top.setAlignment(Pos.CENTER_LEFT);
        top.setPadding(new Insets(0, 0, 10, 0));

        return top;
    }

    private void show(Node page) {
        for (Node n : pages.getChildren()) {
            n.setVisible(false);
            n.setManaged(false);
        }
        page.setVisible(true);
        page.setManaged(true);
    }

    // BARRE DU BAS
    private Node createBottomBar() {
        argentLabel = new Label();
        HBox box = new HBox(argentLabel);
        box.setPadding(new Insets(10, 0, 0, 0));
        return box;
    }

    private Node createHubPage() {

    }

    // PAGE BlackJack
    private Node createBlackJackPage() {

        return null;
    }

    // PAGE Memory
    private Node createMemoryPage() {

        return null;
    }

    // PAGE HangMan
    private Node createHangManPage() {

        return null;
    }

    // PAGE TrueOrFalse
    private Node createTrueOrFalsePage() {

        return null;
    }

    // PAGE MoreOrLESS
    private Node createMoreOrLessPage() {

        return null;
    }

    // RAFRAÎCHISSEMENTS

    private void refreshBlackJack() {

    }

    private void refreshMemory() {

    }

    private void refreshHangMan() {

    }

    private void refreshMoreOrLess() {

    }

    private void refreshTrueOrFalse() {

    }
}
