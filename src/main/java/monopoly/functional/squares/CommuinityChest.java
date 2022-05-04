/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly.functional.squares;

import monopoly.functional.Player;

/**
 *
 * @author ANTONY
 */
public class CommuinityChest {
    
    private final int[] positions = {3, 18, 34};
   
    public String RandomCC (Player player){
        int num = (int)(Math.random()*16+1);
        String ret = "";
        switch (num) {
            case 1 -> ret = penaltyFee(player.getMoney(), player);
            case 2 -> ret = pickupPedestrian(player.getMoney(), player);
            case 3 -> ret = shock (player.getMoney(), player);
            case 4 -> ret = getOutOfJail(player);
            case 5 -> ret = leaveCellular (player.getMoney(), player);
            case 6 -> ret = takeInCar (player.getMoney(), player);
            case 7 -> ret = foreigner (player.getMoney(), player);
            case 8 -> ret = RequestPassengers(player.getMoney(), player.getPlayerNum(), player);
            case 9 -> ret = flatTire (player.getMoney(), player);
            case 10 -> ret = forgetTheHotel(player.getMoney(), player);
            case 11 -> ret = searchCash(player.getMoney(), player);
            case 12 -> ret = goToJail(player);
            case 13 -> ret = forwardToOut(player.getMoney(), player);
            case 14 -> ret = winTheFirstPlace(player.getMoney(), player);
            case 15 -> ret = taxIncrease (player.getMoney(), player.getProperties(), player);
            case 16 -> ret = thegrandmother(player.getMoney(), player);
            default -> {
            }
        }
        return ret;
               
    }

    public int[] getPositions() {
        return positions;
    }
    //1
    private String penaltyFee (int money, Player player){
        
        player.setMoney(money -= 50);
        System.out.println(player.getMoney());
        return "Arca comunal \n Multa por exceso de velocidad, se paga 50";
     
    }
    //2
    private String pickupPedestrian(int money, Player player){
        player.setMoney(money += 20);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Recogio a un peaton, Gana 20";
    }
    //3
    private String shock (int money, Player player){
        player.setMoney(money -= 50);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Gasolinera, pague 50";
        
    }
    //4
    private String getOutOfJail(Player player){
        return "Arca Comunal \n Salga de la carcel gratis, se concerva esta tarjeta hasta utilizarla o venderla";
        //Hay que hacer extra
    }
    //5
    private String leaveCellular (int money, Player player){
        player.setMoney(money += 50);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Dejo su celular en la ultima parada, se cobra 50";
  
    }
    //6
    private String takeInCar (int money, Player player){
        player.setMoney(money += 20); 
        System.out.println(player.getMoney());
        return "Arca Comunal \n Lleva en Auto a un amigo, se cobra 20";
    }
    //7
    private String foreigner (int money, Player player){
        player.setMoney(money += 20);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Un extrajero le pago su cuota en la cabina de peaje, se cobra 20";
    }
    //8
    private String RequestPassengers (int money, int players, Player player){
        player.setMoney(money += 10 *(players - 1));
        System.out.println(player.getMoney());
        return "Arca Comunal \n Pida a sus pasajeros que cooperen un poco con la gasolina, se cobra 10 por cada jugador";
    }
    //9
    private String flatTire (int money, Player player){
        player.setMoney(money -= 100);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Llanta desinflada, pague 100"; 
        
    }
    //10
    private String forgetTheHotel (int money, Player player){
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Olvide el hotel, duerma bajo las estrellas y ahorre dinero, se cobre 100"; 

    }
    //11
    private String searchCash (int money, Player player){
        
        player.setMoney(money += 10);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Busque efectivo en su coche, se cobra 10";
        
    }
    //12
    private String goToJail(Player player){
        return "Arca Comunal \n Vayase a la carcel, no pase por la salida, no cobre 200";
    }
    //13
    private String forwardToOut(int money, Player player){
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Avance a la salida, se cobra 200";
        
    }
    //14
    private String winTheFirstPlace (int money, Player player){
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        return "Arca Comunal \n Gano el primer lugar de un concurso de comer pastel, se cobra 100";
       
    }
    //15
    private String taxIncrease (int money, int properties, Player player){

        player.setMoney(money += 50 * properties);
        System.out.println(player.getMoney());
        return "Arco Comunal \n Aumento de los impuestos de propiedad, se paga 50 por cada propiedad";
        
    }
    //16
    private String thegrandmother (int money, Player player){
        System.out.println("Arco Comunal");
        System.out.println("La abuela le envio un prestamo, se cobra 100");
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        return "Arco Comunal \n La abuela le envio un prestamo, se cobra 100";
    }
    }  

