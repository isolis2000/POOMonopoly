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
        int unTie = 0;
        String retStr = "";
        int[] players = new int[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++) {
            players[i] = i;
        }
        int [][] data = generateTurns(0, numOfPlayers, players);
        unTie++;
        int[] turns = data[0];
        players = data[1];
        int playersWithTurn = 0;
        for(int i = 0; i < numOfPlayers; i++){
            retStr += "\nJugador " + (players[i] + 1) + " obtuvo " + turns[i];
        }
        while (playersWithTurn != numOfPlayers) {
            int nextTurn = 0;
            if(playersWithTurn != (numOfPlayers - 1)){
                nextTurn = turns[playersWithTurn + 1];
            }
            
            if(turns[playersWithTurn] != nextTurn){
                int p = players[playersWithTurn];
                int t = numOfPlayers - playersWithTurn;
                playersWithTurn++;
                retStr += "\nEl jugador " + (p + 1) + " tiene el turno número " + playersWithTurn;
            }else{
                int repeated = playersWithTurn;
                nextTurn = turns[repeated + 1];
                while(turns[repeated] == nextTurn){
                    repeated++;
                    if(repeated == numOfPlayers - 1){
                        nextTurn = 0;
                    }
                }
                if((repeated == numOfPlayers - 1) && (turns[numOfPlayers - 1] == turns[numOfPlayers - 2])){
                    repeated++;
                }
                data = generateTurns(playersWithTurn, repeated, players);
                retStr += "\n---------------------\nDesempate " + unTie + ":";
                for(int i = 0; i < data[0].length; i++){
                    turns[i + playersWithTurn] = data[0][i];
                    players[i + playersWithTurn] = data[1][i];
                    retStr += "\nLos nuevos valores son: El jugador " + (players[i + playersWithTurn] + 1) + " obtuvo " + turns[i + playersWithTurn];
                }
                unTie++;
            }
        }
        Collections.sort(playerList);
        GameMaster.getGameMaster().getBoard().initialDiceResults(retStr);
    }
    
    private int[][] generateTurns(int initial, int end, int[] players) {
        int size = end - initial;
        int[] turns = new int[size];
        int[] tempPlayers = new int[size];
        for (int i = 0; i < size; i++) {
            int initialDice = GameMaster.getGameMaster().getRandom().nextInt(6) + GameMaster.getGameMaster().getRandom().nextInt(6) + 2;
            turns[i] = (initialDice);
            tempPlayers[i] = players[i + initial];
        }
        bubbleSort(turns, tempPlayers, size);
        return new int[][] {turns, tempPlayers};
    }

    private void bubbleSort(int[] turns, int[] players, int arrSize) {
        int tempTurn, tempPlayer;
        
        for (int i = 0; i < arrSize - 1; i++){
            for (int j = 0; j < arrSize - i - 1; j++){
                //No quick porque no fue necesario
                if (turns[j] < turns[j + 1]) {
                  tempTurn = turns[j];
                  tempPlayer = players[j];
                  turns[j] = turns[j + 1];
                  players[j] = players[j + 1];
                  turns [j + 1] = tempTurn;
                  players[j + 1] = tempPlayer;
                }
            }
        }
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
