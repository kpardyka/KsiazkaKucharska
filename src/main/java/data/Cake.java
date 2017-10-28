package data;

import data.enums.Cost;
import data.enums.Difficulty;

import java.util.List;
import java.util.Set;

public class Cake extends Recipe {

    static final long serialVersionUID = 1L;

    private int timeOfCook;
    private int temperature;

    public int getTimeOfCook() {
        return timeOfCook;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTimeOfCook(int timeOfCook) {
        this.timeOfCook = timeOfCook;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Cake() {
    }

    public Cake(String name, String portion, Cost cost, String time, String user, Difficulty difficulty, String category, List<Ingredient> ingredients, List<String> directions, Set<String> likes, int timeOfCook, int temperature) {
        super(name, portion, cost, time, user, difficulty, category, ingredients, directions, likes);
        this.timeOfCook = timeOfCook;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return getName() + " " + getPortion() + "; "
                + getCost().getName() + "; "
                + getTime() + "; "
                + getUser() + "; "
                + getDifficulty().getName() + "; "
                + getCategory() + "; "
                + getIngredients() + "; "
                + getDirections() + "; "
                + getLikes() +"; " + timeOfCook + "; " + temperature + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cake)) return false;
        if (!super.equals(o)) return false;

        Cake cake = (Cake) o;

        if (timeOfCook != cake.timeOfCook) return false;
        return temperature == cake.temperature;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + timeOfCook;
        result = 31 * result + temperature;
        return result;
    }
}
