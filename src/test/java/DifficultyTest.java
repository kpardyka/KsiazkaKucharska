import data.enums.Difficulty;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DifficultyTest {

    @Test
    public void whenDifficultyCreatedThenShouldBePropertyNameGiven(){
        assertEquals(Difficulty.EASY.getName(),"Łatwe");
        assertEquals(Difficulty.MEDIUM.getName(),"Średnie");
        assertEquals(Difficulty.HARD.getName(),"Trudne");
    }
}
