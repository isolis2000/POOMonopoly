package monopoly.functional.squares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import monopoly.functional.Player;
import monopoly.functional.Util;

public class Bank {

    private HashMap<Property, Player> properties = new HashMap<>();
    private HashMap<SpecialProperty, Player> specialProperties = new HashMap<>();
    private final CommuinityChest commuinityChest = new CommuinityChest();
    private final Chance chance = new Chance();
    private final HashMap<String, Integer> propertyNames = new HashMap<>();
    private final HashMap<Property, String> propertyColors = new HashMap<>();
    private final String[] colors = {"Cafe", "Celeste", "Rosado", "Anaranjado", "Rojo", "Amarillo", "Verde", "Azul"};
    private int housesLeft = 32, hotelsLeft = 12;

    public Bank() {
        initProperties();
    }

    public ArrayList<String> getPropertiesStrByPlayer(String playerName) {
        ArrayList<String> propertiesRet = new ArrayList<>();
        for (Map.Entry<Property, Player> set : properties.entrySet()) {
            if (set.getValue() != null && set.getValue().getName().equals(playerName)) {
                Property property = set.getKey();
                String str = "Propiedad: " + property.getName() + "Renta: " 
                        + property.getRent() + " ---- Cantidad de casas: " 
                        + property.getAmmountOfHouses() + " ---- Hotel: ";
                if (property.hasHotel())
                    str += "si";
                else
                    str += "no";
                propertiesRet.add(str);
            }
        }
        for (Map.Entry<SpecialProperty, Player> set : specialProperties.entrySet()) {
            if (set.getValue() != null && set.getValue().getName().equals(playerName)) {
                propertiesRet.add(set.getKey().getName());
            }
        }
        return propertiesRet;
    }
    
    private ArrayList<Property> getPropertiesByPlayer(String playerName) {
        ArrayList<Property> propertiesRet = new ArrayList<>();
        for (Map.Entry<Property, Player> set : properties.entrySet())
            if (set.getValue() != null && set.getValue().getName().equals(playerName)) 
                propertiesRet.add(set.getKey());
        return propertiesRet;
    }
    
    private ArrayList<ArrayList<Property>> checkForMonopoly(ArrayList<Property> propertiesToCheck) {
        ArrayList<ArrayList<Property>> propertiesWithMonopoly = new ArrayList<>();
        for (String c : colors) {
            ArrayList<Property> propertiesOfColor = getAmmountOfOneColor(propertiesToCheck, c);
            if (c.equals(colors[0]) || c.equals(colors[colors.length-1])) {
                if (propertiesOfColor.size() == 2)
                    propertiesWithMonopoly.add(propertiesOfColor);
            } else {
                if (propertiesOfColor.size() == 3)
                    propertiesWithMonopoly.add(propertiesOfColor);
            }
        }
        return propertiesWithMonopoly;
    }
    
    private ArrayList<Property> getAmmountOfOneColor(ArrayList<Property> propertiesToCheck, String colorToCheck) {
        ArrayList<Property> ammountOfColor = new ArrayList<>();
        for (Property p : propertiesToCheck)
            if (propertyColors.get(p).equals(colorToCheck))
                ammountOfColor.add(p);
        return ammountOfColor;
    }
    
    private int getHighestAmmountOfHouses(ArrayList<Property> propertiesToCheck) {
        int ammountRet = 0;
        for (Property p : propertiesToCheck) {
            int ammount = p.getAmmountOfHouses();
            if (p.hasHotel())
                return 5;
            else if (ammount > ammountRet)
                ammountRet = p.getAmmountOfHouses();
        }
        return ammountRet;
    }
    
    public ArrayList<Property> getAvailableHousesToPurchase(Player player) {
        ArrayList<Property> playerProperties = getPropertiesByPlayer(player.getName());
        ArrayList<ArrayList<Property>> propertiesWithMonopoly = checkForMonopoly(playerProperties);
        ArrayList<Property> availableProperties = new ArrayList<>();
        int x = 0;
        for (int m = 0; m < propertiesWithMonopoly.size(); m++) {
            int highest = getHighestAmmountOfHouses(propertiesWithMonopoly.get(m));
            for (int i = 0; i < propertiesWithMonopoly.get(0).size(); i++) {
                Property currentProperty = propertiesWithMonopoly.get(m).get(i);
                if (currentProperty.hasHotel()) {
                } else if (currentProperty.getAmmountOfHouses() < highest) {
                    availableProperties.add(currentProperty);
                    x++;
                }
            }
            if (x == 0)
                for (Property p : propertiesWithMonopoly.get(m))
                    availableProperties.add(p);
            else
                x = 0;
        }
        return availableProperties;
    }
    
