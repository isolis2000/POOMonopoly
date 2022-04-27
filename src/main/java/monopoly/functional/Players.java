package monopoly.functional;

import java.util.ArrayList;
import javax.swing.JButton;

public class Players {
    
    private ArrayList<Player> playerList = new ArrayList<>();
    
    public void initPlayers(int numOfPlayers, JButton[] jButtons) {
        for (int i = 0; i < numOfPlayers; i ++) {
            boolean turn = false;
            if (i == 0)
                turn = true;
            Player player = new Player(i+1, 1, jButtons[i], turn);
            playerList.add(player);
        }
    }
    
    public void movePlayer(int moved) {
        boolean foundPlayer = false;
        for (Player p : playerList) {
            if (p.isTurn()) {
                p.setTurn(false);
                p.setPosition(moved);
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
