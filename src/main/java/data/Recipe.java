package data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import data.enums.Cost;
import data.enums.Difficulty;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "_class")
public class Recipe implements Likeable, Serializable {

    static final long serialVersionUID = 1L;

    @MongoObjectId
    private String  _id;

    private String name;
    private String portion;
    private Cost cost;
    private String time;
    private String user;
    private Difficulty difficulty;
    private String category;
    private List<Ingredient> ingredients;
    private List<String> directions;

    private Set<String> likes;

    public Recipe() {
    }

    public Recipe(String name, String portion, Cost cost, String time, String user, Difficulty difficulty, String category,
                  List<Ingredient> ingredients, List<String> directions, Set<String> likes) {
        this.name = name;
        this.portion = portion;
        this.cost = cost;
        this.time = time;
        this.user = user;
        this.difficulty = difficulty;
        this.category = category;
        this.ingredients = ingredients;
        this.directions = directions;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return name + " " + portion + "; "
                + cost.getName() + "; "
                + time + "; "
                + user + "; "
                + difficulty.getName() + "; "
                + category + "; "
                + ingredients + "; "
                + directions + "; "
                + likes + "\n";
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Cost getCost() {
        return cost;
    }

    public String getPortion() {
        return portion;
    }

    public String getCategory() {
        return category;
    }

    public String getUser() {
        return user;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public Set<String> getLikes() {
        return likes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public void setLikes(Set<String> likes) {
        this.likes = likes;
    }

    @Override
    public void like(String user) {
        likes.add(user);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

}
