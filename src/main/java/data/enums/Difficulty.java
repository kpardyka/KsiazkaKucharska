package data.enums;

public enum Difficulty {
    EASY("Łatwe"), MEDIUM("Średnie"), HARD("Trudne");

    private String name;

    public static Difficulty getDifficulty(String name) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.name.equals(name)) {
                return difficulty;
            }
        }
        return null;
    }

    Difficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
