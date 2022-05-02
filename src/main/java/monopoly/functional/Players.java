package monopoly.functional;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;

public class Players {
    
    private ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    
    public void initPlayers(int numOfPlayers, JButton[] jButtons) {
        String retStr = "";
        for (int i = 0; i < numOfPlayers; i ++) {
            Player player = new Player(i+1, jButtons[i]);
            playerList.add(player);
        }
        ArrayList<Integer> initialDiceArray = new ArrayList<>();
        ArrayList<Integer> initialDiceArrayAux = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            int initialDice = Util.getUtil().getRandom().nextInt(6) + Util.getUtil().getRandom().nextInt(6) + 2;
            retStr += "Jugador " + i + " obtuvo " + initialDice + "\n";
            initialDiceArray.add(initialDice);
            playerList.get(i).setInitialDiceResult(initialDice);
        }
        Collections.sort(playerList);
        playerList.get(0).setTurn(true);
    }
    
    public void movePlayer(int moved) {
        boolean foundPlayer = false;
        for (Player p : playerList) {
            if (p.isTurn()) {
                p.setTurn(false);
                p.addToPosition(moved);
                foundPlayer = true;
            } else if (foundPlayer) {
                p.setTurn(true);
                return;
            }
        }
        if (foundPlayer)
            playerList.get(0).setTurn(true);
        else
            System.out.println("Error en turnos");
    }

}
