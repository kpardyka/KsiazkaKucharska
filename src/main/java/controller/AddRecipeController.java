package controller;

import app.Main;
import data.Ingredient;
import data.Recipe;
import data.enums.Cost;
import data.enums.Difficulty;
import db.RecipesDatabase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private Label portion;

    @FXML
    private Label cost;

    @FXML
    private Label time;

    @FXML
    private Label difficulty;

    @FXML
    private Label category;

    @FXML
    private TextField nameText;

    @FXML
    private TextField portionText;

    @FXML
    private TextField timeText;

    @FXML
    private TextField categoryText;

    @FXML
    private Button addRecipe;

    @FXML
    private ComboBox<Cost> costBox;

    @FXML
    private ComboBox<Difficulty> difficultyBox;

    @FXML
    private Label ingredients;

    @FXML
    private Label directions;

    @FXML
    private TextField ingredientName;

    @FXML
    private TextField ingredientQuantity;

    @FXML
    private TextField directionText;

    @FXML
    private Button addIngredient;

    @FXML
    private Button addDirection;

    @FXML
    private Button backToMainMenu;

    private RecipesDatabase database;

    public Label getName() {
        return name;
    }

    public Label getPortion() {
        return portion;
    }

    public Label getCost() {
        return cost;
    }


    public Label getDifficulty() {
        return difficulty;
    }


    public ComboBox<Cost> getCostBox() {
        return costBox;
    }

    public ComboBox<Difficulty> getDifficultyBox() {
        return difficultyBox;
    }

    public Label getIngredients() {
        return ingredients;
    }

    public Label getDirections() {
        return directions;
    }


    public void setDatabase(RecipesDatabase database) {
        this.database = database;
    }

    public RecipesDatabase getDatabase() {
        return database;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Ingredient> ingredientList = new ArrayList<>();
        List<String> directionsList = new ArrayList<>();

        difficultyBox.setItems(FXCollections.observableArrayList(Difficulty.values()));
        costBox.setItems(FXCollections.observableArrayList(Cost.values()));

        addIngredient.setOnAction(event1 -> {
            ingredientList.add(new
                    Ingredient(ingredientName.getText(),
                    ingredientQuantity.getText()));
            ingredientName.clear();
            ingredientQuantity.clear();
        });
        addDirection.setOnAction(event2 -> {
            directionsList.add(directionText.getText());
            directionText.clear();
        });


        addRecipe.setOnAction(new EventHandler<ActionEvent>() {
            Recipe recipe;

            @Override
            public void handle(ActionEvent event) {
                recipe = new Recipe(nameText.getText(), portionText.getText(), getCostBox().getValue(),
                        timeText.getText(), MainPaneController.user, getDifficultyBox().getValue(),
                        categoryText.getText(), ingredientList, directionsList, new HashSet<>());
                database.saveRecipe(recipe);
            }
        });

        backToMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(Main.mainView);
            }
        });
    }
}
