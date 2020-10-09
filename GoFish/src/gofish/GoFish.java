/*
 *  Zoë Mermin
 *  AT Computer Science
 * 
 */
package gofish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author zoemermin
 */
public class GoFish {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner kb= new Scanner(System.in);
        //declare
        int catching=0,checknewcard=-1,checkDrawLoop=0, inhand=0;
        String spot,playername="",playernotname="",matchedcard="";
        boolean tog2=true, tog1=false;
        
        ArrayList<String> playert = new ArrayList<>();
        ArrayList<String> playernot = new ArrayList<>();
        ArrayList<String> booksOne = new ArrayList<>();
        ArrayList<String> booksTwo = new ArrayList<>();
        //deck
        ArrayList<String> stock = new ArrayList<>();
        for (int l = 0; l < 4; l++) {
            stock.add("ace");
            stock.add("1");
            stock.add("2");
            stock.add("3");
            stock.add("4");
            stock.add("5");
            stock.add("6");
            stock.add("7");
            stock.add("8");
            stock.add("9");
            stock.add("10");
            stock.add("jack");
            stock.add("queen");
            stock.add("king");    
        }
        //shuffle deck
        Collections.shuffle(stock);
        
        //player one hand
        ArrayList<String> onehand = new ArrayList<>();

        for (int i = 0; i<7; i++) {
            spot=stock.get(0);
            onehand.add(spot); 
            stock.remove(0);
        }
        System.out.println("Player One's Hand:\n" + onehand);
        
        //player two hand
        ArrayList<String> twohand = new ArrayList<>();
        for (int j = 0; j<7; j++) {
            spot= stock.get(0);
            twohand.add(spot);
            stock.remove(0);   
        }
        
        System.out.println("Player Two's Hand:\n" +twohand);
        
        while (stock.size()>0) {
            if (tog2==true) {
                playername="Player One";
                playernotname="Player Two";
                playert = onehand;
                playernot = twohand;
            }
            else {
                playername="Player Two";
                playernotname="Player One";
                playert = twohand;
                playernot = onehand;
            }
            
            if (playert.isEmpty()) {
                playert.add(stock.get(0));
                stock.remove(0);
                System.out.println("\nYou are out of cards. Your new card from "
                        + "the stock is:\n" + playert);
            }
                
            System.out.println("\n"+playername + "- what card do you want to ask for? ");
            String ask = kb.next();
            firstloop:
            for (int y=0; y<playert.size(); y++) {
                tog1 = ask.equals(playert.get(y));
                if (tog1==true) {
                    inhand=1;
                    break firstloop;
                }
            }
            if (inhand==1) {
                for (int r = 0; r < playernot.size(); r++) {
                    spot= playernot.get(r);
                    int check=ask.compareToIgnoreCase(spot);
                    if (check==0) {
                        playert.add(spot);
                        playernot.remove(r);
                        r-=1;
                        catching=1;
                    }
                }

                if (catching==1) {
                    System.out.println("\nYou made a catch!");
                    System.out.println(playername +"'s new hand:\n" + playert);
                    System.out.println(playernotname +"'s new hand:\n" +playernot);
                    for (int e = 0; e < playert.size(); e++) {
                        int amount = Collections.frequency(playert, playert.get(e));
                        matchedcard=playert.get(e);
                        if (amount==4) {
                            System.out.println("\nYou got a book of " + matchedcard + "s!");
                            for (int f = 0; f < playert.size(); f++) {
                                if (playert.get(f).equals(matchedcard)) {
                                    playert.remove(f); 
                                    f-=1;
                                    if (tog2==true) {
                                        booksOne.add(matchedcard);
                                    }
                                    else {
                                        booksTwo.add(matchedcard);
                                    }
                            }    
                        }

                        System.out.println(playername +"'s new hand:\n" + playert);
                        System.out.println(playernotname +"'s new hand:\n" +playernot);   
                        }
                    }

                }


                else {
                    System.out.println("\nGo fish!");
                    playert.add(stock.get(0));
                    String newcard= stock.get(0);
                    stock.remove(0);
                    System.out.println(playernotname +"'s new hand:\n" +playernot);
                    System.out.println(playername + "'s new hand:\n" + playert);
                    checknewcard=ask.compareToIgnoreCase(newcard);
                    checkDrawLoop=1;
                    for (int e = 0; e < playert.size(); e++) {
                        int amount = Collections.frequency(playert, playert.get(e));
                        matchedcard=playert.get(e);
                        if (amount==4) {
                            System.out.println("\nYou got a book of " + 
                                    matchedcard + "s!");
                            for (int f = 0; f < playert.size(); f++) {
                                if (playert.get(f).equals(matchedcard)) {
                                    playert.remove(f); 
                                    f-=1;
                                    if (tog2==true) {
                                        booksOne.add(matchedcard);
                                    }
                                    else  {
                                        booksTwo.add(matchedcard);
                                    }
                            }    
                        } 
                        System.out.println(playernotname +"'s new hand:\n" +playernot);
                        System.out.println(playername +"'s new hand:\n" + playert);
                    }
                }

                }
                if (tog2==true) {
                    onehand=playert;
                    twohand=playernot;
                }
                else {
                    twohand=playert;
                    onehand = playernot;
                }

                if (checknewcard==0) {
                    System.out.println("You got lucky and drew the card you "
                            + "asked for! Go again! ");
                }
                else if (checknewcard!=0 && checkDrawLoop==1) {
                    tog2=!tog2;
                }
                else {

                }
                checknewcard=1;
                catching=0;
                checkDrawLoop=0;
                inhand=0;
                }
            
            else {
                System.out.println("You can only ask for a card you have in "
                        + "your hand! ");
                
            }
            
        }
        if (onehand.isEmpty()) {
            for (int m = 0; m < stock.size(); m++) {
                String finalcards = stock.get(0);
                twohand.add(finalcards);
                stock.remove(0);        
            }
            for (int e = 0; e < twohand.size(); e++) {
                        int amount = Collections.frequency(twohand, twohand.get(e));
                        matchedcard=twohand.get(e);
                        if (amount==4) { 
                            for (int f = 0; f < playert.size(); f++) {
                                if (twohand.get(f).equals(matchedcard)) {
                                    twohand.remove(f); 
                                    f-=1;
                                    booksTwo.add(matchedcard);
                            }    
                        }     
                    }
                }
        }
        
        else if (twohand.isEmpty()) {
            for (int m = 0; m < stock.size(); m++) {
                String finalcards = stock.get(0);
                onehand.add(finalcards);
                stock.remove(0);        
            }
            for (int e = 0; e < onehand.size(); e++) {
                        int amount = Collections.frequency(onehand, onehand.get(e));
                        matchedcard=onehand.get(e);
                        if (amount==4) { 
                            for (int f = 0; f < playert.size(); f++) {
                                if (onehand.get(f).equals(matchedcard)) {
                                    onehand.remove(f); 
                                    f-=1;
                                    booksTwo.add(matchedcard);
                            }    
                        }     
                    }
                }
        }
        
        System.out.println("\nGame Over!");
        if (booksOne.size()>booksTwo.size()) {
            System.out.println("\nPlayer One won with "+ booksOne.size()/4 + " books!");    
        }
        else {
            System.out.println("\nPlayer Two won with "+ booksTwo.size()/4 + " books!");
            
        }
        System.out.println("\nPlayer One's Books: \n" + booksOne);
        System.out.println("\nPlayer Two's Books: \n" + booksTwo);
        
        
    }
    
}
