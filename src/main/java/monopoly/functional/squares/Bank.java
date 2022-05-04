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
    
    public String getPropertyType(int position) {
        for (Property p : Property.values())
            if (p.getPosition()== position)
                return "Property";
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
    
    //Listas de propiedades 
    //Arraylist

}