    public boolean buyHouse(Property property, Player player) {
        if (player.buy(property.getPricePerHouse()) && !property.hasHotel()) {
            property.addHouse();
            return true;
        } else 
            return false;
    }
    
    public String propertyArrayListToString(ArrayList<Property> arr) {
        String ret = "";
        for (int i = 0; i < arr.size(); i++) {
            String pr = arr.get(i).getName();
            ret += i + ". " + pr + "\n";
        }
        return ret;
    }

    public void transferProperties(Player player1, Player player2) {
        for (Map.Entry<Property, Player> set : properties.entrySet()) {
            if (set.getValue() != null && set.getValue().equals(player1)) {
                set.setValue(player2);
            }
        }
        for (Map.Entry<SpecialProperty, Player> set : specialProperties.entrySet()) {
            if (set.getValue() != null && set.getValue().equals(player1)) {
                set.setValue(player2);
            }
        }
    }

    private String getPropertyType(int position) {
        for (Property p : Property.values()) {
            if (p.getPosition() == position) {
                return "Property";
            }
        }
        for (SpecialProperty sp : specialProperties.keySet()) {
            if (sp.getPosition() == position) {
                return "SpecialProperty";
            }
        }
        for (int pos : commuinityChest.getPositions()) {
            if (pos == position) {
                return "CommunityChest";
            }
        }
        for (int pos : chance.getPositions()) {
            if (pos == position) {
                return "Chance";
            }
        
        }

        return "-1";
    }

    private void initProperties() {
        Property[] values = Property.values();
        int x = 0;
        int currentColor = 0;
        Property pr1;
        Property pr2;
        for (Property value : values) {
            if (((currentColor == 0 || currentColor == colors.length) && x == 2) || x == 3) {
                currentColor ++;
                x = 0;                
            }
            propertyColors.put(value, colors[currentColor]);
            properties.put(value, null);
            x++;
            
        }
        int[] stationPositions = {6, 16, 26, 36};
        String[] stationNames = {"King Cross Station", "Marylebone Station", "Fenchurch ST. Station", "Liverpool ST. Station"};
        for (int i = 0; i < 4; i++) {
            specialProperties.put(new SpecialProperty(stationPositions[i], 200, stationNames[i], "Station"), null);
        }
        specialProperties.put(new SpecialProperty(13, 150, "Electric Company", "Service"), null);
        specialProperties.put(new SpecialProperty(29, 150, "Water Works", "Service"), null);
        for (Map.Entry<SpecialProperty, Player> entry : specialProperties.entrySet()) {
            if (entry.getKey().getName().equals("King Cross Station")) {
                System.out.println("station: " + entry.getKey().getPrice());
            }
        }
        for (int i = 0; i <= 40; i++) {
            if (getPropertyByPosition(i) != null) {
                propertyNames.put(getPropertyByPosition(i).getName(), i);
            } else if (getSpecialPropertyByPosition(i) != null) {
                propertyNames.put(getSpecialPropertyByPosition(i).getName(), i);
            }
        }
    }
    
    private void casa() {
        properties.replace(Property.OldKentRd, Util.getUtil().getPlayers().getPlayerList().get(0));
        properties.replace(Property.WhiteChapelRD, Util.getUtil().getPlayers().getPlayerList().get(0));
    }

    private Property getPropertyByPosition(int position) {
        for (Property p : properties.keySet()) {
            if (p.getPosition() == position) {
                return p;
            }
        }
        return null;
    }

    private SpecialProperty getSpecialPropertyByPosition(int position) {
        for (SpecialProperty sp : specialProperties.keySet()) {
            if (sp.getPosition() == position) {
                return sp;
            }
        }
        return null;
    }

    private Player whoOwns(int position, String squareType) {
        switch (squareType) {
            case "Property" -> {
                return properties.get(getPropertyByPosition(position));
            }
            case "SpecialProperty" -> {
                return specialProperties.get(getSpecialPropertyByPosition(position));
            }
            default -> {
            }
        }
        return null;
    }

//    private void buyProperty(Player player, )
    private void pay(Player payer, Player payee, int ammount) { //the one who pays and the one who gets payed
        payer.payUp(ammount, payee);
    }

