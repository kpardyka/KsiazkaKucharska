import data.Ingredient;
import data.Recipe;
import data.enums.Cost;
import data.enums.Difficulty;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class RecipeTest {

    private Recipe recipe;
    private List<Ingredient> ingredients;
    private List<String> directions;

    @Before
    public void before() {
        ingredients = Arrays.asList(new Ingredient("Woda", "2 litry"), new Ingredient("Sól", "szczypta"));
        directions = Arrays.asList("Podgrzać wodę", "Posolić do smaku");
        recipe = new Recipe("Przepis", "6 osób", Cost.EXPENSIVE, "30 minut", "Testowy użytkownik", Difficulty.MEDIUM, "Zupa", ingredients, directions, new HashSet<>());
    }

    @Test
    public void whenRecipeCreatedEveryFieldIsProperlySet() {
        assertEquals("Przepis", recipe.getName());
        assertEquals("6 osób", recipe.getPortion());
        assertEquals(Cost.EXPENSIVE, recipe.getCost());
        assertEquals("30 minut", recipe.getTime());
        assertEquals("Testowy użytkownik", recipe.getUser());
        assertEquals(Difficulty.MEDIUM, recipe.getDifficulty());
        assertEquals("Zupa", recipe.getCategory());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(directions, recipe.getDirections());
        assertNotNull(recipe.getLikes());
        assertTrue(recipe.getLikes().isEmpty());
    }

    @Test
    public void whenRecipeGetLikeFromUserThenLikeIsVisibleInRecipe() {
        assertTrue(recipe.getLikes().isEmpty());
        recipe.like("Test");
        assertFalse(recipe.getLikes().isEmpty());
        assertTrue(recipe.getLikes().contains("Test"));
    }

    @Test
    public void whenRecipeGetTwoLikesFromSameUserThenOnlyOneIsVisible() {
        assertTrue(recipe.getLikes().isEmpty());
        recipe.like("Test");
        recipe.like("Test");
        assertTrue(recipe.getLikes().contains("Test"));
        assertEquals(1, recipe.getLikes().size());
    }

}
