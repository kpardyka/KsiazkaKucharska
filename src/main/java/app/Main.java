package app;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import controller.*;
import data.Recipe;
import db.RecipesDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.io.IOException;

public class Main extends Application {

    private Scene mainPane;
    public static Scene mainView;
    public static Scene addCake;
    public static Scene addRecipe;
    public static Recipe selectedRecipe;
    private static FXMLLoader editRecipeLoader;
    private static RecipesDatabase recipesDatabase;
    private static FXMLLoader editCakeLoader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final String appName = "Książka kucharska";

        DB db = new MongoClient().getDB("recipeapp");
        Jongo jongo = new Jongo(db);
        MongoCollection recipes = jongo.getCollection("recipes");
        recipesDatabase = new RecipesDatabase(recipes);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainPane.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            MainPaneController controller = fxmlLoader.<MainPaneController>getController();
            controller.setDatabase(recipesDatabase);

            fxmlLoader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
            Parent parentMainView = (Parent) fxmlLoader.load();
            MainViewController mainViewController = fxmlLoader.<MainViewController>getController();
            mainViewController.setDatabase(recipesDatabase);

            fxmlLoader = new FXMLLoader(getClass().getResource("/AddRecipe.fxml"));
            Parent parentAddRecipe = (Parent) fxmlLoader.load();
            AddRecipeController addRecipeController = fxmlLoader.<AddRecipeController>getController();
            addRecipeController.setDatabase(recipesDatabase);

            fxmlLoader = new FXMLLoader(getClass().getResource("/AddCake.fxml"));
            Parent parentAddCake = (Parent) fxmlLoader.load();
            AddCakeController addCakeController = fxmlLoader.<AddCakeController>getController();
            addCakeController.setDatabase(recipesDatabase);

            editCakeLoader = new FXMLLoader(getClass().getResource("/EditCake.fxml"));


            editRecipeLoader = new FXMLLoader(getClass().getResource("/EditRecipe.fxml"));

            mainPane = new Scene(parent);
            mainView = new Scene(parentMainView, 1000, 600);
            addCake = new Scene(parentAddCake);
            addRecipe = new Scene(parentAddRecipe);


            primaryStage.setTitle(appName);
            primaryStage.setScene(mainPane);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Scene getEditRecipeScene() throws IOException {
        Parent parentEditRecipe = (Parent) editRecipeLoader.load();
        EditRecipeController editRecipeController = editRecipeLoader.<EditRecipeController>getController();
        editRecipeController.setDatabase(recipesDatabase);
        editRecipeController.setRecipe(selectedRecipe);
        return new Scene(parentEditRecipe);
    }

    public static Scene getEditCakeScene() throws IOException {
        Parent parentEditRecipe = (Parent) editCakeLoader.load();
        EditCakeController editCakeController = editCakeLoader.<EditCakeController>getController();
        editCakeController.setDatabase(recipesDatabase);
        editCakeController.setRecipe(selectedRecipe);
        return new Scene(parentEditRecipe);
    }

}
