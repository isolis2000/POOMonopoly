package monopoly.functional.squares;

import java.util.ArrayList;
import monopoly.functional.squares.Property;
import java.util.HashMap;
import java.util.Map;
import monopoly.functional.Player;
import monopoly.functional.Util;

public class Bank {
    
    private HashMap<Property, Player> properties = new HashMap<>();
    private HashMap<SpecialProperty, Player> specialProperties = new HashMap<>();
    private final CommuinityChest commuinityChest = new CommuinityChest();
    private final Chance chance = new Chance();

    public Bank() {
        initProperties();
    }
    
    public ArrayList<String> getPropertiesByPlayer(String playerName) {
        ArrayList<String> propertiesRet = new ArrayList<>();
        for (Map.Entry<Property, Player> set : properties.entrySet())
            if (set.getValue().getName().equals(playerName))
                propertiesRet.add(set.getKey().getName());
        return propertiesRet;
    }
    
    private String getPropertyType(int position) {
        for (Property p : Property.values())
            if (p.getPosition()== position)
                return "Property";
        for (SpecialProperty sp : specialProperties.keySet())
            if (sp.getPosition() == position)
                return "SpecialProperty";
        for (int pos : commuinityChest.getPositions())
            if (pos == position)
                return "CommunityChest";
        for (int pos : chance.getPositions())
            if (pos == position)
                return "Chance";
        return "-1"; 
    }
    
    private void initProperties() {
        for (Property p : Property.values())
            properties.put(p, null);            
        int[] stationPositions = {6, 16, 26, 36};
        String[] stationNames = {"King Cross Station", "Marylebone Station", "Fenchurch ST. Station", "Liverpool ST. Station"}; 
        for (int i = 0; i < 4; i++)
            specialProperties.put(new Station(stationPositions[i], 200, stationNames[i]), null);
        specialProperties.put(new Service(13, 150, "Electric Company"), null);
        specialProperties.put(new Service(29, 150, "Water Works"), null);
        for (Map.Entry<SpecialProperty, Player> entry : specialProperties.entrySet())
            if (entry.getKey().getName().equals("King Cross Station"))
                System.out.println("station: " + entry.getKey().getPrice());
    }
    
    private Property getPropertyByPosition(int position) {
        for (Property p : properties.keySet())
            if (p.getPosition() == position)
                return p;
        return null;
    }
    
    private SpecialProperty getSpecialPropertyByPosition(int position) {
        for (SpecialProperty sp : specialProperties.keySet())
            if (sp.getPosition() == position)
                return sp;
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
    
    private void onProperty(Player player, String squareType) {
        Player secondPlayer = whoOwns(player.getPosition(), squareType);
        if (secondPlayer == null)
            Util.getUtil().getBoard().buyPrompt();
//        else
//            pay(player, secondPlayer, 0)
    }
    
    //controls what happens after a player moves
    public void checkPosition(Player player) {
        int playerPosition = player.getPosition();
        String squareType = Util.getUtil().getBank().getPropertyType(playerPosition);
        if (squareType.equals("Property") || squareType.equals("SpecialProperty"))
            onProperty(player, squareType);
        else if (squareType.equals("CommunityChest"))
            System.out.println("CommunityChest");//Aqui su codigo de esta vara
        else if (squareType.equals("Chance"))
            System.out.println("Chance");//Y aqui
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
