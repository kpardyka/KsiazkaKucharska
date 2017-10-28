package controller;

import app.Main;
import data.Cake;
import data.Ingredient;
import data.Recipe;
import data.enums.Cost;
import data.enums.Difficulty;
import db.RecipesDatabase;
import filters.RecipesFilter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private static final String NAME = "Nazwa";
    private static final String PORTION = "Liczba porcji";
    private static final String COST = "Koszt";
    private static final String TIME = "Czas";
    private static final String USER = "Autor";
    private static final String DIFFICULTY = "Trudność";
    private static final String CATEGORY = "Kategoria";
    private static final String INGREDIENTS = "Składniki";
    private static final String DIRECTIONS = "Sposób przyrządzenia";
    private static final String LIKES = "Polubili";
    private static final String TIME_OF_COOKING = "Czas pieczenia";
    private static final String TEMPERATURE = "Temperatura";

    @FXML
    private Button byName;

    @FXML
    private TextField filterText;

    @FXML
    private Button filterByUser;

    @FXML
    private Button filterByLikes;

    @FXML
    private Button byCategory;

    @FXML
    private Button byIngredients;

    @FXML
    private ComboBox<Cost> byCost;

    @FXML
    private ComboBox<Difficulty> byDifficulty;

    @FXML
    private ComboBox<?> byUsersAndLikes;

    @FXML
    private Button addRecipe;

    @FXML
    private Button addCake;

    @FXML
    private Button removeRecipe;

    @FXML
    private Button editRecipe;

    @FXML
    private Button likeRecipe;

    @FXML
    private TableView<Recipe> recipeList;

    @FXML
    private Button readRecipes;

    @FXML
    private Button getMostLikedRecipes;

    private RecipesDatabase database;
    private RecipesFilter filter;

    public Button getByName() {
        return byName;
    }

    public Button getByCategory() {
        return byCategory;
    }

    public Button getByIngredients() {
        return byIngredients;
    }


    public Button getAddRecipe() {
        return addRecipe;
    }

    public Button getRemoveRecipe() {
        return removeRecipe;
    }

    public Button getEditRecipe() {
        return editRecipe;
    }

    public Button getLikeRecipe() {
        return likeRecipe;
    }

    public Button getReadRecipes() {
        return readRecipes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();

        readRecipes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateRecipeList();
            }
        });

        addRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(Main.addRecipe);
            }
        });

        addCake.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(Main.addCake);
            }
        });

        editRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Recipe recipe = recipeList.getSelectionModel().getSelectedItem();
                Main.selectedRecipe = recipe;
                recipeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                try {
                    if (recipe instanceof Cake) {
                        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageTheEventSourceNodeBelongs.setScene(Main.getEditCakeScene());
                    } else {
                        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stageTheEventSourceNodeBelongs.setScene(Main.getEditRecipeScene());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        removeRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Recipe recipe = recipeList.getSelectionModel().getSelectedItem();
                Main.selectedRecipe = recipe;
                database.removeRecipe(recipe);
                updateRecipeList();
            }
        });

        likeRecipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Recipe recipe = recipeList.getSelectionModel().getSelectedItem();
                Main.selectedRecipe = recipe;
                recipe.like(MainPaneController.user);
                database.editRecipe(recipe, recipe);
                updateRecipeList();
            }
        });
        byDifficulty.setItems(FXCollections.observableArrayList(Difficulty.values()));
        byCost.setItems(FXCollections.observableArrayList(Cost.values()));
        byCost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter = new RecipesFilter();
                List<Recipe> recipeList = filter.filterByCost(byCost.getValue(), database.getAllRecipes());
                updateRecipeList(recipeList);

            }
        });

        byDifficulty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter = new RecipesFilter();
                List<Recipe> recipeList = filter.filterByDifficulty(byDifficulty.getValue(), database.getAllRecipes());
                updateRecipeList(recipeList);
            }
        });
        byCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter = new RecipesFilter();
                List<Recipe> recipeList = filter.filterByCategory(filterText.getText(), database.getAllRecipes() );
                updateRecipeList(recipeList);
            }
        });
        byIngredients.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter=new RecipesFilter();
                List<Recipe> recipeList = filter.filterByIngredientsContaining(filterText.getText(), database.getAllRecipes());
                updateRecipeList(recipeList);
            }
        });
        byName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter=new RecipesFilter();
                List<Recipe> recipeList = filter.filterByNameContaining(filterText.getText(), database.getAllRecipes());
                updateRecipeList(recipeList);
            }
        });

        filterByUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter = new RecipesFilter();
                List<Recipe> recipeList = filter.filterByUser(MainPaneController.user, database.getAllRecipes());
                updateRecipeList(recipeList);
            }
        });

        filterByLikes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter = new RecipesFilter();
                List<Recipe> recipeList = filter.filterByLikes(MainPaneController.user, database.getAllRecipes());
                updateRecipeList(recipeList);
            }
        });

        getMostLikedRecipes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filter = new RecipesFilter();
                List<Recipe> recipeList = filter.mostPopular(database.getAllRecipes());
                updateRecipeList(recipeList);
            }
        });

    }

    private void updateRecipeList() {
        List<Recipe> allRecipes = database.getAllRecipes();
        recipeList.getItems().setAll(allRecipes);
    }

    private void updateRecipeList(List<Recipe> recipes) {
        recipeList.getItems().setAll(recipes);
    }

    private void configureTable() {
        TableColumn<Recipe, String> nameColumn = new TableColumn<Recipe, String>(NAME);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Recipe, String> portionColumn = new TableColumn<Recipe, String>(PORTION);
        portionColumn.setCellValueFactory(new PropertyValueFactory<>("portion"));

        TableColumn<Recipe, Cost> costColumn = new TableColumn<Recipe, Cost>(COST);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn<Recipe, String> timeColumn = new TableColumn<Recipe, String>(TIME);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Recipe, String> userColumn = new TableColumn<Recipe, String>(USER);
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        TableColumn<Recipe, Difficulty> difficultyColumn = new TableColumn<Recipe, Difficulty>(DIFFICULTY);
        PropertyValueFactory<Recipe, Difficulty> difficulty = new PropertyValueFactory<>("difficulty");
        difficultyColumn.setCellValueFactory(difficulty);

        TableColumn<Recipe, String> categoryColumn = new TableColumn<Recipe, String>(CATEGORY);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Recipe, List<Ingredient>> ingredientColumn = new TableColumn<Recipe, List<Ingredient>>(INGREDIENTS);
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredients"));

        TableColumn<Recipe, List<String>> directionColumn = new TableColumn<Recipe, List<String>>(DIRECTIONS);
        directionColumn.setCellValueFactory(new PropertyValueFactory<>("directions"));

        TableColumn<Recipe, List<String>> likesColumn = new TableColumn<Recipe, List<String>>(LIKES);
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));

        TableColumn<Recipe, Integer> timeOfCookingColumn = new TableColumn<Recipe, Integer>(TIME_OF_COOKING);
        timeOfCookingColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfCook"));

        TableColumn<Recipe, Integer> temperatureOfCookingColumn = new TableColumn<Recipe, Integer>(TEMPERATURE);
        temperatureOfCookingColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));

        recipeList.getColumns().add(nameColumn);
        recipeList.getColumns().add(portionColumn);
        recipeList.getColumns().add(costColumn);
        recipeList.getColumns().add(timeColumn);
        recipeList.getColumns().add(userColumn);
        recipeList.getColumns().add(difficultyColumn);
        recipeList.getColumns().add(categoryColumn);
        recipeList.getColumns().add(ingredientColumn);
        recipeList.getColumns().add(directionColumn);
        recipeList.getColumns().add(likesColumn);
        recipeList.getColumns().add(timeOfCookingColumn);
        recipeList.getColumns().add(temperatureOfCookingColumn);
    }

    public void setDatabase(RecipesDatabase database) {
        this.database = database;
    }


}
