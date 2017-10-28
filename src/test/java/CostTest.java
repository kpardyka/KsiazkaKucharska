import data.enums.Cost;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostTest {

    @Test
    public void whenCostIsUsingThenShouldBePropertyNameGiven(){
        assertEquals(Cost.CHEAP.getName(),"Tanie");
        assertEquals(Cost.MEDIUM_COST.getName(),"Średni koszt");
        assertEquals(Cost.EXPENSIVE.getName(), "Drogie");
    }
}
