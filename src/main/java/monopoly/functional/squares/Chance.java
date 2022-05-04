package monopoly.functional.squares;

import monopoly.functional.Player;

public class Chance {
    
    private final int[] positions = {8, 23, 37};
   
    public void RandomCC (Player player){
        int num = (int)(Math.random()*16+1);
        switch (num) {
            case 1 -> gasolinePrice(player.getMoney(), player);
            case 2 -> goToJail (player);
            case 3 -> tolls(player.getMoney(), player);
            case 4 -> advanceToHotel(player.getMoney(), player);
            case 5 -> advanceToDock (player);
            case 6 -> takeAWalkAroundThePier (player.getMoney(), player);
            case 7 -> takeAtripAvenidaOriental(player.getMoney(), player);
            case 8 -> advanceAvenidaMediterraneo (player.getMoney(), player);
            case 9 -> getOutOfJail(player);
            case 10 -> generalRepairs(player.getMoney(), player.getProperties(), player);
            case 11 -> visitAvenidaMediterraneo (player.getMoney(), player);
            case 12 -> advanceToOutput (player.getMoney(), player);
            case 13 -> deviation (player.getPosition(), player);
            case 14 -> smallShock(player.getMoney(), player.getPlayerNum(),player);
            case 15 -> takeAdvantageOfYourSavings(player.getMoney(), player);
            case 16 -> ForwardToTheAvenueNewYork(player);
            default -> {
            }
        }        
    }

    public int[] getPositions() {
        return positions;
    }
    //1
    private void gasolinePrice(int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Los precios de la gasolina estan abajo, se cobra 50");
        player.setMoney(money += 50);
        System.out.println(player.getMoney());
    }
    //2
    private void goToJail (Player player){
        System.out.println("Fortuna");
        System.out.println("Vayase a la carcel, no pase por salida, no cobre 200");
    }
    //3
    private void tolls(int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Peajes, se paga 20");
        player.setMoney(money -= 20);
        System.out.println(player.getMoney());
    }
    //4
    private void advanceToHotel (int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Avance al hotel resort, si pasa por salida, se cobra 200");
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        
    }
    //5
    private void advanceToDock (Player player){
        System.out.println("Fortuna");
        System.out.println("Avance al muelle, si esta a la venta, puede comprarselo al banco, si es propiedad de alguien se paga el doble de la admision marcada");
        //
    }
    //6
    private void takeAWalkAroundThePier (int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Tome un paseo por el muelle, si pasa por la salida, se cobra 200");
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        
    }
    //7
    private void takeAtripAvenidaOriental(int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Tome un viaje por avenida oriental, si pasa por salida cobre 200");
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        
    }
    //8
    private void advanceAvenidaMediterraneo (int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Avance a avneida mediterraneo, si esta a la venta puede comparselo al banco, si es propiedad de alguien pague el doble de la cuenta marcada");
        //
        
    }
    //9
    private void getOutOfJail(Player player){
        System.out.println("Fortuna");
        System.out.println("Salga de la carcel gratis, conserve esta tarjeta hasta utilizarla o venderla");
    }
    //10
    private void generalRepairs(int money, int properties, Player player){
        System.out.println("Fortuna");
        System.out.println("Reparaciones Generales, se paga 20 por cada una de sus propiedades");
        player.setMoney(money += 20 * properties);
        System.out.println(player.getMoney());
        
        
    }
    //11
    private void visitAvenidaMediterraneo (int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Visite Avenida Mediterraneo, si pasa por salida, cobre 200");
        //Hacer llamado a la funcion 
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        
    }
    //12
    private void advanceToOutput (int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Avance a la salida, se cobre 200");
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        
    }
    //13
    private void deviation (int position, Player player){
        System.out.println("Fortuna");
        System.out.println("Desviacion, retroceda 3 casillas");
        player.setPosition(position -= 3);
        //Retroceder 3 casillas
        //setPosition(-3);
    }
    //14
    private void smallShock(int money, int players, Player player){
        System.out.println("Fortuna");
        System.out.println("Pequeño choque, pague a cada jugador 50");
        player.setMoney(money -= 50 * players);
        System.out.println(player.getMoney());
        
    }
    //15
    private void takeAdvantageOfYourSavings(int money, Player player){
        System.out.println("Fortuna");
        System.out.println("Aproveche su ahorro de dia lluvioso, se cobra 150");
        player.setMoney(money += 150);
        System.out.println(player.getMoney());
           
    }
    //16
    private void ForwardToTheAvenueNewYork(Player player){
        System.out.println("Fortuna");
        System.out.println("Avance a la avenida New York, si esta a la venta puede comprarsela al banco, si es propiedad de alguien pague el doble de la cuenta marcada");
        //
    }
    
}

