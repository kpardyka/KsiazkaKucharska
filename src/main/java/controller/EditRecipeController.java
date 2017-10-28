package controller;

import app.Main;
import data.Ingredient;
import data.Recipe;
import data.enums.Cost;
import data.enums.Difficulty;
import db.RecipesDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class EditRecipeController implements Initializable {
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
    private Button editRecipe;

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
    private Button removeDirections;
    @FXML
    private Button removeIngredients;

    @FXML
    private Button backToMainMenu;

    private RecipesDatabase database;
    private Recipe recipe;

    public void setDatabase(RecipesDatabase database) {
        this.database = database;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipe = Main.selectedRecipe;
        List<Ingredient> ingredientList = recipe.getIngredients();
        List<String> directionsList = recipe.getDirections();
        nameText.setText(recipe.getName());
        portionText.setText(recipe.getPortion());
        categoryText.setText(recipe.getCategory());
        timeText.setText(recipe.getTime());
        difficultyBox.setValue(recipe.getDifficulty());
        costBox.setValue(recipe.getCost());

        removeIngredients.setOnAction(event -> ingredientList.clear());
        addIngredient.setOnAction(event -> {
            ingredientList.add(new
                    Ingredient(ingredientName.getText(),
                    ingredientQuantity.getText()));
            ingredientName.clear();
            ingredientQuantity.clear();
        });

        removeDirections.setOnAction(event -> directionsList.clear());
        addDirection.setOnAction(event -> {
            directionsList.add(directionText.getText());
            directionText.clear();
        });


        editRecipe.setOnAction(new EventHandler<ActionEvent>() {
            Recipe recipe2;

            @Override
            public void handle(ActionEvent event) {
                recipe2 = new Recipe(nameText.getText(), portionText.getText(), getCostBox().getValue(),
                        timeText.getText(), MainPaneController.user, getDifficultyBox().getValue(),
                        categoryText.getText(), ingredientList, directionsList, new HashSet<>());
                database.editRecipe(recipe, recipe2);
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

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public ComboBox<Cost> getCostBox() {
        return costBox;
    }

    public ComboBox<Difficulty> getDifficultyBox() {
        return difficultyBox;
    }
}
