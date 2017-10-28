package filters;

import data.Recipe;
import data.enums.Cost;
import data.enums.Difficulty;

import java.util.List;
import java.util.stream.Collectors;

public class RecipesFilter {

    public List<Recipe> filterByNameContaining(String name, List<Recipe> allRecipes) {
        return allRecipes.stream().filter(recipe -> recipe.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Recipe> filterByCategory(String category, List<Recipe> allRecipes) {
        return allRecipes.stream().filter(recipe -> recipe.getCategory().equals(category)).collect(Collectors.toList());
    }

    public List<Recipe> filterByCost(Cost cost, List<Recipe> allRecipes) {
        return allRecipes.stream().filter(recipe -> recipe.getCost().getName().equals(cost.getName())).collect(Collectors.toList());
    }

    public List<Recipe> filterByDifficulty(Difficulty difficulty, List<Recipe> allRecipes) {
        return allRecipes.stream().filter(recipe -> recipe.getDifficulty().getName().equals(difficulty.getName())).collect(Collectors.toList());
    }

    public List<Recipe> filterByIngredientsContaining(String name, List<Recipe> allRecipes) {
        return allRecipes.stream().filter(
                recipe -> recipe.getIngredients().stream().anyMatch(ingredient -> ingredient.getName().contains(name))
        ).collect(Collectors.toList());
    }

    public List<Recipe> filterByUser(String string, List<Recipe> allRecipes){
        return allRecipes.stream().filter(recipe -> recipe.getUser().equalsIgnoreCase(string)). collect(Collectors.toList());
    }

    public List<Recipe> filterByLikes(String string, List<Recipe> allRecipes){
        return allRecipes.stream().filter(recipe -> recipe.getLikes().contains(string)).collect(Collectors.toList());
    }


    public List<Recipe> filterByAnything(String string, List<Recipe> allRecipes) {
        return allRecipes.stream().filter(recipe -> recipe.toString().contains(string)).collect(Collectors.toList());
    }

    public int countLikes(Recipe recipe) {
       return recipe.getLikes().size();
    }

    public List<Recipe> mostPopular(List<Recipe> allRecipes){
        RecipeComparator recipeComparator = new RecipeComparator();
        return allRecipes.stream().sorted((Recipe a,Recipe b) ->recipeComparator.compare(a,b)).collect(Collectors.toList()).stream().limit(5).collect(Collectors.toList());
    }



}



