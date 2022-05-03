package monopoly.functional.squares;

import monopoly.functional.Player;

public enum Property {
    
//    //Esto todavia no se debe utilizar porque seguramente van a haber cambios (Esperando respuesta del profe)
    OldKentRd(60, 50, 2, 10, 30, 90, 160,250, "Old Kent RD"),
    WhiteChapelRD(60, 50, 4, 20, 60, 180, 320, 450, "White Chapel RD");
    
    private int price, pricePerHouse, rent, rent1House, rent2House, rent3House, rent4House, rentHotel;
    private String name;

    private Property(int price, int pricePerHouse, int rent, int rent1House, int rent2House, int rent3House, int rent4House, int rentHotel, String name) {
        this.price = price;
        this.pricePerHouse = pricePerHouse;
        this.rent = rent;
        this.rent1House = rent1House;
        this.rent2House = rent2House;
        this.rent3House = rent3House;
        this.rent4House = rent4House;
        this.rentHotel = rentHotel;
        this.name = name;
        
    }

    public static Property getOldKentRd() {
        return OldKentRd;
    }

    public int getPrice() {
        return price;
    }

    public int getPricePerHouse() {
        return pricePerHouse;
    }

    public int getRent() {
        return rent;
    }

    public int getRent1House() {
        return rent1House;
    }

    public int getRent2House() {
        return rent2House;
    }

    public int getRent3House() {
        return rent3House;
    }

    public int getRent4House() {
        return rent4House;
    }

    public int getRentHotel() {
        return rentHotel;
    }

    public String getName() {
        return name;
    }
    

    
    
        
    
    
}
