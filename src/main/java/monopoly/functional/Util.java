package monopoly.functional;

import java.util.ArrayList;
import monopoly.functional.squares.Bank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import monopoly.gui.Board;

public class Util {
    
    private static final Util util = new Util();
    private final Random random = new Random();
    private final Players players = new Players();
    private final Bank bank = new Bank();
    private final HashMap<Integer, int[]> boardPositions = new HashMap<>(40);
    private Board board;


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
        return boardPositions;
    }
    
    private void initPlayerPositions() {
        int x, y;
        x = 890;
        y = 870;
        boardPositions.put(1, new int[] {x, y});
        x -= 100;
        boardPositions.put(2, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            if (i == 3)
                x -= 70;
            else
                x -= 80;
            boardPositions.put(i+3, new int[] {x, y});
        }
        x -= 100;
        boardPositions.put(11, new int[] {x, y});
        y -= 100;
        boardPositions.put(12, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            if (i == 5)
                y -= 70;
            else
                y -= 80;
            boardPositions.put(i+13, new int[] {x, y});
        }
        y -= 120;
        boardPositions.put(21, new int[] {x, y});
        x += 100;
        boardPositions.put(22, new int[] {x, y});        
        for (int i = 0; i < 8; i++) {
            if (i == 5)
                x += 70;
            else
                x += 80;
            boardPositions.put(i+23, new int[] {x, y});
        }
        x += 120;
        boardPositions.put(31, new int[] {x, y});
        y += 120;
        boardPositions.put(32, new int[] {x, y});
        for (int i = 0; i < 8; i++) {
            if (i == 2)
                y += 70;
            else
                y += 80;
            boardPositions.put(i+33, new int[] {x, y});
        }    
        System.out.println("map: ");
        for (int arr : boardPositions.keySet()) {
            System.out.println(arr + " : " + Arrays.toString(boardPositions.get(arr)));
        }
    }
    
}
