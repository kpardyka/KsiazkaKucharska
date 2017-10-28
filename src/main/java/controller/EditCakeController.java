package controller;

import app.Main;
import data.Cake;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;


public class EditCakeController implements Initializable {
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
    private Label timeOfCooking;

    @FXML
    private Label temperatureOfCooking;

    @FXML
    private TextField nameText;

    @FXML
    private TextField portionText;

    @FXML
    private TextField timeText;

    @FXML
    private TextField categoryText;

    @FXML
    private TextField timeOfCookingText;

    @FXML
    private TextField temperatureOfCookingText;

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
        if(recipe instanceof Cake) {
            Cake cake = (Cake) recipe;
            temperatureOfCookingText.setText(String.valueOf(cake.getTemperature()));
            timeOfCookingText.setText(String.valueOf(cake.getTimeOfCook()));
        }

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
                recipe2 = new Cake(nameText.getText(), portionText.getText(), getCostBox().getValue(), timeText.getText(),
                        MainPaneController.user, getDifficultyBox().getValue(), categoryText.getText(),
                        ingredientList, directionsList, new HashSet<>(),
                        Integer.parseInt(timeOfCookingText.getText()), Integer.parseInt(temperatureOfCookingText.getText()));
                database.editRecipe(recipe, recipe2);
            }

        });
        backToMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(Main.mainView);
            }
        });
    }

    public ComboBox<Cost> getCostBox() {
        return costBox;
    }

    public ComboBox<Difficulty> getDifficultyBox() {
        return difficultyBox;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}