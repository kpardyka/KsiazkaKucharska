package controller;

import app.Main;
import db.RecipesDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable{
    public static String user;

    @FXML
    private UserNameController userNameController;

    private RecipesDatabase database;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameController.getSetName().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user = userNameController.getNameText().getText();
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(Main.mainView);
            }
        });

    }

    public void setDatabase(RecipesDatabase database) {
        this.database = database;
    }

}
