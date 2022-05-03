package monopoly.functional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JButton;
import monopoly.gui.Board;

public class Util {
    
    private static Util util = new Util();
    private final Random random = new Random();
    private final Bank bank = new Bank();
    private final HashMap<Integer, int[]> playerPositions = new HashMap<>(40);
    private Board board;
    private Players players = new Players();

    public Util() {
        initPlayerPositions();
    }

    public Bank getBank() {
        return bank;
    }

    public Players getPlayers() {
        return players;
    }

    public static Util getUtil() {
        return util;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Random getRandom() {
        return random;
    }

    public HashMap<Integer, int[]> getPlayerPositions() {
        return playerPositions;
    }
    
    private void initPlayerPositions() {
        int x, y;
        x = 890;
        y = 870;
        playerPositions.put(1, new int[] {x, y});
        x -= 100;
        playerPositions.put(2, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            if (i == 3)
                x -= 70;
            else
                x -= 80;
            playerPositions.put(i+3, new int[] {x, y});
        }
        x -= 100;
        playerPositions.put(11, new int[] {x, y});
        y -= 100;
        playerPositions.put(12, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            if (i == 5)
                y -= 70;
            else
                y -= 80;
            playerPositions.put(i+13, new int[] {x, y});
        }
        y -= 120;
        playerPositions.put(21, new int[] {x, y});
        x += 100;
        playerPositions.put(22, new int[] {x, y});        
        for (int i = 0; i < 8; i++) {
            if (i == 5)
                x += 70;
            else
                x += 80;
            playerPositions.put(i+23, new int[] {x, y});
        }
        x += 120;
        playerPositions.put(31, new int[] {x, y});
        y += 120;
        playerPositions.put(32, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            if (i == 2)
                y += 70;
            else
                y += 80;
            playerPositions.put(i+33, new int[] {x, y});
        }    
        System.out.println("map: ");
        for (int arr : playerPositions.keySet()) {
            System.out.println(arr + " : " + Arrays.toString(playerPositions.get(arr)));
        }
    }

}
