package data.enums;

public enum Cost {
    CHEAP("Tanie"), MEDIUM_COST("Średni koszt"), EXPENSIVE("Drogie");

    private String name;

    public static Cost getCost(String name) {
        for(Cost cost : Cost.values()) {
            if(cost.name.equals(name)) {
                return cost;
            }
        }
        return null;
    }


    Cost(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
