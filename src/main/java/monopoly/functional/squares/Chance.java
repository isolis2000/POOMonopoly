package monopoly.functional.squares;

import java.util.ArrayList;
import java.util.Collections;
import monopoly.functional.Player;
import monopoly.functional.Util;

public class Chance {
    
    private final int[] positions = {8, 23, 37};
    
    private ArrayList<Integer> cardsArrayList = new ArrayList<>();

    public Chance() {
        for (int i = 0; i < 16; i++)
            cardsArrayList.add(i+1);
        Collections.shuffle(cardsArrayList);
    }
    
    private String useCard(Player player, int n){
        String ret = "";
        switch (n) {
            case 1 -> ret = gasolinePrice(player);
            case 2 -> ret = goToJail (player);
            case 3 -> ret = tolls(player);
            case 4 -> ret = advanceToHotel(player);
            case 5 -> ret = advanceToDock (player);
            case 6 -> ret = takeAWalkAroundThePier (player);
            case 7 -> ret = takeAtripAvenidaOriental(player);
            case 8 -> ret = advanceAvenidaMediterraneo (player);
            case 9 -> ret = getOutOfJail(player);
            case 10 -> ret = generalRepairs(player);
            case 11 -> ret = visitAvenidaMediterraneo (player);
            case 12 -> ret = advanceToOutput (player);
            case 13 -> ret = deviation (player);
            case 14 -> ret = smallShock(player);
            case 15 -> ret = takeAdvantageOfYourSavings(player);
            case 16 -> ret = ForwardToTheAvenueNewYork(player);
            default -> {
            }
        } 
        return ret;
    }
    
    public String getNextCard(Player player) {
        int card = cardsArrayList.get(0);
        cardsArrayList.remove(card);
        cardsArrayList.add(card);
        return useCard(player, card);
    }

    public int[] getPositions() {
        return positions;
    }
    //1
    private String gasolinePrice(Player player){
        int money = player.getMoney(); 
        player.setMoney(money += 50);
        return "Fortuna \n Los precios de la gasolina estan abajo, se cobra 50";
    }
    //2
    private String goToJail (Player player){
        
        return "Fortuna \n Vayase a la carcel, no pase por salida, no cobre 200";
    }
    //3
    private String tolls(Player player){
        int money = player.getMoney(); 
        player.setMoney(money -= 20);
        return "Fortuna \n Peajes, se paga 20";
    }
    //4
    private String advanceToHotel (Player player){
        //Hacer llamado a la funcion 
        int money = player.getMoney();
        player.setMoney(money += 200);
        return "Fortuna \n Avance al hotel resort, si pasa por salida, se cobra 200";
        
    }
    //5
    private String advanceToDock (Player player){
        //
        return "Fortuna \n Avance al muelle, si esta a la venta, puede comprarselo al banco, si es propiedad de alguien se paga el doble de la admision marcada";
    }
    //6
    private String takeAWalkAroundThePier (Player player){
       
        //Hacer llamado a la funcion 
        int money = player.getMoney(); 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Fortuna \n Tome un paseo por el muelle, si pasa por la salida, se cobra 200"; 
        
    }
    //7
    private String takeAtripAvenidaOriental(Player player){
        
        //Hacer llamado a la funcion 
        int money = player.getMoney();
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Fortuna \n Tome un viaje por avenida oriental, si pasa por salida cobre 200"; 
        
    }
    //8
    private String advanceAvenidaMediterraneo (Player player){
        //
        return "Fortuna \n Avance a avneida mediterraneo, si esta a la venta puede comparselo al banco, si es propiedad de alguien pague el doble de la cuenta marcada"; 
        
    }
    //9
    private String getOutOfJail(Player player){
        player.setOutOfJailCard(true);
        return "Fortuna \n Salga de la carcel gratis, conserve esta tarjeta hasta utilizarla o venderla"; 
    }
    //10
    private String generalRepairs(Player player){
        int money = player.getMoney();
        int properties = player.getPlayerNum();
        player.setMoney(money += 20 * properties);
        System.out.println(player.getMoney());
        return "Fortuna \n Reparaciones Generales, se paga 20 por cada una de sus propiedades"; 
        
        
    }
    //11
    private String visitAvenidaMediterraneo (Player player){
        
        //Hacer llamado a la funcion 
        int money = player.getMoney();
        player.setMoney(money += 200);
        return "Fortuna \n Visite Avenida Mediterraneo, si pasa por salida, cobre 200"; 
        
    }
    //12
    private String advanceToOutput (Player player){
        int money = player.getMoney();
        player.setMoney(money += 200);
        return "Fortuna \n Avance a la salida, se cobre 200"; 
        
    }
    //13
    private String deviation (Player player){
        player.addToPosition(0, -3);
        return "Fortuna \n Desviacion, retroceda 3 casillas"; 
        
    }
    //14
    private String smallShock(Player player){
        int money = player.getMoney();
        int players = player.getPlayerNum();
        player.setMoney(money -= 50 * players);
        return "Fortuna \n Pequeño choque, pague a cada jugador 50"; 
        
    }
    //15
    private String takeAdvantageOfYourSavings(Player player){
        int money = player.getMoney();
        player.setMoney(money += 150);
        System.out.println(player.getMoney());
        return "Fortuna \n Aproveche su ahorro de dia lluvioso, se cobra 150"; 
           
    }
    //16
    private String ForwardToTheAvenueNewYork(Player player){
        //
        return "Fortuna \n Avance a la avenida New York, si esta a la venta puede comprarsela al banco, si es propiedad de alguien pague el doble de la cuenta marcada";
    }
    
}

