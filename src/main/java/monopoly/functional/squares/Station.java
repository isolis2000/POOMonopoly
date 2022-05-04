package monopoly.functional.squares;

public class Station extends SpecialProperty {

    public Station(int position, int price, String name) {
        super(position, price, name);
    }
    //6, 16, 26, 36
    
    public int getRent(int owned) {
        int res = 25;
        for (int i = 1; i < owned; i++) {
            res *= 2;
        }
        return res;
    }

}
