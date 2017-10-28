package filters;

import data.Recipe;

import java.util.Comparator;

public class RecipeComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe o1, Recipe o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        Integer i1 = o1.getLikes().size();
        Integer i2 = o2.getLikes().size();
        return -i1.compareTo(i2);

    }
}
