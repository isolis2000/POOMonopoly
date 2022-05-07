package monopoly.functional.squares;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import monopoly.functional.Player;
import monopoly.functional.Util;

public class Bank {

    private HashMap<Property, Player> properties = new HashMap<>();
    private HashMap<SpecialProperty, Player> specialProperties = new HashMap<>();
    private final CommuinityChest commuinityChest = new CommuinityChest();
    private final Chance chance = new Chance();
    private final HashMap<String, Integer> propertyNames = new HashMap<>();

    public Bank() {
        initProperties();
    }

    public ArrayList<String> getPropertiesByPlayer(String playerName) {
        ArrayList<String> propertiesRet = new ArrayList<>();
//        System.out.println("PropertiesHash: " + properties.toString());
        for (Map.Entry<Property, Player> set : properties.entrySet()) {
            if (set.getValue() != null && set.getValue().getName().equals(playerName)) {
                propertiesRet.add(set.getKey().getName());
            }
        }
        for (Map.Entry<SpecialProperty, Player> set : specialProperties.entrySet()) {
            if (set.getValue() != null && set.getValue().getName().equals(playerName)) {
                propertiesRet.add(set.getKey().getName());
            }
        }
        return propertiesRet;
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
        for (Property p : Property.values()) {
            properties.put(p, null);
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
        int playerPosition = player.getPosition();
        String squareType = Util.getUtil().getBank().getPropertyType(playerPosition);
        switch (squareType) {
            case "Property", "SpecialProperty" ->
                onProperty(player, squareType, playerPosition, diceResult);
            case "CommunityChest" ->
                System.out.println("CommunityChest");//Aqui su codigo de esta vara
            case "Chance" ->
                System.out.println("Chance");//Y aqui
            default -> {
            }
        }
    }

    //Listas de propiedades 
    //Arraylist
    public CommuinityChest getCommuinityChest() {
        return commuinityChest;
    }

    public Chance getChance() {
        return chance;
    }

}
