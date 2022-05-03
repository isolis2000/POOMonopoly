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
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
   
    public static void RandomCC (Player player){
        int num = (int)(Math.random()*16+1);
        switch (num) {
            case 1 -> penaltyFee(player.getMoney(), player);
            case 2 -> pickupPedestrian(player.getMoney(), player);
            case 3 -> shock (player.getMoney(), player);
            case 4 -> getOutOfJail(player);
            case 5 -> leaveCellular (player.getMoney(), player);
            case 6 -> takeInCar (player.getMoney(), player);
            case 7 -> foreigner (player.getMoney(), player);
            case 8 -> RequestPassengers(player.getMoney(), player.getPlayerNum(), player);
            case 9 -> flatTire (player.getMoney(), player);
            case 10 -> forgetTheHotel(player.getMoney(), player);
            case 11 -> searchCash(player.getMoney(), player);
            case 12 -> goToJail(player);
            case 13 -> forwardToOut(player.getMoney(), player);
            case 14 -> winTheFirstPlace(player.getMoney(), player);
            case 15 -> taxIncrease (player.getMoney(), player.getProperties(), player);
            case 16 -> thegrandmother(player.getMoney(), player);
            default -> {
            }
        }
       
        
    }
    //1
    public static void penaltyFee (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Multa por exceso de velocidad, se paga 50");
        player.setMoney(money -= 50);
        System.out.println(player.getMoney());
     
    }
    //2
    public static void pickupPedestrian(int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Recogio a un peaton, Gana 20");
        player.setMoney(money += 20);
        System.out.println(player.getMoney());
    }
    //3
    public static void shock (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Gasolinera, pague 50");
        player.setMoney(money -= 50);
        System.out.println(player.getMoney());
        
    }
    //4
    public static void getOutOfJail(Player player){
        System.out.println("Arca Comunal");
        System.out.println("Salga de la carcel gratis, se concerva esta tarjeta hasta utilizarla o venderla");
    }
    //5
    public static void leaveCellular (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Dejo su celular en la ultima parada, se cobra 50");
        player.setMoney(money += 50);
        System.out.println(player.getMoney());
  
    }
    //6
    public static void takeInCar (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Lleva en Auto a un amigo, se cobra 20");
        player.setMoney(money += 20); 
        System.out.println(player.getMoney());
    }
    //7
    public static void foreigner (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Un extrajero le pago su cuota en la cabina de peaje, se cobra 20");
        player.setMoney(money += 20);
        System.out.println(player.getMoney());
    }
    //8
    public static void RequestPassengers (int money, int players, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Pida a sus pasajeros que cooperen un poco con la gasolina, se cobra 10 por cada jugador");
        player.setMoney(money += 10 *(players - 1));
        System.out.println(player.getMoney());
        
    }
    //9
    public static void flatTire (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Llanta desinflada, pague 100");
        player.setMoney(money -= 100);
        System.out.println(player.getMoney());
        
    }
    //10
    public static void forgetTheHotel (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Olvide el hotel, duerma bajo las estrellas y ahorre dinero, se cobre 100");
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
        
    }
    //11
    public static void searchCash (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Busque efectivo en su coche, se cobra 10");
        player.setMoney(money += 10);
        System.out.println(player.getMoney());
        
    }
    //12
    public static void goToJail(Player player){
        System.out.println("Arca Comunal");
        System.out.println("Vayase a la carcel, no pase por la salida, no cobre 200");
    }
    //13
    public static void forwardToOut(int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Avance a la salida, se cobra 200");
        player.setMoney(money += 200);
        System.out.println(player.getMoney());
        
    }
    //14
    public static void winTheFirstPlace (int money, Player player){
        System.out.println("Arca Comunal");
        System.out.println("Gano el primer lugar de un concurso de comer pastel, se cobra 100");
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
       
    }
    //15
    public static void taxIncrease (int money, int properties, Player player){
        System.out.println("Arco Comunal");
        System.out.println("Aumento de los impuestos de propiedad, se paga 50 por cada propiedad");
        player.setMoney(money += 50 * properties);
        System.out.println(player.getMoney());
        
    }
    //16
    public static void thegrandmother (int money, Player player){
        System.out.println("Arco Comunal");
        System.out.println("La abuela le envio un prestamo, se cobra 100");
        player.setMoney(money += 100);
        System.out.println(player.getMoney());
    }
    }  

