package sample.controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main view controller
 * Used to store other view controllers and manage scenes and layout of the app.
 * @author Dominik Drag
 */
public class MainViewController {
    private Stage mainStage;

    private BorderPane mainLayout = new BorderPane();

    private TableViewController tableViewController = new TableViewController();
    private SideMenuViewController sideMenuViewController = new SideMenuViewController();

    public VBox tableViewBox = tableViewController.loadTableView();
    public VBox sideMenu = sideMenuViewController.loadSideMenu();

    /**
     * @param stage stage to be set as a main stage
     */
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    /**
     * sets up the main scene of the app
     */
    public void setupMainScene() {
        sideMenuViewController.setMainViewController(this);
        mainLayout.setCenter(tableViewBox);
        mainLayout.setLeft(sideMenu);

        Scene mainScene = new Scene(mainLayout, 800, 400);

        mainStage.setTitle("Stocker");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /**
     * @param node node to be set in the center of the layout
     */
    public void setCenter(Node node) {
        mainLayout.setCenter(node);
    }
}
