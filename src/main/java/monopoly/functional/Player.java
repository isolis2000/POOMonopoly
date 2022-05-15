package monopoly.functional;

import java.util.ArrayList;
import javax.swing.JButton;

public class Player implements Comparable<Player> {
    
    private int playerNum, position, ammountOfProperties, money, initialDiceResult, jailTries;
    private JButton button;
    private boolean turn, jail, outOfJailCard, purchase;
    private final String name;
    
    

    public Player(int playerNum, JButton button, String playerName) {
        this.playerNum = playerNum;
        this.button = button;
        this.name = playerName;
        turn = false;
        position = 1;
        money = 1500;
        ammountOfProperties = 0;
        jail = false;
        jailTries = 0;
        outOfJailCard = false;
        this.button.setVisible(true);
        purchase = false;
    }

    public boolean canPurchase() {
        return purchase;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }

    public String getName() {
        return name;
    }

    public int getInitialDiceResult() {
        return initialDiceResult;
    }

    public void setInitialDiceResult(int initialDiceResult) {
        this.initialDiceResult = initialDiceResult;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        int[] arr = GameMaster.getGameMaster().getPlayerPositions().get(position);
        button.setLocation(arr[0], arr[1]);
        GameMaster.getGameMaster().getBoard().repaint();
    }

    public void addToPosition(int dice1, int dice2) {
        position += dice1 + dice2;
        if (position > 40) {
            position -= 40;
            money += 200;
            if (!purchase)
                purchase = true;
        }
        int[] arr = GameMaster.getGameMaster().getPlayerPositions().get(position);
        button.setLocation(arr[0], arr[1]);
        GameMaster.getGameMaster().getBoard().updateGameString();
        GameMaster.getGameMaster().getBoard().repaint();
    }
    
    public void checkPosition(int dice1, int dice2) {
        GameMaster.getGameMaster().getBank().checkPosition(this, dice1, dice2);
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getProperties() {
        return ammountOfProperties;
    }

    public void addProperties(int propertiesToAdd) {
        ammountOfProperties += propertiesToAdd;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public void addMoney(int money) {
        this.money += money;
        if (this.money < 0) {
            GameMaster.getGameMaster().getPlayers().declarePlayerBankrupt(this);
            GameMaster.getGameMaster().getBoard().declareBankruptBank(name);
        }
    }

    public int getJailTries() {
        return jailTries;
    }

    public void setJailTries(int jailTries) {
        this.jailTries = jailTries;
    }
    
    public void addJailTry() {
        jailTries++;
        GameMaster.getGameMaster().getPlayers().changePlayerTurn();
    }

    public boolean isInJail() {
        return jail;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }

    public boolean hasOutOfJailCard() {
        return outOfJailCard;
    }

    public void setOutOfJailCard(boolean outOfJailCard) {
        this.outOfJailCard = outOfJailCard;
    }
    
    public void getOutOfJail(boolean usedCard) {
        if (usedCard) {
            jail = false;
            jailTries = 0;
        } else if (canBuy(50)) {
            money -= 50;
            jail = false;
            jailTries = 0;
        } else
            GameMaster.getGameMaster().getBoard().noMoneyPrompt();
        if (!jail)
            GameMaster.getGameMaster().getBoard().isOutOfJailMessage(this);
    }
    
    public void payUp(int moneyToPay, Player payee) {
        if (!canBuy(moneyToPay)) {
            GameMaster.getGameMaster().getBank().transferProperties(this, payee);
            GameMaster.getGameMaster().getPlayers().declarePlayerBankrupt(this);
            GameMaster.getGameMaster().getBoard().declareBankrupt(name, payee.getName());
        }
        else {
            money -= moneyToPay;
            payee.setMoney(payee.getMoney() + moneyToPay);
            GameMaster.getGameMaster().getBoard().rentPrompt(name, payee.getName(), moneyToPay);
        }
    }
    
    public void buyProperty(String propertyName, int price) {
        if (canBuy(price)) {
            ammountOfProperties ++;
            money -= price;
            GameMaster.getGameMaster().getBank().buyProperty(this, propertyName);
        } else {
            GameMaster.getGameMaster().getBoard().noMoneyPrompt();
        }
    }
    
    public boolean buy(int price) {
        if (canBuy(price)) {
            money -= price;
            return true;
        } else
            return false;
    }
    
    private boolean canBuy(int price) {
        return price <= money;
    }
    
    public void payTaxes(int type) { // 1 = income tax, 2 = super tax
        boolean payed = false;
        int ammount = 0;
        if (type == 1)
            if (buy(200)) {
                ammount = 200;
                payed = true;
            }
        else
            if (buy(100)) {
                ammount = 100;
                payed = true;
            }
        if (payed) 
            GameMaster.getGameMaster().getBoard().payTaxesPrompt(this, ammount);
        else {
            GameMaster.getGameMaster().getPlayers().declarePlayerBankrupt(this);
            GameMaster.getGameMaster().getBoard().declareBankruptBank(name);
        }
        GameMaster.getGameMaster().getPlayers().changePlayerTurn();
    }
    
    public void findNearestStation() {
        int res = 20;
        for (int i = 6; i <= 36; i += 10)
            if (i-position > 0 && i-position < res)
                res = i;
        if (res == 20) {
            setPosition(6);
            money += 200;
        }
        else
            setPosition(res);
        GameMaster.getGameMaster().getBank().checkPosition(this);
    }
    
    public void findNearestUtility() {
         //13 29
        if (13 - position > 0)
            position = 13;
        else if (29 - position > 0)
            setPosition(29);
        else {
            setPosition(13);
            money += 200;
        }
        GameMaster.getGameMaster().getBank().checkPosition(this);
    }
    
    @Override
    public String toString() {
        ArrayList<String> playerPropertiesArr = GameMaster.getGameMaster().getBank().getPropertiesStrByPlayer(name);
        String playerProperties = "";
        for (String s : playerPropertiesArr)
            playerProperties += "- " + s + "\n";
        String retStr = "Jugador " + name + " tiene $" + money + " en el banco y " + ammountOfProperties + " propiedades a su nombre\n"
                + playerProperties;
        return retStr;
    }

    @Override
    public int compareTo(Player p) {
        int compareDice = p.getInitialDiceResult();
        return this.initialDiceResult-compareDice;
    }

}
