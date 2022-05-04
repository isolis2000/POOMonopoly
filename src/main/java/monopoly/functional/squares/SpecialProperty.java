package monopoly.functional.squares;

public class SpecialProperty {
    
    private final int position, price;
    private final String name;

    public SpecialProperty(int position, int price, String name) {
        this.position = position;
        this.price = price;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    
}
