package monopoly.functional.squares;

import java.util.ArrayList;
import monopoly.functional.squares.Property;
import java.util.HashMap;
import java.util.Map;
import monopoly.functional.Player;
import monopoly.functional.Util;

public class Bank {
    
    private HashMap<Property, Player> properties = new HashMap<>(28);
    private HashMap<SpecialProperty, Player> specialProperties = new HashMap<>(6);
    private final CommuinityChest commuinityChest = new CommuinityChest();
    private final Chance chance = new Chance();
    
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
    
    //Listas de propiedades 
    //Arraylist

}
