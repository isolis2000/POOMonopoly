package monopoly.functional;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;

public class Players {
    
    private final ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    
    public void initPlayers(int numOfPlayers, JButton[] jButtons, String[] playerNames) {
        ArrayList<Player[]> results;
        for (int i = 0; i < numOfPlayers; i ++) {
            Player player = new Player(i+1, jButtons[i], playerNames[i]);
            playerList.add(player);
        }
        initTurns(numOfPlayers);
        playerList.get(0).setTurn(true);
    }
    
    private void initTurns(int numOfPlayers) {
        String retStr = "";
        ArrayList<Integer> initialDiceArray = new ArrayList<>();
        ArrayList<Integer> initialDiceArrayAux = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            int initialDice = Util.getUtil().getRandom().nextInt(6) + Util.getUtil().getRandom().nextInt(6) + 2;
            retStr += "Jugador " + i + " obtuvo " + initialDice + "\n";
            initialDiceArray.add(initialDice);
            playerList.get(i).setInitialDiceResult(initialDice);
        }
        System.out.println(retStr);
        Collections.sort(playerList);
    }
    
    public void movePlayer(int moved) {
        Player player = getPlayerTurn();
        player.addToPosition(moved);
        
        player.setTurn(false);
        
    }
    
    private Player getPlayerTurn() {
        for (Player p : playerList)
            if (p.isTurn())
                return p;
        return null;
    }
    
    public String getPlayerTurnName() {
        return getPlayerTurn().getName();
    }
    
    public String getPlayerString(int playerNum) {
        String playerString = "";
        for (Player p : playerList)
            if (p.getPlayerNum() == playerNum)
                playerString = p.toString();
        return playerString;
    }
    
}
