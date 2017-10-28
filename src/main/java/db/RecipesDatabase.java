package db;

import data.Recipe;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RecipesDatabase {
    MongoCollection database;

    public RecipesDatabase(MongoCollection database) {
        this.database = database;
    }

    public void saveRecipe(Recipe recipe) {
        database.save(recipe);
    }

    public void removeRecipe(Recipe recipeToRemove) {
        database.remove(new ObjectId(recipeToRemove.get_id()));
    }

    public void editRecipe(Recipe recipeToEdit, Recipe newRecipe) {
        database.update(new ObjectId(recipeToEdit.get_id())).with(newRecipe);
    }

    public List<Recipe> getAllRecipes() {
        MongoCursor<Recipe> as = database.find().as(Recipe.class);
        List<Recipe> recipeList = new ArrayList<>();

        while (as.hasNext()) {
            Recipe next = as.next();
            recipeList.add(next);
        }
        try {
            as.close();
        } catch (IOException e) {
            System.out.println("Błąd podczas konwertowania danych z bazy do listy");
        }
        return recipeList;
    }


}
