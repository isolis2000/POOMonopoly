package monopoly.functional.squares;

import monopoly.functional.Player;

public enum Property {
    
    OldKentRd(60, 50, 2, 10, 30, 90, 160,250, 30, 2, "Old Kent RD"),
    WhiteChapelRD(60, 50, 4, 20, 60, 180, 320, 450,30, 4, "White Chapel RD"),
    TheAngelIslingtong(100, 50, 4, 20, 60, 180,320,450, 50, 7, "The Angel Islington"),
    EustonRD(100, 50, 6, 30, 90, 270, 400, 550, 50, 9, "Euston RD"), 
    PentonvilleRD(120, 50, 6, 30, 90, 270, 450, 600, 60, 10, "Penton Ville RD"),
    PallMall(140, 100, 10, 50, 150, 450, 650, 750, 70, 12, "Pall Mall"),
    WhiteHall(140, 100, 10, 50, 150, 450, 625, 750, 70, 14, "White Hall"),
    NorthUmberLand(150, 100, 12, 60, 180, 500, 700, 900, 80, 15, "North Umber Land"),
    BowST(180, 100, 14, 70, 200, 550, 750, 950, 90, 17, "BowST"),
    MarlBorounghST(180, 100, 14, 70, 200, 550, 750, 950, 90, 19, "Marl Boroungh ST"),
    VineST(200, 100, 16, 80, 220, 600, 800, 1000, 100, 20, "Vine ST"),
    Strand(220, 150, 18, 90, 250, 700, 875, 1050, 110, 22, "Strand"),
    FleetST(220, 150, 18, 90, 250, 700, 875, 1050, 110, 24, "Fleet ST"),
    Trafalgar(240, 150, 20, 100, 300, 750, 925, 1100, 120, 25, "Trafalgar"),
    Leicester(260, 150, 22, 110, 330, 800, 975, 1150,130, 27, "Leicester"),
    CoventryST(260, 150, 22, 110, 330, 800, 975, 1150,130, 28, "Conventry ST"),
    Piccadilly(280, 150, 24, 120, 360, 850, 1025, 1200,140, 30, "Picadilly"),
    RegentST(300, 200, 26, 130, 390, 900, 1100, 1275,150, 32,  "RegentST"),
    OxfordST(300, 200, 26, 130, 390, 900, 1100, 1275,150, 33, "Oxford ST"),
    BondST(320, 200, 28, 150, 450, 1000, 1200, 1400,160, 35,  "BondST"),
    ParkLane(350, 200, 35, 175, 500, 1100, 1300, 1500,175, 38,  "Park Lane"),
    MayFair(400, 200, 50, 200, 600, 1400, 1700, 2000,200, 40,  "May Fair");
    
    private final int price, pricePerHouse, rent, rent1House, rent2House, rent3House, rent4House, rentHotel, position, mortgage;
    private int ammountOfHouses;
    private boolean hotel;
    private final String name;

    private Property(int price, int pricePerHouse, int rent, int rent1House, int rent2House, int rent3House, int rent4House, int rentHotel,int mortgage, int position, String name ) {
        this.price = price;
        this.pricePerHouse = pricePerHouse;
        this.rent = rent;
        this.rent1House = rent1House;
        this.rent2House = rent2House;
        this.rent3House = rent3House;
        this.rent4House = rent4House;
        this.rentHotel = rentHotel;
        this.name = name;
        this.position = position; 
        this.mortgage = mortgage; 
        ammountOfHouses = 0;
        hotel = false;
    }

    public int getAmmountOfHouses() {
        return ammountOfHouses;
    }

    public void setAmmountOfHouses(int ammountOfHouses) {
        this.ammountOfHouses = ammountOfHouses;
    }

    public boolean hasHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
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
        int retValue = 0;
        if (hasHotel())
            retValue = rentHotel;
        else
            switch (ammountOfHouses) {
                case 0 -> retValue = rent;
                case 1 -> retValue = rent1House;
                case 2 -> retValue = rent2House;
                case 3 -> retValue = rent3House;
                case 4 -> retValue = rent4House;
            }
        return retValue;
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

    public int getPosition() {
        return position;
    }

    public int getMortgage() {
        return mortgage;
    }
    
}
