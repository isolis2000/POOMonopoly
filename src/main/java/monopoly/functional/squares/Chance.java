package monopoly.functional.squares;

import monopoly.functional.Player;

public class Chance {
    
    private final int[] positions = {8, 23, 37};
    
    public String RandomCC (Player player){
        int num = (int)(Math.random()*16+1);
        String ret = "";
        switch (num) {
            case 1 -> ret = gasolinePrice(player.getMoney(), player);
            case 2 -> ret = goToJail (player);
            case 3 -> ret = tolls(player.getMoney(), player);
            case 4 -> ret = advanceToHotel(player.getMoney(), player);
            case 5 -> ret = advanceToDock (player);
            case 6 -> ret = takeAWalkAroundThePier (player.getMoney(), player);
            case 7 -> ret = takeAtripAvenidaOriental(player.getMoney(), player);
            case 8 -> ret = advanceAvenidaMediterraneo (player.getMoney(), player);
            case 9 -> ret = getOutOfJail(player);
            case 10 -> ret = generalRepairs(player.getMoney(), player.getProperties(), player);
            case 11 -> ret = visitAvenidaMediterraneo (player.getMoney(), player);
            case 12 -> ret = advanceToOutput (player.getMoney(), player);
            case 13 -> ret = deviation (player.getPosition(), player);
            case 14 -> ret = smallShock(player.getMoney(), player.getPlayerNum(),player);
            case 15 -> ret = takeAdvantageOfYourSavings(player.getMoney(), player);
            case 16 -> ret = ForwardToTheAvenueNewYork(player);
            default -> {
            }
        } 
        return ret;
    }
    


    public int[] getPositions() {
        return positions;
    }
    //1
    private String gasolinePrice(int money, Player player){
        player.setMoney(money += 50);
        System.out.println(player.getMoney());
        return "Fortuna \n Los precios de la gasolina estan abajo, se cobra 50";
    }
    //2
    private String goToJail (Player player){
        
        return "Fortuna \n Vayase a la carcel, no pase por salida, no cobre 200";
    }
    //3
    private String tolls(int money, Player player){
        
        player.setMoney(money -= 20);
        System.out.println(player.getMoney());
        return "Fortuna \n Peajes, se paga 20";
    }
    //4
    private String advanceToHotel (int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Avance al hotel resort, si pasa por salida, se cobra 200");
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        return "Fortuna \n Avance al hotel resort, si pasa por salida, se cobra 200";
        
    }
    //5
    private String advanceToDock (Player player){
        //
        return "Fortuna \n Avance al muelle, si esta a la venta, puede comprarselo al banco, si es propiedad de alguien se paga el doble de la admision marcada";
    }
    //6
    private String takeAWalkAroundThePier (int money, Player player){
       
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Fortuna \n Tome un paseo por el muelle, si pasa por la salida, se cobra 200"; 
        
    }
    //7
    private String takeAtripAvenidaOriental(int money, Player player){
        
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Fortuna \n Tome un viaje por avenida oriental, si pasa por salida cobre 200"; 
        
    }
    //8
    private String advanceAvenidaMediterraneo (int money, Player player){
        //
        return "Fortuna \n Avance a avneida mediterraneo, si esta a la venta puede comparselo al banco, si es propiedad de alguien pague el doble de la cuenta marcada"; 
        
    }
    //9
    private String getOutOfJail(Player player){
        
        return "Fortuna \n Salga de la carcel gratis, conserve esta tarjeta hasta utilizarla o venderla"; 
    }
    //10
    private String generalRepairs(int money, int properties, Player player){
        player.setMoney(money += 20 * properties);
        System.out.println(player.getMoney());
        return "Fortuna \n Reparaciones Generales, se paga 20 por cada una de sus propiedades"; 
        
        
    }
    //11
    private String visitAvenidaMediterraneo (int money, Player player){
        
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Fortuna \n Visite Avenida Mediterraneo, si pasa por salida, cobre 200"; 
        
    }
    //12
    private String advanceToOutput (int money, Player player){
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Fortuna \n Avance a la salida, se cobre 200"; 
        
    }
    //13
    private String deviation (int position, Player player){
        
        player.setPosition(position -= 3);
        return "Fortuna \n Desviacion, retroceda 3 casillas"; 
        
    }
    //14
    private String smallShock(int money, int players, Player player){
        
        player.setMoney(money -= 50 * players);
        System.out.println(player.getMoney());
        return "Fortuna \n Pequeño choque, pague a cada jugador 50"; 
        
    }
    //15
    private String takeAdvantageOfYourSavings(int money, Player player){
       
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