    public void buyProperty(Player player, String propertyName) {
        try {
            int position = propertyNames.get(propertyName);
            Property property = getPropertyByPosition(position);
            SpecialProperty specialProperty = getSpecialPropertyByPosition(position);
            if (property != null) {
                properties.replace(property, player);
            } else if (specialProperty != null) {
                specialProperties.replace(specialProperty, player);
            }
            System.out.println("property " + propertyName + " was bought by player " + player.getName());
        } catch (Exception e) {
            System.out.println("Exception with property " + propertyName);
        }
    }

//    private int getRent(int position, String squareType) {
//        if (squareType.equals("Property"))
//            for (Property p : Property.values()) {
//                if (p.getPosition() == position)
//                    return p.getRent();
//            }
//        else
//            for (SpecialProperty sp : specialProperties.keySet())
//                return 
//        
//    }
    private int checkAmmountOfSpecialProperties(String type, Player player) {
        int res = 0;
        for (Map.Entry<SpecialProperty, Player> set : specialProperties.entrySet()) {
            Player value = set.getValue();
            SpecialProperty key = set.getKey();
            if (value != null && value.equals(player) && key.getType().equals(type)) {
                res += 1;
            }
        }
        return res;
    }

    private void onProperty(Player player, String squareType, int playerPosition, int diceResult) {
        Player secondPlayer = whoOwns(player.getPosition(), squareType);
        if (secondPlayer == null) {
            if (squareType.equals("Property")) {
                Property property = getPropertyByPosition(playerPosition);
                Util.getUtil().getBoard().buyPrompt(player, property.getName(), property.getPrice());
            } else if (squareType.equals("SpecialProperty")) {
                SpecialProperty specialProperty = getSpecialPropertyByPosition(playerPosition);
                Util.getUtil().getBoard().buyPrompt(player, specialProperty.getName(), specialProperty.getPrice());
            }
        } else {
            if (squareType.equals("Property")) {
                Property property = getPropertyByPosition(playerPosition);
                int rent = property.getRent();
//                Util.getUtil().getBoard().rentPrompt(player, property.getName(), property.getPrice());
                pay(player, secondPlayer, rent);
            } else if (squareType.equals("SpecialProperty")) {
                SpecialProperty specialProperty = getSpecialPropertyByPosition(playerPosition);
//                Util.getUtil().getBoard().buyPrompt(player, specialProperty.getName(), specialProperty.getPrice());
                pay(player, secondPlayer, specialProperty.getRent(checkAmmountOfSpecialProperties(specialProperty.getType(), player), diceResult));
//                pay(player, secondPlayer, specialProperty.getRent());
            }
        }
    }

    //controls what happens after a player moves
    public void checkPosition(Player player, int diceResult) {
        casa();
        int playerPosition = player.getPosition();
        System.out.println("Position = " + playerPosition);
        String squareType = Util.getUtil().getBank().getPropertyType(playerPosition);
        if (playerPosition == 31){
            squareType = "GoTOJail";
        }
        System.out.println("squareType = " + squareType);
        switch (squareType) {
            case "Property", "SpecialProperty" ->
                onProperty(player, squareType, playerPosition, diceResult);
            case "CommunityChest" -> String(player); //Aqui su codigo de esta vara
            case "Chance" -> 
                Util.getUtil().getBoard().toggleComponents(6);//Y aqui
            case "GoTOJail" -> GoToJail(playerPosition);
            default -> {
            }
        }
    }
    
    private void String(Player player){
        Util.getUtil().getBoard().toggleComponents(5);
        System.out.println("Community Chest");
        System.out.println(player.getPosition());
        
    }

    //Listas de propiedades 
    //Arraylist
    public CommuinityChest getCommuinityChest() {
        return commuinityChest;
    }

    public Chance getChance() {
        return chance;
    }
    
    public void GoToJail(int position){
        JOptionPane.showMessageDialog(null, "Vaya a la carcel");
        position = 21;
        Util.getUtil().getPlayers().movePlayer(position);
    }

    public int getHousesLeft() {
        return housesLeft;
    }

    public int getHotelsLeft() {
        return hotelsLeft;
    }
    
    public void removeHouseFromTotal() {
        housesLeft--;
    }
    
    public void removeHotelFromTotal() {
        hotelsLeft--;
    }

}
