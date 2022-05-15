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
            int initialDice = GameMaster.getGameMaster().getRandom().nextInt(6) + GameMaster.getGameMaster().getRandom().nextInt(6) + 2;
            retStr += "Jugador " + i + " obtuvo " + initialDice + "\n";
            initialDiceArray.add(initialDice);
            playerList.get(i).setInitialDiceResult(initialDice);
        }
        Collections.sort(playerList);
    }
    
    public void movePlayer(int dice1, int dice2) {
        Player player = getPlayerTurn();
        if (!player.isInJail()) {
            player.addToPosition(dice1, dice2);
            player.checkPosition(dice1, dice2);
        } else
            player.checkPosition(dice1, dice2);
    }
    
    public void changePlayerTurn() {
        Player player = getPlayerTurn();
        player.setTurn(false);
        getNextPlayer(player).setTurn(true);
    }
    
    public void goToJail(Player player) {
        player.setJail(true);
        player.setPosition(11);
        GameMaster.getGameMaster().getBoard().toggleComponents(9);
        changePlayerTurn();
    }
    
    public void declarePlayerBankrupt(Player player) {
        player.setTurn(false);
        getNextPlayer(player).setTurn(true);
        playerList.remove(player);
//        if (playerList.size() == 1)
//            Util.getUtil().getBoard().gameOver(playerList.get(0).getName());
    }
    
    public Player getPlayerTurn() {
        for (Player p : playerList)
            if (p.isTurn())
                return p;
        return null;
    }
    
    private Player getNextPlayer(Player player) {
        int index = playerList.indexOf(player);
        if (index + 1 == playerList.size())
            return playerList.get(0);
        else
            return playerList.get(index+1);
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
    
    public String getGameString() {
        String gameStr = "";
        for (int i = 0; i < playerList.size(); i++)
            gameStr += "\n" + getPlayerString(i + 1);
        return gameStr;
    }
    
}
