import data.Ingredient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void whenIngredientCreatedEveryFieldIsProperlySet(){
        Ingredient ingredient = new Ingredient("woda", "2l");
        assertEquals("woda", ingredient.getName());
        assertEquals("2l",ingredient.getQuantity());
    }
}
