package monopoly.functional.squares;

public class Service extends SpecialProperty {

    public Service(int position, int price, String name) {
        super(position, price, name);
    }    
    //13, 29

    public int getRent(int owned, int dice) {
        if (owned == 1)
            return dice * 4;
        else
            return dice * 10;
    }

}
