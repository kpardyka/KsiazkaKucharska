import data.Ingredient;
import data.Recipe;
import data.enums.Cost;
import data.enums.Difficulty;
import filters.RecipesFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class RecipesFilterTest {

    private Recipe recipe;
    private Recipe recipe2, recipe3;
    private List<Recipe> recipes;
    private List<Ingredient> ingredients, ingredients2;
    private List<String> directions;
    private RecipesFilter filter;

    @Before
    public void before() {
        filter = new RecipesFilter();
        ingredients = Arrays.asList(new Ingredient("Woda", "2 litry"), new Ingredient("Sól", "szczypta"));
        ingredients2 = Arrays.asList(new Ingredient("Woda", "2 litry"), new Ingredient("Sól", "szczypta"), new Ingredient("Mięso", "1kg"));
        directions = Arrays.asList("Podgrzać wodę", "Posolić do smaku");
        recipe = new Recipe("Przepis", "6 osób", Cost.EXPENSIVE, "30 minut", "Testowy użytkownik", Difficulty.MEDIUM, "Zupa", ingredients, directions, new HashSet<>());
        recipes = new ArrayList<>();
        recipe2 = new Recipe("Przykładowa zupa", "6 talerzy", Cost.CHEAP, "60 minut", "Testowy", Difficulty.EASY,"Zupa", ingredients2, directions, new HashSet<>());
        recipe3 = new Recipe("Przykładowe danie główne", "6 talerzy", Cost.CHEAP, "60 minut", "Testowy", Difficulty.MEDIUM,"Danie główne", ingredients, directions, new HashSet<>());
        recipes.add(recipe);
        recipes.add(recipe2);
        recipes.add(recipe3);
    }

    @Test
    public void whenFilterByNameAndAnyRecipeContainItThenShouldReturnEmptyList(){
        assertThat(filter.filterByNameContaining("Test", recipes), empty());
    }

    @Test
    public void whenFilterByNameThenShouldGiveListWithPropertyRecipe(){
        assertThat(filter.filterByNameContaining("zupa", recipes), hasItems(recipe2));
        assertThat(filter.filterByNameContaining("zupa", recipes).size(),is(1));
    }

    @Test
    public void whenFilterByCategoryAndAnyRecipeHaveThatCategoryThenShouldReturnEmptyList(){
        assertThat(filter.filterByCategory("Deser", recipes), empty());
    }


    @Test
    public void whenFilterByCategoryShouldReturnList(){
               assertThat(filter.filterByCategory("Zupa", recipes).size(), is(2));
               assertThat(filter.filterByCategory("Zupa", recipes), containsInAnyOrder(recipe, recipe2));
    }


    @Test
    public void whenFilterByCostShouldReturnList(){
        assertThat(filter.filterByCost(Cost.CHEAP,recipes),containsInAnyOrder(recipe2,recipe3));
        assertThat(filter.filterByCost(Cost.CHEAP,recipes).size(), is(2));
    }

    @Test
    public void whenFilterByDifficultyShouldReturnList(){
        assertThat(filter.filterByDifficulty(Difficulty.EASY,recipes), containsInAnyOrder(recipe2));
        assertThat(filter.filterByDifficulty(Difficulty.EASY,recipes).size(), is(1));
    }

    @Test
    public void whenFilterByIngredientsThatAnyRecipeContainingShouldReturnEmptyList(){
        assertThat(filter.filterByIngredientsContaining("kurczak", recipes), empty());
        List<Recipe> woda = filter.filterByIngredientsContaining(new Ingredient("Woda", "2 litry").getName(), recipes);
        assertThat(woda, containsInAnyOrder(recipe2, recipe, recipe3));
        assertThat(woda.size(), is(3));

    }
}
