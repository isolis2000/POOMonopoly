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
        System.out.println(num);
        switch (num) {
            case 1 -> ret = penaltyFee(player);
            case 2 -> ret = pickupPedestrian(player);
            case 3 -> ret = shock (player);
            case 4 -> ret = getOutOfJail(player);
            case 5 -> ret = leaveCellular (player);
            case 6 -> ret = takeInCar (player);
            case 7 -> ret = foreigner (player);
            case 8 -> ret = RequestPassengers(player);
            case 9 -> ret = flatTire (player);
            case 10 -> ret = forgetTheHotel(player);
            case 11 -> ret = searchCash(player);
            case 12 -> ret = goToJail(player);
            case 13 -> ret = forwardToOut(player);
            case 14 -> ret = winTheFirstPlace(player);
            case 15 -> ret = taxIncrease (player);
            case 16 -> ret = thegrandmother(player);
            default -> {
            }
        }
        return ret;
               
    }

    public int[] getPositions() {
        return positions;
    }
    //1
    private String penaltyFee (Player player){
        int money = player.getMoney();
        player.setMoney(money -= 50);
        System.out.println(player.getMoney());
        System.out.println("Hola1");
        return " Arca comunal \n Multa por exceso de velocidad, se paga 50";
     
    }
    //2
    private String pickupPedestrian(Player player){
        int money = player.getMoney();
        player.setMoney(money += 20);
        System.out.println(player.getMoney());
        System.out.println("Hola2");
        return " Arca Comunal \n Recogio a un peaton, Gana 20";
    }
    //3
    private String shock (Player player){
        int money = player.getMoney();
        player.setMoney(money -= 50);
        System.out.println(player.getMoney());
        System.out.println("Hola3");
        return " Arca Comunal \n Gasolinera, pague 50";
        
    }
    //4
    private String getOutOfJail(Player player){
        
        return " Arca Comunal \n Salga de la carcel gratis, se concerva esta tarjeta hasta utilizarla o venderla";
        //Hay que hacer extra
    }
    //5
    private String leaveCellular (Player player){
        System.out.println(player.getMoney());
        int money = player.getMoney();
        player.setMoney(money += 50);
        System.out.println("Hola5");
        return " Arca Comunal \n Dejo su celular en la ultima parada, se cobra 50";
  
    }
    //6
    private String takeInCar (Player player){
        System.out.println(player.getMoney());
        int money = player.getMoney();
        player.setMoney(money += 20); 
        return " Arca Comunal \n Lleva en Auto a un amigo, se cobra 20";
    }
    //7
    private String foreigner (Player player){
        int money = player.getMoney();
        player.setMoney(money += 20);
        System.out.println(player.getMoney());
        System.out.println("Hola7");
        return " Arca Comunal \n Un extrajero le pago su cuota en la cabina de peaje, se cobra 20";
    }
    //8
    private String RequestPassengers (Player player){
        int players = player.getPlayerNum();
        int money = player.getMoney();
        player.setMoney(money += 10 *(players - 1));
        System.out.println(player.getMoney());
        System.out.println("Hola8");
        return " Arca Comunal \n Pida a sus pasajeros que cooperen un poco con la gasolina, se cobra 10 por cada jugador";
    }
    //9
    private String flatTire ( Player player){
        int money = player.getMoney();
        player.setMoney(money -= 100);
        System.out.println(player.getMoney());
        System.out.println("Hola9");
        return " Arca Comunal \n Llanta desinflada, pague 100"; 
        
    }
    //10
    private String forgetTheHotel (Player player){
        int money = player.getMoney();
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        System.out.println("Hola10");
        return " Arca Comunal \n Olvide el hotel, duerma bajo las estrellas y ahorre dinero, se cobre 100"; 

    }
    //11
    private String searchCash (Player player){
        int money = player.getMoney();
        
        player.setMoney(money += 10);
        System.out.println(player.getMoney());
        System.out.println("Hola11");
        return " Arca Comunal \n Busque efectivo en su coche, se cobra 10";
        
    }
    //12
    private String goToJail(Player player){
        
        System.out.println("Hola12");
        return " Arca Comunal \n Vayase a la carcel, no pase por la salida, no cobre 200";
    }
    //13
    private String forwardToOut(Player player){
        int money = player.getMoney();
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        System.out.println("Hola13");
        return " Arca Comunal \n Avance a la salida, se cobra 200";
        
    }
    //14
    private String winTheFirstPlace (Player player){
        int money = player.getMoney();
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        System.out.println("Hola14");
        return " Arca Comunal \n Gano el primer lugar de un concurso de comer pastel, se cobra 100";
       
    }
    //15
    private String taxIncrease (Player player){
        int money = player.getMoney();
        int properties = player.getProperties();
        player.setMoney(money += 50 * properties);
        System.out.println(player.getMoney());
        System.out.println("Hola15");
        return " Arco Comunal \n Aumento de los impuestos de propiedad, se paga 50 por cada propiedad";
        
    }
    //16
    private String thegrandmother (Player player){
        int money = player.getMoney();
        System.out.println("Arco Comunal");
        System.out.println("La abuela le envio un prestamo, se cobra 100");
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        System.out.println("Hola16");
        return " Arco Comunal \n La abuela le envio un prestamo, se cobra 100";
    }
    }  

