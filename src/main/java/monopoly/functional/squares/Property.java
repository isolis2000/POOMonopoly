package monopoly.functional.squares;

import monopoly.functional.Player;

public enum Property {
    
//    //Esto todavia no se debe utilizar porque seguramente van a haber cambios (Esperando respuesta del profe)
    OldKentRd(1, 1, 1, 1, 1, 1, 1);
    private int price, pricePerHouse, rent, rent1House, rent2House, rent3House, rent4House;

    private Property(int price, int pricePerHouse, int rent, int rent1House, int rent2House, int rent3House, int rent4House) {
        this.price = price;
        this.pricePerHouse = pricePerHouse;
        this.rent = rent;
        this.rent1House = rent1House;
        this.rent2House = rent2House;
        this.rent3House = rent3House;
        this.rent4House = rent4House;
    }

    
    private enum rents {
        Property(60, 50, 2, 10, 30, 90, 160, 2, "Old Kend Road");
    }
        
    
    
}
