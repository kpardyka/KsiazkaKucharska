package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserNameController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private TextField nameText;

    @FXML
    private Button setName;

    public Label getName() {
        return name;
    }

    public TextField getNameText() {
        return nameText;
    }

    public Button getSetName() {
        return setName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
