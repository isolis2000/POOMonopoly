package monopoly.functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import monopoly.functional.Player;
import monopoly.functional.GameMaster;
import monopoly.functional.squares.Chance;
import monopoly.functional.squares.CommuinityChest;
import monopoly.functional.squares.Property;
import monopoly.functional.squares.SpecialProperty;

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
                player2.addProperties(1);
            }
        }
        for (Map.Entry<SpecialProperty, Player> set : specialProperties.entrySet()) {
            if (set.getValue() != null && set.getValue().equals(player1)) {
                set.setValue(player2);
                player2.addProperties(1);
            }
        }
    }

    private String getPropertyType(int position) {
        if (position == 5)
            return "IncomeTax";
        else if (position == 39)
            return "SuperTax";
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
        for (int i = 0; i <= 40; i++) {
            if (getPropertyByPosition(i) != null) {
                propertyNames.put(getPropertyByPosition(i).getName(), i);
            } else if (getSpecialPropertyByPosition(i) != null) {
                propertyNames.put(getSpecialPropertyByPosition(i).getName(), i);
            }
        }
    }
    
    public void casa() {
        Player player = GameMaster.getGameMaster().getPlayers().getPlayerList().get(0);
        properties.replace(Property.OldKentRd, player);
        properties.replace(Property.WhiteChapelRD, player);
        player.addProperties(2);
        
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
            if (player.canPurchase()) {
                if (squareType.equals("Property")) {
                    Property property = getPropertyByPosition(playerPosition);
                    GameMaster.getGameMaster().getBoard().buyPrompt(player, property.getName(), property.getPrice());
                } else if (squareType.equals("SpecialProperty")) {
                    SpecialProperty specialProperty = getSpecialPropertyByPosition(playerPosition);
                    GameMaster.getGameMaster().getBoard().buyPrompt(player, specialProperty.getName(), specialProperty.getPrice());
                }
            }
        } else if (!secondPlayer.equals(player)){
            if (squareType.equals("Property")) {
                Property property = getPropertyByPosition(playerPosition);
                int rent = property.getRent();
                pay(player, secondPlayer, rent);
            } else if (squareType.equals("SpecialProperty")) {
                SpecialProperty specialProperty = getSpecialPropertyByPosition(playerPosition);
                pay(player, secondPlayer, specialProperty.getRent(checkAmmountOfSpecialProperties(specialProperty.getType(), player), diceResult));
            }
        }
        GameMaster.getGameMaster().getPlayers().changePlayerTurn();
    }

    //controls what happens after a player moves
    public void checkPosition(Player player, int dice1, int dice2) {
        if (!player.isInJail()) {
            int playerPosition = player.getPosition();
            String squareType = GameMaster.getGameMaster().getBank().getPropertyType(playerPosition);
            if (playerPosition == 31){
                squareType = "GoTOJail";
            }
            switch (squareType) {
                case "Property", "SpecialProperty" ->
                    onProperty(player, squareType, playerPosition, dice1 + dice2);
                case "CommunityChest" -> GameMaster.getGameMaster().getBoard().toggleComponents(5);
                case "Chance" -> GameMaster.getGameMaster().getBoard().toggleComponents(6);
                case "GoTOJail" -> GameMaster.getGameMaster().getPlayers().goToJail(GameMaster.getGameMaster().getPlayers().getPlayerTurn());
                case "IncomeTax" -> player.payTaxes(1);
                case "SuperTax" -> player.payTaxes(2);
                default -> {GameMaster.getGameMaster().getPlayers().changePlayerTurn();}
            }
        } else if (dice1 == dice2 && dice2 == 5)
            player.getOutOfJail(true);
        else 
            player.addJailTry();
    }
    
    public void checkPosition(Player player) {
        int playerPosition = player.getPosition();
            String squareType = GameMaster.getGameMaster().getBank().getPropertyType(playerPosition);
        switch (squareType) {
                case "Property", "SpecialProperty" ->
                    onProperty(player, squareType, playerPosition, 20);
                case "CommunityChest" -> GameMaster.getGameMaster().getBoard().toggleComponents(5);
                case "Chance" -> GameMaster.getGameMaster().getBoard().toggleComponents(6);
                case "GoTOJail" -> GameMaster.getGameMaster().getPlayers().goToJail(GameMaster.getGameMaster().getPlayers().getPlayerTurn());
                case "IncomeTax" -> player.payTaxes(1);
                case "SuperTax" -> player.payTaxes(2);
                default -> {}
            }
    }

    public CommuinityChest getCommuinityChest() {
        return commuinityChest;
    }

    public Chance getChance() {
        return chance;
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
