package monopoly.functional.squares;

import monopoly.functional.Player;

public class Property extends Square{
    
    //Esto todavia no se debe utilizar porque seguramente van a haber cambios (Esperando respuesta del profe)
    private Player owner;
    private int price, pricePerHouse, rent, rent1House, rent2House, rent3House, rent4House;

    public Property(int position, String name) {
        super(position, name);
    }
    
    
}
