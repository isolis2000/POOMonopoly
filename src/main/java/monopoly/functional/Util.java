package monopoly.functional;

import java.util.HashMap;
import java.util.Random;

public class Util {
    
    private static final Random random = new Random();
    
    private static final HashMap<Integer, int[]> playerPositions = new HashMap<>(44);

    public static Random getRandom() {
        return random;
    }

    public static HashMap<Integer, int[]> getPlayerPositions() {
        return playerPositions;
    }
    
    public static void initPlayerPositions() {
        int x, y;
        x = y = 870;
        playerPositions.put(1, new int[] {x, y});
        x -= 100;
        playerPositions.put(2, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            x -= 80;
            playerPositions.put(i+3, new int[] {x, y});
        }
        x -= 100;
        playerPositions.put(12, new int[] {x, y});
        y += 100;
        playerPositions.put(13, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            y += 80;
            playerPositions.put(i+14, new int[] {x, y});
        }
        y += 100;
        playerPositions.put(23, new int[] {x, y});
        x += 100;
        playerPositions.put(24, new int[] {x, y});        
        for (int i = 0; i < 8; i++) {
            x += 80;
            playerPositions.put(i+25, new int[] {x, y});
        }
        x += 100;
        playerPositions.put(34, new int[] {x, y});
        y -= 100;
        playerPositions.put(35, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            y -= 80;
            playerPositions.put(i+36, new int[] {x, y});
        }     
    }

}
