package monopoly.functional.squares;

import java.util.ArrayList;
import monopoly.functional.squares.Property;
import java.util.HashMap;
import java.util.Map;
import monopoly.functional.Player;
import monopoly.functional.Util;

public class Bank {
    
    private HashMap<Property, Player> squaresMap = new HashMap<>(40);
    
    public ArrayList<String> getPropertiesByPlayer(String playerName) {
        ArrayList<String> propertiesRet = new ArrayList<>();
        for (Map.Entry<Property, Player> set : squaresMap.entrySet())
            if (set.getValue().getName().equals(playerName))
                propertiesRet.add(set.getKey().getName());
        return propertiesRet;
    }

}
