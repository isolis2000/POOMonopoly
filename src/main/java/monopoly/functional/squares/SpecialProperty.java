package monopoly.functional.squares;

public class SpecialProperty {
    
    private final int position, price;
    private final String name, type;

    public SpecialProperty(int position, int price, String name, String type) {
        this.position = position;
        this.price = price;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }
    
    public int getRent(int owned, int dice) {
        if (type.equals("Station"))
            return getRentStation(owned);
        else
            return getRentService(owned, dice);
    }
    
    public int getRentService(int owned, int dice) {
        if (owned == 1)
            return dice * 4;
        else
            return dice * 10;
    }
    
    public int getRentStation(int owned) {
        int res = 25;
        for (int i = 1; i < owned; i++) {
            res *= 2;
        }
        return res;
    }
    
}
